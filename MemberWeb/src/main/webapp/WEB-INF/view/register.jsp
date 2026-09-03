<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>會員註冊｜歡樂點餐</title>
</head>
<body class="member-page">
    <jsp:include page="header.jsp" />

    <main class="member-card member-card-auth member-card-wide" aria-labelledby="register-title">
        <p class="member-eyebrow">JOIN THE FUN</p>
        <h1 id="register-title">建立會員帳號</h1>
        <p class="member-lead">完成基本資料，開始使用歡樂點餐會員服務。</p>

        <form class="member-form" method="post" action="${pageContext.request.contextPath}/register">
            <label class="member-field" for="username">
                帳號
                <input class="member-input" type="text" id="username" name="username"
                       placeholder="請輸入帳號" autocomplete="username" required>
            </label>

            <label class="member-field" for="password">
                密碼
                <input class="member-input" type="password" id="password" name="password"
                       placeholder="請輸入密碼" autocomplete="new-password" required>
            </label>

            <label class="member-field" for="email">
                電子郵件
                <input class="member-input" type="email" id="email" name="email"
                       placeholder="請輸入電子郵件信箱" autocomplete="email" required>
            </label>

            <fieldset class="member-fieldset">
                <legend>會員角色</legend>
                <div class="member-choice-grid">
                    <label class="member-choice" for="roleUser">
                        <input type="radio" id="roleUser" name="role" value="USER" checked>
                        <strong>一般會員</strong>
                        <small>使用會員與點餐服務</small>
                    </label>
                    <label class="member-choice" for="roleAdmin">
                        <input type="radio" id="roleAdmin" name="role" value="ADMIN">
                        <strong>管理者</strong>
                        <small>可查看會員管理清單</small>
                    </label>
                </div>
            </fieldset>

            <div class="member-form-actions">
                <button type="reset" class="member-button member-button-secondary">重置</button>
                <button type="submit" class="member-button member-button-primary">註冊</button>
            </div>
        </form>

        <p class="member-card-switch">
            已經有帳號？
            <a href="${pageContext.request.contextPath}/login">返回登入</a>
        </p>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>
