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
		if($("#m_content").val()==""){
			alert("내용을 입력해주세요.");
			$("#m_content").focus();
			return false;
		}
		if($("#m_title").val()==""){
			alert("제목을 입력해주세요"");
			$("#m_title").focus();
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
				<h1 class="mt-5">영화소개 글쓰기</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="/movie/M_list">영화소개</a></li>
					<li class="breadcrumb-item active">영화소개 글쓰기</li>
				</ol>
				
				<div class="card shadow-lg border-0 rounded-lg">
					<div class="card-body">
						<form id="WriteForm" role="form" method="post" action="/movie/M_write" enctype="multipart/form-data">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="small mb-1" >작성자</label>
										${login.userId}
										<input class="form-control py-4" id="m_writer" name="m_writer" type="hidden" value="${login.userId}" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="small mb-1" >영화장르</label>
								<select id="category_id" name="category_id" >
									<c:forEach items="${category}" var = "category">
										<option value="${category.category_id}">${category.category_name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label class="small mb-1" >제목</label>
								<input class="form-control py-4" id="m_title" name="m_title" type="text" placeholder="제목을 입력해주세요" />
							</div>
							<div class="form-group">
								<label class="small mb-1" >내용</label>
								<textarea class="form-control py-4"  name="m_content" id="m_content" rows="10" cols="100"></textarea>
							</div>
							<!-- 이미지 첨부파일 시작 -->
							<div class="form-group">
								<div class="inputArea">
									<label class="small mb-1" for="postImg">포스터이미지</label><br/>
									<input class="py-2" type="file" id="postImg" name="file" />
									<div class="select_img"><img src="" /></div>
									<!--%=request.getRealPath("/") %-->
								</div>
							</div>
							<!-- 이미지 첨부파일 끝 -->
							<div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
									<button id="savebutton"  class="btn btn-primary"  type="submit">글 작성하기</button>
									<a class="btn btn-dark"  href="/movie/M_list">목록으로</a>
							</div>
						</form>
					</div>
				</div>
			</div>

		</main>
		<%@ include file="../include/main_footer.jsp" %>
	</div>
</div>
<%@ include file="../js/editor_js_movie.jsp" %>
<%@ include file="../include/plugin_js.jsp" %>

</body>
</html>