<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<%@ include file="include/head.jsp" %>

<body class="sb-nav-fixed sb-sidenav-toggled">

<%@ include file="include/main_header.jsp" %>

<div id="layoutSidenav">
	<%@ include file="include/left_column.jsp" %>
	
	<div id="layoutSidenav_content" class="main_bg">
		<main>
			<div id="mc_wrap">
			<div class="m_content">메인 페이지</div>
			</div>
		</main>
		<%@ include file="include/main_footer.jsp" %>
	</div>
</div>

<%@ include file="include/plugin_js.jsp" %>

</body>
</html>
