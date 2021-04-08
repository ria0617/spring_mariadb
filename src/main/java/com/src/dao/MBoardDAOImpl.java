package com.src.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.src.domain.BoardVO;
import com.src.domain.CategoryVO;
import com.src.domain.MBoardVO;
import com.src.domain.SearchCriteria;

@Repository
public class MBoardDAOImpl implements MBoardDAO {
	
	private static final String namespace = "MBoardMapper" ;
	
	@Inject
	private SqlSession sqlSession;
	
	//게시글 작성
	@Override
	public void movieWrite(MBoardVO mboardVO) throws Exception {
		sqlSession.insert(namespace + ".movieWrite", mboardVO);
	}
	
	// 게시물 목록 조회
	@Override
	public List<MBoardVO> movieListPage(SearchCriteria scri) throws Exception {
		return sqlSession.selectList(namespace + ".movieListPage", scri);
	}
	
	//게시물 총 갯수
	@Override
	public int movieListCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne(namespace + ".movieListCount", scri);
	}
	
	// 게시물 보기
	@Override
	public MBoardVO movieRead(int movie_id) throws Exception {
		return sqlSession.selectOne(namespace + ".movieRead", movie_id);
	}
	
	// 게시물 수정
	@Override
	public void movieUpdate(MBoardVO mboardVO) throws Exception {
		sqlSession.update(namespace + ".movieUpdate", mboardVO);
	}
	
	// 게시물 삭제
	@Override
	public void movieDelete(int movie_id) throws Exception {
		sqlSession.delete(namespace + ".movieDelete", movie_id);
	}
	
	// 게시판 조회수
	@Override
	public void movieBoardHit(int movie_id) throws Exception {
		sqlSession.update(namespace + ".movieBoardHit", movie_id);
	}
	
	//카테고리 조회
	@Override
	public List<CategoryVO> categoryList() throws Exception {
		return sqlSession.selectList(namespace + ".categoryList");
	}
	
	//특정 카테고리 조회
	@Override
	public CategoryVO searchCategoty(CategoryVO categoryVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".searchCategoty", categoryVO);
	}

}
