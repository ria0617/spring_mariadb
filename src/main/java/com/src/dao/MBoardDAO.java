package com.src.dao;

import java.util.List;

import com.src.domain.CategoryVO;
import com.src.domain.MBoardVO;
import com.src.domain.SearchCriteria;

public interface MBoardDAO {
	
	// 게시글 작성
	public void movieWrite(MBoardVO mboardVO) throws Exception;
	
	// 게시물 목록 조회
	public List<MBoardVO> movieListPage(SearchCriteria scri) throws Exception;
		
	//게시물 총 갯수
	public int movieListCount(SearchCriteria scri) throws Exception;
	
	// 게시물 보기
	public MBoardVO movieRead(int movie_id) throws Exception;

	// 게시물 수정
	public void movieUpdate(MBoardVO mboardVO) throws Exception;
	
	// 게시물 삭제
	public void movieDelete(int movie_id) throws Exception;
	
	// 게시판 조회수
	public void movieBoardHit(int movie_id) throws Exception;
	
	// 카테고리 목록 조회
	public List<CategoryVO> categoryList() throws Exception;
	
	//특정 카테고리 이름 조회
	public CategoryVO searchCategoty(CategoryVO categoryVO) throws Exception;
	
}
