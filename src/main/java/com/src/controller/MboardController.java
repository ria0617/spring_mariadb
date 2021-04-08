package com.src.controller;

import java.io.File;

import javax.annotation.Resource;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.src.domain.CategoryVO;
import com.src.domain.MBoardVO;
import com.src.domain.PageMaker;
import com.src.domain.SearchCriteria;
import com.src.service.MBoardService;
import com.src.service.UserService;
import com.src.utils.UploadFileUtils;


@Controller
@RequestMapping("/movie/*")
public class MboardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	UserService userService;
	
	@Inject
	MBoardService Mservice;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	// 영화 소개 글 작성 화면
	@RequestMapping(value = "/M_writeView", method = RequestMethod.GET)
	public void MwriteView(CategoryVO categoryVO, Model model) throws Exception{
		model.addAttribute("category",Mservice.categoryList());
		logger.info("영화 소개 글 작성 화면");
	}
	
	// 영화 소개 글 작성
	@RequestMapping(value = "/M_write", method = RequestMethod.POST)
	public String Mwrite(CategoryVO categoryVO, MBoardVO mboardVO, MultipartFile file) throws Exception{
		//이미지 첨부파일
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		if(file != null) {
			fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
		} else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}

		mboardVO.setPost_img(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		mboardVO.setPost_thumbimg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		logger.info("영화 소개 글 작성");
		Mservice.movieWrite(mboardVO);
		
		return "redirect:/movie/M_list";
	}

	// 영화 소개 목록 조회
	@RequestMapping(value = "/M_list", method = RequestMethod.GET)
	public String Mlist(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("영화 소개글 목록");
		model.addAttribute("mlist",Mservice.movieListPage(scri));
		
		//페이징
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(Mservice.movieListCount(scri));
		model.addAttribute("pageMaker", pageMaker);

		return "movie/M_list";
	}
	
	// 영화소개 글 보기
	@RequestMapping(value = "/M_readView", method = RequestMethod.GET)
	public String Mread(CategoryVO categoryVO, MBoardVO mboardVO, @ModelAttribute("scri") SearchCriteria scri, Model model, HttpSession httpsession, RedirectAttributes rttr) throws Exception{
		logger.info("영화 소개글 읽기");
		model.addAttribute("category", Mservice.searchCategoty(categoryVO));
		model.addAttribute("mread", Mservice.movieRead(mboardVO.getMovie_id()));
		model.addAttribute("mlist",Mservice.movieListPage(scri));
		return "movie/M_readView";
	}
	
	// 영화소개 삭제
	@RequestMapping(value = "/M_delete", method = RequestMethod.POST)
	public String Mdelete(MBoardVO mboardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("영화 소개글 삭제");
		Mservice.movieDelete(mboardVO.getMovie_id());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/movie/M_list";
	}
	
	// 게시판 수정뷰
	@RequestMapping(value = "/M_updateView", method = RequestMethod.GET)
	public String MupdateView(CategoryVO categoryVO, MBoardVO mboardVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		mboardVO.setCategory_id(categoryVO.getCategory_id());
		categoryVO.setCategory_id(categoryVO.getCategory_id());
		model.addAttribute("mupdate", Mservice.movieRead(mboardVO.getMovie_id()));
		model.addAttribute("scri", scri);
		model.addAttribute("category",Mservice.categoryList());
		model.addAttribute("c_name", Mservice.searchCategoty(categoryVO));
		return "movie/M_updateView";
	}
	
	// 게시판 수정
	@RequestMapping(value = "/M_update", method = RequestMethod.POST)
	public String Mupdate(CategoryVO categoryVO, MBoardVO mboardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr, MultipartFile file, HttpServletRequest req) throws Exception{
		// 새로운 파일이 등록되었는지 확인
		if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
		// 기존 파일을 삭제
		new File(uploadPath + req.getParameter("post_img")).delete();
		new File(uploadPath + req.getParameter("post_thumbimg")).delete();

		// 새로 첨부한 파일을 등록
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);

		mboardVO.setPost_img(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		mboardVO.setPost_thumbimg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);

		} else {// 새로운 파일이 등록되지 않았다면
			// 기존 이미지를 그대로 사용
			mboardVO.setPost_img(req.getParameter("post_img"));
			mboardVO.setPost_thumbimg(req.getParameter("post_thumbimg"));
		}
		Mservice.movieUpdate(mboardVO);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/movie/M_list";
	}
}
