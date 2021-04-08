package com.src.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.src.domain.BoardVO;
import com.src.domain.PageMaker;
import com.src.domain.PushVO;
import com.src.domain.ReplyVO;
import com.src.domain.SearchCriteria;
import com.src.domain.UserVO;
import com.src.service.BoardService;
import com.src.service.PushService;
import com.src.service.ReplyService;
import com.src.service.UserService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	@Inject
	ReplyService replyService;
	
	@Inject
	PushService pushService;
	
	@Inject
	UserService userService;
	
	// 게시판 글 작성 화면
	@RequestMapping(value = "/writeView", method = RequestMethod.GET)
	public void writeView() throws Exception{
		logger.info("게시글 작성 화면");
	}
	
	// 게시판 글 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO boardVO) throws Exception{
		logger.info("게시글 작성");
		service.write(boardVO);
		return "redirect:/board/list";
	}
	
	// 게시판 목록 조회
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("게시글 목록");
		model.addAttribute("list",service.list(scri));
		//페이징
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));
		model.addAttribute("pageMaker", pageMaker);

		return "board/list";
	}

	// 게시물 보기
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(UserVO userVO, PushVO pushVO, BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model, HttpSession httpsession, RedirectAttributes rttr) throws Exception{
		
		//추천버튼제어
		UserVO login = (UserVO) httpsession.getAttribute("login");
		if(login != null) {
			String sessionId = login.getUserId();
			pushVO.setUserId(sessionId);
			System.out.println(pushVO);
			int pushCheck = pushService.pushCheck(pushVO);
			System.out.println(pushCheck);
			if(pushCheck == 1) {
				model.addAttribute("pushCheck", pushCheck);
			}else {
				model.addAttribute("pushCheck", pushCheck);
			}
			System.out.println("회원" + sessionId);
		}
		
		logger.info("게시글 읽기");
		model.addAttribute("read", service.read(boardVO.getBno()));
		model.addAttribute("list",service.list(scri));

		//댓글 보기
		List<ReplyVO> replyList = replyService.readReply(boardVO.getBno());
		model.addAttribute("replyList", replyList);
		
		//총 추천수
		int Ptotal = pushService.totalPush(pushVO);
		model.addAttribute("push", Ptotal);
		
		return "board/readView";
	}
	
	// 게시판 수정뷰
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("게시글 수정 페이지");
		model.addAttribute("update", service.read(boardVO.getBno()));
		model.addAttribute("scri", scri);

		return "board/updateView";
	}
	
	// 게시판 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("게시글 수정");
		service.update(boardVO);

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/board/list";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("게시글 삭제");
		service.delete(boardVO.getBno());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/board/list";
	}
	
	//댓글 작성
	@RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	public String replyWrite(ReplyVO replyVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("댓글 작성");
		replyService.writeReply(replyVO);
		
		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/board/readView";
	}
	
	//댓글 수정 post
	@RequestMapping(value="/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(ReplyVO replyVO, SearchCriteria scri, RedirectAttributes rttr, Model model) throws Exception {
		System.out.println("들어온 값: " + replyVO);
		//수정 댓글 조회
		model.addAttribute("replyUpdate", replyService.selectReply(replyVO.getRno()));
		model.addAttribute("scri", scri);
		
		logger.info("댓글 수정 post");
		replyService.updateReply(replyVO);
		System.out.println("updateReply: " + replyVO);
		
		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView";
	}
		
	//댓글 삭제
	@RequestMapping(value="/replyDelete", method = RequestMethod.POST)
	public String replyDelete(ReplyVO replyVO, SearchCriteria scri, RedirectAttributes rttr, Model model) throws Exception {
		logger.info("댓글 삭제");
		System.out.println(replyVO);
		//삭제 댓글 조회
		model.addAttribute("replyDelete", replyService.selectReply(replyVO.getRno()));
		model.addAttribute("scri", scri);
		
		//댓글 삭제
		replyService.deleteReply(replyVO);
		
		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView";
	}
	
	//추천하기
	@RequestMapping(value = "/pushIn", method = RequestMethod.POST)
	public String pushIn(BoardVO boardVO, ReplyVO replyVO, PushVO pushVO, SearchCriteria scri, RedirectAttributes rttr, Model model) throws Exception{
		logger.info("추천하기");
		
		//페이지 값 가져오기
		model.addAttribute("scri", scri);
		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		pushService.pushIn(pushVO);
		
		//총 추천수
		int Ptotal = pushService.totalPush(pushVO);
		
		boardVO.setBno(pushVO.getBno());
		boardVO.setTotalpush(Ptotal);
		service.boardTotalPust(boardVO);
		
		
		return "redirect:/board/readView";
	}
	
	//추천회수
	@RequestMapping(value = "/pushOut", method = RequestMethod.POST)
	public String pushOut(BoardVO boardVO, ReplyVO replyVO, PushVO pushVO, SearchCriteria scri, RedirectAttributes rttr, Model model) throws Exception{
		logger.info("추천하기");
		
		//페이지 값 가져오기
		model.addAttribute("scri", scri);
		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		pushService.pushOut(pushVO);
		
		//총 추천수
		int Ptotal = pushService.totalPush(pushVO);
	
		boardVO.setBno(pushVO.getBno());
		boardVO.setTotalpush(Ptotal);
		service.boardTotalPust(boardVO);
		
		return "redirect:/board/readView";
	}
	
}
