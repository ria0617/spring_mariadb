package com.src.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.src.dao.PushDAO;
import com.src.domain.PushVO;

@Service
public class PushServiceImpl implements PushService {
	
	@Inject
	private PushDAO dao;
	
	//추천하기
	@Override
	public void pushIn(PushVO pushVO) throws Exception {
		dao.pushIn(pushVO);
	}
	
	//추천글 확인
	@Override
	public int pushCheck(PushVO pushVO) throws Exception {
		return dao.pushCheck(pushVO);
	}
	
	//총 추천수 
	@Override
	public int totalPush(PushVO pushVO) throws Exception {
		return dao.totalPush(pushVO);
	}
	
	//추천 회수
	@Override
	public void pushOut(PushVO pushVO) throws Exception {
		dao.pushOut(pushVO);
	}
	
	//글 추천 모두 회수 
	@Override
	public void pushAllOut(PushVO pushVO) throws Exception {
		dao.pushAllOut(pushVO);
	}
	
}
