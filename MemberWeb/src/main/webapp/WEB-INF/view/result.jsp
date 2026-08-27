<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}" />｜歡樂點餐</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body class="member-page">
    <jsp:include page="header.jsp" />

    <c:set var="resultIsError"
           value="${fn:contains(message, '失敗') or fn:contains(message, '不足') or fn:contains(message, '錯誤')}" />

    <main class="member-card member-result-card" aria-labelledby="result-title">
        <c:choose>
            <c:when test="${resultIsError}">
                <span class="member-result-icon member-result-icon-error" aria-hidden="true">!</span>
            </c:when>
            <c:otherwise>
                <span class="member-result-icon" aria-hidden="true">✓</span>
            </c:otherwise>
        </c:choose>

        <p class="member-eyebrow">MEMBER SERVICE</p>
        <h1 id="result-title"><c:out value="${title}" /></h1>
        <p class="member-result-message"><c:out value="${message}" /></p>

        <c:choose>
            <c:when test="${not empty sessionScope.member}">
                <a class="member-button member-button-primary"
                   href="${pageContext.request.contextPath}/profile">返回會員中心</a>
            </c:when>
            <c:otherwise>
                <a class="member-button member-button-primary"
                   href="${pageContext.request.contextPath}/login">返回登入</a>
            </c:otherwise>
        </c:choose>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>
