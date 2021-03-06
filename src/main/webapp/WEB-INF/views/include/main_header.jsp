<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
	<a class="navbar-brand" href="/">Spring Project</a>
	<button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#">
		<i class="fas fa-bars"></i>
	</button>
	<!-- Navbar Search-->
	<form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
		
	</form>
	<!-- Navbar-->
	<ul class="navbar-nav ml-auto ml-md-0">
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<i class="fas fa-user fa-fw"></i>
			</a>
			<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
			<c:if test="${not empty login}">
				<span class="dropdown-item"><strong>${login.userId} 접속중</strong></span>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item"  href="/member/userModifyView">회원정보수정</a>
				<a class="dropdown-item"  href="/member/logout">로그아웃</a>
				<a class="dropdown-item"  href="/member/userBlockView">회원탈퇴</a>
			</c:if>
			<c:if test="${empty login}">
				<a class="dropdown-item"  href="/member/login">로그인</a>
				<a class="dropdown-item"  href="/member/register">회원가입</a>
			</c:if>
			</div>
		</li>
	</ul>
</nav>