<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>會員中心｜歡樂點餐</title>
</head>
<body class="member-page">
    <jsp:include page="header.jsp" />

    <main class="member-card member-card-profile" aria-labelledby="profile-title">
        <header class="member-profile-heading">
            <span class="member-avatar" aria-hidden="true">M</span>
            <div>
                <p class="member-eyebrow">MY ACCOUNT</p>
                <h1 id="profile-title">會員中心</h1>
                <p class="member-lead">管理電子郵件與登入密碼。</p>
            </div>
        </header>

        <dl class="member-profile-summary">
            <div>
                <dt>會員帳號</dt>
                <dd>${sessionScope.member.username}</dd>
            </div>
            <div>
                <dt>會員角色</dt>
                <dd><span class="member-role">${sessionScope.member.role}</span></dd>
            </div>
            <div>
                <dt>建立時間</dt>
                <dd>${sessionScope.member.createTime}</dd>
            </div>
        </dl>

        <form class="member-form member-form-profile" method="post"
              action="${pageContext.request.contextPath}/profile">
            <label class="member-field" for="id">
                會員序號
                <input class="member-input" type="text" id="id" name="id"
                       value="${sessionScope.member.id}" readonly>
            </label>

            <label class="member-field" for="password">
                更新密碼
                <input class="member-input" type="password" id="password" name="password"
                       placeholder="若不修改密碼，請保持空白" autocomplete="new-password">
                <small class="member-hint">只有輸入新密碼時才會更新。</small>
            </label>

            <label class="member-field" for="email">
                電子郵件
                <input class="member-input" type="email" id="email" name="email"
                       value="${sessionScope.member.email}" placeholder="請輸入電子郵件信箱"
                       autocomplete="email" required>
            </label>

            <button type="submit" class="member-button member-button-primary member-button-block">儲存修改</button>
        </form>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>
