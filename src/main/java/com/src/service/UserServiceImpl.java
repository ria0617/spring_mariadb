package com.src.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.src.controller.MemberController;
import com.src.dao.UserDAO;
import com.src.domain.LoginDTO;
import com.src.domain.UserVO;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final UserDAO userDAO;
	
	@Inject 
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	//회원 가입 처리
	@Override
	public void register(UserVO userVO) throws Exception {
		userDAO.register(userVO);
	}
	
	//로그인 처리
	@Override
	public UserVO login(LoginDTO loginDTO)  throws Exception {
		return userDAO.login(loginDTO);
	}
	
	//회원 수정
	//컨트롤러에서 보내는 파라미터들을 userUpdate(UserVO userVO)로 받는다
	@Override
	public void userUpdate(UserVO userVO) throws Exception {
		//받은 userVO를 DAO로 보내준다
		userDAO.userUpdate(userVO);
	}
	
	//아이디 중복체크 
	@Override
	public int idChk(String userId)  throws Exception {
		int result = userDAO.idChk(userId);
		return result;
	}
	
	//회원탈퇴 표기 
	@Override
	public void userBlock(UserVO userVO) throws Exception {
		userDAO.userBlock(userVO);
	}
	
	//Xid 로그인 막기
	@Override
	public int Xid(String userId)  throws Exception {
		int result =userDAO.Xid(userId);
		return result;
	}
	
	//아이디 찾기
	@Override
	public String findId(String email)  throws Exception {
		String result =userDAO.findId(email);
		return result;
	}
	
	//이메일중복체크
	@Override
	public int findEmail(String email)  throws Exception {
		int result = userDAO.findEmail(email);
		return result;
	}
	
	//비밀번호 변경 updatePw
	@Override
	public void updatePw(UserVO userVO) throws Exception {
		userDAO.updatePw(userVO);
	}
	
	//랭크 조회
	@Override
	public int rankChk(String userId) throws Exception {
		int result = userDAO.rankChk(userId);
		return result;
	}

}
