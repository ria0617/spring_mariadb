package com.src.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.src.dao.BoardDAO;
import com.src.domain.BoardVO;
import com.src.domain.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		dao.write(boardVO);
	}
	
	// 게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception {
		return dao.list(scri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return dao.listCount(scri);
	}
	
	// 게시물 보기
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(int bno) throws Exception {
		dao.boardHit(bno);
		return dao.read(bno);
	}
	
	//게시물 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {
		dao.update(boardVO);
	}

	//게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}
	
	//게시판 총 추천 수 
	@Override
	public void boardTotalPust(BoardVO boardVO) throws Exception {
		dao.boardTotalPust(boardVO);
	}
		
}
