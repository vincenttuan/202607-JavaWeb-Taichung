<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<header class="member-header">
    <a class="member-brand" href="${contextPath}/login" aria-label="歡樂點餐會員服務首頁">
        <span class="member-brand-mark" aria-hidden="true">M</span>
        <span class="member-brand-text">
            <strong>歡樂點餐</strong>
            <small>會員服務</small>
        </span>
    </a>

    <nav class="member-nav" aria-label="會員導覽">
        <c:if test="${not empty sessionScope.member}">
            <span class="member-nav-account">
                <span class="member-nav-greeting">Hi，<c:out value="${sessionScope.member.username}" /></span>
                <span class="member-role"><c:out value="${sessionScope.member.role}" /></span>
            </span>
            <a class="member-nav-link" href="${contextPath}/profile">會員中心</a>

            <c:if test="${sessionScope.member.role == 'ADMIN'}">
                <a class="member-nav-link" href="${contextPath}/list">會員列表</a>
            </c:if>

            <a class="member-nav-link member-nav-link-accent" href="${contextPath}/logout">登出</a>
        </c:if>

        <c:if test="${empty sessionScope.member}">
            <a class="member-nav-link" href="${contextPath}/login">登入</a>
            <a class="member-nav-link member-nav-link-accent" href="${contextPath}/register">註冊</a>
        </c:if>
    </nav>
</header>
