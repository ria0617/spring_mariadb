package com.src.service;

import java.util.List;

import com.src.domain.BoardVO;
import com.src.domain.Criteria;
import com.src.domain.SearchCriteria;

public interface BoardService {
	// 게시글 작성
	public void write(BoardVO boardVO) throws Exception;
	
	// 게시물 목록 조회
	public List<BoardVO> list(SearchCriteria scri) throws Exception;
	
	//게시물 총 갯수
	public int listCount(SearchCriteria scri) throws Exception;
	
	// 게시물 보기
	public BoardVO read(int bno) throws Exception;
	
	// 게시물 수정
	public void update(BoardVO boardVO) throws Exception;
	
	// 게시물 삭제
	public void delete(int bno) throws Exception;
	
	//게시물 총 조회수
	public void boardTotalPust(BoardVO boardVO) throws Exception;

}
