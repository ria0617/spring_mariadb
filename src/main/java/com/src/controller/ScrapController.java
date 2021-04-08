package com.src.controller;

import javax.inject.Inject;
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
import com.src.domain.ReplyVO;
import com.src.domain.ScrapVO;
import com.src.domain.SearchCriteria;
import com.src.domain.UserVO;
import com.src.service.BoardService;
import com.src.service.ScrapService;
import com.src.service.UserService;

@Controller
@RequestMapping("/board/*")
public class ScrapController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	
	@Inject
	BoardService service;
	
	@Inject
	UserService userService;
	
	@Inject
	ScrapService scrapService;
	
	// 스크랩저장
	@RequestMapping(value = "/scrapIn", method = RequestMethod.POST)
	public String scrapIn(ReplyVO replyVO, ScrapVO scrapVO, SearchCriteria scri, RedirectAttributes rttr, Model model) throws Exception{
		//페이지 값 가져오기
		model.addAttribute("scri", scri);
		rttr.addAttribute("bno", replyVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		logger.info("스크랩 저장");
		scrapService.scrapIn(scrapVO);
		rttr.addFlashAttribute("msg", "scrapOk");
		
		return "redirect:/board/readView";
	}
	
	// 스크랩 조회
	@RequestMapping(value = "/myScrap", method = RequestMethod.GET)
	public String myScrap(Model model, HttpSession httpsession) throws Exception{
		logger.info("스크랩 조회");
		UserVO login = (UserVO) httpsession.getAttribute("login");
		String userId =  login.getUserId();
		model.addAttribute("scrap", scrapService.scrapList(userId));

		return "board/myScrap";
	}
	
	//스크랩 삭제
	@RequestMapping(value = "/scrapDelete", method = RequestMethod.POST)
	public String scrapDelete(ScrapVO scrapVO) throws Exception{
		logger.info("스크랩 삭제");
		scrapService.scrapDelete(scrapVO);
		
		return "redirect:/board/myScrap";
	}
}
