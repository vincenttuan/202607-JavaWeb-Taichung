<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>    
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>會員列表｜歡樂點餐</title>
</head>
<body class="member-page">
    <jsp:include page="header.jsp" />

    <main class="member-card member-card-table" aria-labelledby="member-list-title">
        <header class="member-page-heading">
            <div>
                <p class="member-eyebrow">MEMBER MANAGEMENT</p>
                <h1 id="member-list-title">會員列表</h1>
                <p class="member-lead">管理系統中的會員帳號與權限資料。</p>
            </div>
            <span class="member-admin-badge">ADMIN</span>
        </header>

        <div class="member-table-scroll" tabindex="0" role="region" aria-label="會員資料表，可左右捲動">
            <table class="member-table">
                <caption class="member-visually-hidden">會員帳號清單</caption>
                <thead>
                    <tr>
                        <th scope="col">序號</th>
                        <th scope="col">帳號</th>
                        <th scope="col">電子郵件</th>
                        <th scope="col">角色</th>
                        <th scope="col">建立時間</th>
                        <th scope="col">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="member" items="${members}">
                        <tr>
                            <td><span class="member-table-id">#${member.id}</span></td>
                            <td><strong>${member.username}</strong></td>
                            <td>${member.email}</td>
                            <td><span class="member-table-role">${member.role}</span></td>
                            <td>${member.createTime}</td>
                            <td>
                                <a class="member-button member-button-danger member-button-small"
                                   href="${pageContext.request.contextPath}/delete?id=${member.id}">刪除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty members}">
                        <tr>
                            <td class="member-table-empty" colspan="6">目前沒有會員資料。</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>
