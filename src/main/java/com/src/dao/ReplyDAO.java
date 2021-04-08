package com.src.dao;

import java.util.List;

import com.src.domain.ReplyVO;

public interface ReplyDAO {
	
	//댓글조회
	public List<ReplyVO> readReply(int bno) throws Exception;
	
	//댓글 작성
	public void writeReply(ReplyVO replyVO) throws Exception;
	
	//댓글 수정
	public void updateReply(ReplyVO replyVO) throws Exception;
	
	//댓글 삭제
	public void deleteReply(ReplyVO replyVO) throws Exception;
	
	//선택 댓글 조회
	public ReplyVO selectReply(int rno) throws Exception;
	
}
