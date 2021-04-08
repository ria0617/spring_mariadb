package com.src.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.src.domain.BoardVO;
import com.src.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final String namespace = "boardMapper" ;
	
	@Inject
	private SqlSession sqlSession;
	
	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert(namespace + ".write", boardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception {
		return sqlSession.selectList(namespace + ".listPage", scri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne(namespace + ".listCount", scri);
	}
	
	// 게시물 보기
	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne(namespace + ".read", bno);
	}

	// 게시물 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {
		sqlSession.update(namespace + ".update", boardVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(namespace + ".delete", bno);
	}
	
	// 게시판 조회수
	@Override
	public void boardHit(int bno) throws Exception {
		sqlSession.update(namespace + ".boardHit", bno);
	}
	
	//게시판 총 추천수
	@Override
	public void boardTotalPust(BoardVO boardVO) throws Exception {
		sqlSession.update(namespace + ".boardTotalPust", boardVO);
		
	}
}
