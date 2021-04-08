<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">영화소개</div>
                <a class="nav-link" href="/movie/M_list">
                    <div class="sb-nav-link-icon"><i class="fas fa-film"></i></div>
                    영화소개
                </a>
                <div class="sb-sidenav-menu-heading">영화리뷰</div>
                <a class="nav-link" href="/board/list">
                    <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                    영화리뷰
                </a>
                <c:if test="${not empty login}">
                <div class="sb-sidenav-menu-heading">마이페이지</div>
                <a class="nav-link" href="/board/myScrap">
                    <div class="sb-nav-link-icon"><i class="fas fa-scroll"></i></div>
                    내 스크랩
                </a>
                </c:if>
            </div>
        </div>
    </nav>
</div>