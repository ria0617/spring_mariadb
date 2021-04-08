<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%@ include file="../include/head.jsp" %>

<script type="text/javascript">
// 수정 
function updateClick(){
	var formObj = $("form[name='mreadForm']");
	formObj.attr("action", "/movie/M_updateView");
	formObj.attr("method", "get");
	formObj.submit();				
}
//삭제 
function deleteClick(){
	var formObj = $("form[name='mreadForm']");
	formObj.attr("action", "/movie/M_delete");
	formObj.attr("method", "post");
	formObj.submit();
}
//페이징 목록 유지
function pageListClick(){
	location.href = "/movie/M_list?page=${scri.page}"
	+"&perPageNum=${scri.perPageNum}"
	+"&searchType=${scri.searchType}&keyword=${scri.keyword}";
}
</script>
<style>
.oriImg {width:500px; height:auto;}
.thumbImg{}
</style>
<body class="sb-nav-fixed">
<%@ include file="../include/main_header.jsp" %>
<div id="layoutSidenav">
	<%@ include file="../include/left_column.jsp" %>
	
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid">
				<h1 class="mt-5">영화소개 읽기</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="/movie/M_list">영화소개</a></li>
					<li class="breadcrumb-item active">영화소개 읽기</li>
				</ol>
				<div class="card shadow-lg border-0 rounded-lg">
					<div class="card-body">
						<form name="mreadForm" role="form" method="post" >
							<input id="movie_id" name="movie_id" type="hidden" value="${mread.movie_id}"/>
							<input id="category_id" name="category_id" type="hidden" value="${mread.category_id}"/>
							<input type="hidden" id="page" name="page" value="${scri.page}"> 
							<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
							<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
							<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}">
						</form>
						<div class="form-row py-5">
							<div class="col-md-3 text-align-center">
								<img class="thumbImg"  src="${mread.post_thumbimg}"/>
							</div>
							<div class="col-md-9">
								<div class="form-group font-weight-bold">
									<h3>[${category.category_name}] ${mread.m_title}</h3>
								</div>
								<div class="form-row">
									<div class="col-md-2 col-sm-12">기본정보: 미국, 106분&nbsp;&nbsp;</div>
									<div class="col-md-2 col-sm-12">개봉일: 2016.02.17&nbsp;&nbsp;</div>
									<div class="col-md-2 col-sm-12">등급: 청소년 관람불가&nbsp;&nbsp;</div>
									<div class="col-md-2 col-sm-12">감독: 팀 밀러</div>
								</div>
								<div class="form-row pt-4">
									<div class="col-md-9">${mread.m_content}</div>
								</div>
							</div>
						</div>
						<div class="dropdown-divider"></div>
						<div class="form-group">
							<div class="float-left">
								<c:if test="${login.userId == mread.m_writer}">
								<button class="btn btn-success" type="submit" onclick="updateClick()">수정</button>
								<button class="btn btn-danger" type="submit" onclick="deleteClick()">삭제</button>
								</c:if>
							</div>
							<div class="float-right">
								<button class="btn btn-dark " type="submit" onclick="pageListClick()">목록으로</button>
							</div>
						</div>
					</div>
				</div>
			</div>

		</main>
		<%@ include file="../include/main_footer.jsp" %>
	</div>
</div>

<%@ include file="../include/plugin_js.jsp" %>

</body>
</html>