<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<%@ include file="../include/head.jsp" %>
<script type="text/javascript">
(document).ready(function(){
	$("#savebutton").on("click", function(){
		//공백문자 방지
		if($("#content").val()==""){
			alert("내용을 입력해주세요.");
			$("#content").focus();
			return false;
		}
		if($("#title").val()==""){
			alert("제목을 입력해주세요"");
			$("#title").focus();
			return false;
		}
	});
})
</script>
<body class="sb-nav-fixed">

<%@ include file="../include/main_header.jsp" %>

<div id="layoutSidenav">
	<%@ include file="../include/left_column.jsp" %>
	
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid">
				<h1 class="mt-5">영화리뷰 글쓰기</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="/board/list">영화리뷰</a></li>
					<li class="breadcrumb-item active">영화리뷰 글쓰기</li>
				</ol>
				
				<div class="card shadow-lg border-0 rounded-lg">
					<div class="card-body">
						<form id="WriteForm" role="form" method="post" action="/board/write">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="small mb-1" >작성자</label>
										${login.userId}
										<input class="form-control py-4" id="writer" name="writer" type="hidden" value="${login.userId}" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<input class="form-control py-4" id="title" name="title" type="text" placeholder="제목을 입력해주세요" />
							</div>
							<div class="form-group">
								<textarea class="form-control py-4"  name="content" id="content" rows="10" cols="100"></textarea>
							</div>
							<div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
									<button class="btn btn-primary"  id="savebutton" type="submit">글 작성하기</button>
									<a class="btn btn-dark "   href="/board/list">목록으로</a>
							</div>
						</form>
					</div>
					<div class="card-footer text-center">
						<div class="small">
							<a href="#">계정이 있으면 로그인 해주세요</a>
						</div>
					</div>
				</div>
			</div>

		</main>
		<%@ include file="../include/main_footer.jsp" %>
	</div>
</div>

<%@ include file="../js/editor_js.jsp" %>
<%@ include file="../include/plugin_js.jsp" %>

</body>
</html>