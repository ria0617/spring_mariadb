package com.src.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.src.dao.MBoardDAO;
import com.src.domain.BoardVO;
import com.src.domain.CategoryVO;
import com.src.domain.MBoardVO;
import com.src.domain.SearchCriteria;

@Service
public class MBoardServiceImpl implements MBoardService {
	
	@Inject
	private MBoardDAO dao;
	
	//게시글작성
	@Override
	public void movieWrite(MBoardVO mboardVO) throws Exception {
		dao.movieWrite(mboardVO);
	}
	
	// 게시물 목록 조회
	@Override
	public List<MBoardVO> movieListPage(SearchCriteria scri) throws Exception {
		return dao.movieListPage(scri);
	}
	
	//게시물 총 갯수
	@Override
	public int movieListCount(SearchCriteria scri) throws Exception {
		return dao.movieListCount(scri);
	}
	
	// 게시물 보기
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public MBoardVO movieRead(int movie_id) throws Exception {
		dao.movieBoardHit(movie_id);
		return dao.movieRead(movie_id);
	}
	
	//게시물 수정
	@Override
	public void movieUpdate(MBoardVO mboardVO) throws Exception {
		dao.movieUpdate(mboardVO);
	}
	
	//게시물 삭제
	@Override
	public void movieDelete(int movie_id) throws Exception {
		dao.movieDelete(movie_id);
	}
	
	//카테고리 조회
	@Override
	public List<CategoryVO> categoryList() throws Exception {
		return dao.categoryList();
	}

	//특정 카테고리조회
	@Override
	public CategoryVO searchCategoty(CategoryVO categoryVO) throws Exception {
		return dao.searchCategoty(categoryVO);
	}

}
