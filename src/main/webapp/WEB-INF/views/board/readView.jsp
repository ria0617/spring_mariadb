<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%@ include file="../include/head.jsp" %>
<style>
.hide{display:none !important;}
</style>
<script type="text/javascript">
// 수정 
function updateClick(){
	var formObj = $("form[name='readForm']");
	formObj.attr("action", "/board/updateView");
	formObj.attr("method", "get");
	formObj.submit();				
}
//삭제 
function deleteClick(){
	var formObj = $("form[name='readForm']");
	formObj.attr("action", "/board/delete");
	formObj.attr("method", "post");
	formObj.submit();
}
//페이징 목록 유지
function pageListClick(){
	location.href = "/board/list?page=${scri.page}"
	+"&perPageNum=${scri.perPageNum}"
	+"&searchType=${scri.searchType}&keyword=${scri.keyword}";
}
//댓글 작성
function replyWriteBtn(){
	var formObj = $("form[name='replyForm']");
	formObj.attr("action", "/board/replyWrite");
	formObj.submit();
}
//댓글 수정
function replyUpdateClick(rno){
	var myRno = 'replyFormMy' + rno;
	var formObj = $("form[name="+myRno+"]");
	formObj.attr("action", "/board/replyUpdate");
	formObj.attr("method", "post");
	formObj.submit();
}
//댓글 삭제
function replyDeleteClick(rno){
	var myRno = 'replyFormMy' + rno;
	var formObj = $("form[name="+myRno+"]");
	formObj.attr("action", "/board/replyDelete");
	formObj.attr("method", "post");
	formObj.submit();
}
//추천하기
function pushClick(){
	var formObj = $("form[name='pushForm']");
	formObj.attr("action", "/board/pushIn");
	formObj.attr("method", "post");
	formObj.submit();
}
//추천회수
function pushOutClick(){
	var formObj = $("form[name='pushForm']");
	formObj.attr("action", "/board/pushOut");
	formObj.attr("method", "post");
	formObj.submit();
}
//스크랩
function scrapIn(){
	var scrapUrl = '/board/readView?bno=${read.bno}';
	$('input[name=scrapAd]').attr('value',scrapUrl);
	var formObj = $("form[name='scrapForm']");
	formObj.attr("action", "/board/scrapIn");
	formObj.attr("method", "post");
	formObj.submit();
}

//스크랩 확인 문구
var msg = "${msg}";
if (msg === "scrapOk") {
alert ("스크랩되었습니다");
}
</script>

<body class="sb-nav-fixed">
<%@ include file="../include/main_header.jsp" %>
<div id="layoutSidenav">
	<%@ include file="../include/left_column.jsp" %>
	
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid">
				<h1 class="mt-5">영화리뷰 읽기</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="/board/list">영화리뷰</a></li>
					<li class="breadcrumb-item active">영화리뷰 읽기</li>
				</ol>
				<div class="card shadow-lg border-0 rounded-lg">
					<div class="card-body">
						<form name="readForm" role="form" method="post" >
							<input id="bno" name="bno" type="hidden" value="${read.bno}"/>
							<input type="hidden" id="page" name="page" value="${scri.page}"> 
							<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
							<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
							<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}">
						</form>
						<form name="pushForm" role="form" method="post" >
							<input id="bno" name="bno" type="hidden" value="${read.bno}"/>
							<input type="hidden" id="userId" name="userId" value="${login.userId}">
							<input type="hidden" id="page" name="page" value="${scri.page}"> 
							<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
							<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
							<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}">
						</form>
						<form name="scrapForm" role="form" method="post" >
							<input id="bno" name="bno" type="hidden" value="${read.bno}"/>
							<input type="hidden" id="scrapTitle" name="scrapTitle" value="${read.title}"/>
							<input type="hidden" id="scrapAd" name="scrapAd" value=""/>
							<input type="hidden" id="userId" name="userId" value="${login.userId}">
							<input type="hidden" id="page" name="page" value="${scri.page}"> 
							<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
							<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
							<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}">
						</form>
						<div class="form-group">
							<label class="small mb-1" for="bno">글 번호</label>
							${read.bno}&nbsp;&nbsp;|&nbsp;&nbsp;
							<label class="small mb-1" for="writer">작성자</label>
							<input class="form-control py-4" id="writer" name="writer" type="hidden" value="${read.writer}"/>
							${read.writer}&nbsp;&nbsp;|&nbsp;&nbsp;
							<label class="small mb-1"  for="regdate">작성일</label>
							<fmt:formatDate value="${read.regdate}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<label class="small mb-1"  for="totalpush">총 추천수</label>
							${push} 
						</div>
						<div class="form-group font-weight-bold">
							<label class="middle mb-1 " for="title">제목: </label>
							<input class="form-control py-4" id="title" name="title" type="hidden" value="${read.title}"/>
							${read.title}
						</div>
						<div class="form-group py-4">
							${read.content}
						</div>
						<div class="dropdown-divider"></div>
						<div class="form-group align-items-center justify-content-between mt-4 mb-0">
							<c:if test="${not empty login}">
							<div class="float-left">
								<c:if test="${login.userId == read.writer}">
								<button class="btn btn-success" type="submit" onclick="updateClick()">수정</button>
								<button class="btn btn-danger" type="submit" onclick="deleteClick()">삭제</button>
								</c:if>
							</div>
							</c:if>
							<div class="float-right">
									<c:if test="${not empty login}">
									<button class="btn btn-primary " type="submit" onclick="scrapIn()">스크랩</button>
									<c:if test="${pushCheck == 0}">
									<button class="btn btn-info " type="submit" onclick="pushClick()">추천하기</button>
									</c:if>
									<c:if test="${pushCheck == 1}">
									<button class="btn btn-primary " type="submit" onclick="pushOutClick()">추천회수</button>
									</c:if>
									</c:if>
								<button class="btn btn-dark " type="submit" onclick="pageListClick()">목록으로</button>
							</div>
						</div>
					</div>
					<!-- 댓글 기능 전체 -->
					<div class="card-footer text-left">
						<c:if test="${not empty login}">
						<h4 class="mb-2">댓글 목록</h4>
						<!-- 댓글 쓰기 -->
						<form name="replyForm" method="post">
							<input type="hidden" id="bno" name="bno" value="${read.bno}" />
							<input type="hidden" id="page" name="page" value="${scri.page}">
							<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}">
							<input type="hidden"id="searchType" name="searchType" value="${scri.searchType}">
							<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}">
							<div class="form-group row">
								<div class="col-md-11">
									<input class="form-control py-4" type="hidden" id="writer" name="writer" value="${login.userId}"/>
									<textarea  class="form-control py-3"  id="content" name="content" ></textarea>
								</div>
								<div class="col-md-1 align-items-center justify-content-between">
									<button type="button" class="btn btn-secondary btn-block py-4" onclick="replyWriteBtn()">댓글 작성</button>
								</div>
							</div>
						</form>
						</c:if>
						<!-- 댓글 쓰기 끝 -->
						<!-- 댓글 보기-->
						<div id="reply">
							<ol class="replyList">
								<c:forEach items="${replyList}" var="replyList">
									<li class="py-4">
										<div>
											작성자 : ${replyList.writer}&nbsp;&nbsp;
											작성 날짜 : <fmt:formatDate value="${replyList.regdate}" pattern="yyyy-MM-dd" />
										</div>
										<div class="" id="reply_content">${replyList.content}</div>
										<!-- 댓글 수정 삭제 -->
										<c:if test="${login.userId == replyList.writer}">
											<form name="replyFormMy${replyList.rno}" role="form" method="post" >
												<input id="bno" name="bno" type="hidden" value="${read.bno}"/>
												<input id="rno" name="rno" type="hidden" value="${replyList.rno}"/>
												<input id="writer" name="writer" type="hidden" value="${replyList.writer}"/>
												<script type="text/javascript">
													//댓글 버튼제어
													function replyUpdateBtn${replyList.rno}(rno){
														var replyRno = ${replyList.rno};
														var myRno = rno;
														if(replyRno==myRno){
															document.getElementById('reContent${replyList.rno}').classList.remove('hide');
															document.getElementById('replyUpdateClick${replyList.rno}').classList.remove('hide');
															document.getElementById('replyUpdateBtn${replyList.rno}').classList.add('hide');
														}else{
														}
													}
												</script>
												<div class="hide" id="reContent${replyList.rno}">
													<textarea class="form-control py-4" id="content" name="content" >${replyList.content}</textarea>
												</div>
											</form>
										<div class="form-group align-items-center justify-content-between mt-4 mb-0">
											<button type="button" class="btn btn-success"  id="replyUpdateBtn${replyList.rno}"  onclick="replyUpdateBtn${replyList.rno}(${replyList.rno})" data-rno="${replyList.rno}">수정</button>
											<button type="button" class="btn btn-success hide"  id="replyUpdateClick${replyList.rno}"  onclick="replyUpdateClick(${replyList.rno})" data-rno="${replyList.rno}">저장</button>
											<button type="button" class="btn btn-danger"  onclick="replyDeleteClick(${replyList.rno})" data-rno="${replyList.rno}">삭제</button>
										</div>
										</c:if>
										<!-- 댓글 수정 삭제 끝 -->
									</li>
								</c:forEach>
							</ol>
						</div>
						<!-- 댓글 보기 끝 -->
					</div>
					<!-- 댓글 전체 끝 -->
				</div>
			</div>

		</main>
		<%@ include file="../include/main_footer.jsp" %>
	</div>
</div>

<%@ include file="../include/plugin_js.jsp" %>

</body>
</html>