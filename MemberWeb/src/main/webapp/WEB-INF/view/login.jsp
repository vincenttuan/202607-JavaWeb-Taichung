<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>會員登入｜歡樂點餐</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body class="member-page">
    <jsp:include page="header.jsp" />

    <main class="member-card member-card-auth" aria-labelledby="login-title">
        <p class="member-eyebrow">WELCOME BACK</p>
        <h1 id="login-title">會員登入</h1>
        <p class="member-lead">登入會員帳號，繼續使用歡樂點餐服務。</p>

        <form class="member-form" method="post" action="${pageContext.request.contextPath}/login">
            <label class="member-field" for="username">
                帳號
                <input class="member-input" type="text" id="username" name="username"
                       placeholder="請輸入帳號" autocomplete="username" required>
            </label>

            <label class="member-field" for="password">
                密碼
                <input class="member-input" type="password" id="password" name="password"
                       placeholder="請輸入密碼" autocomplete="current-password" required>
            </label>

            <div class="member-field">
                <label for="code">圖形認證碼</label>
                <div class="member-captcha-row">
                    <input class="member-input" type="number" id="code" name="code"
                           placeholder="輸入四位數字" inputmode="numeric">
                    <button type="button" class="member-captcha-button" id="refreshCodeButton"
                            title="點擊更新認證碼" aria-label="更新圖形認證碼">
                        <img class="member-captcha" id="codeImage"
                             src="${pageContext.request.contextPath}/code"
                             alt="四位數圖形認證碼">
                    </button>
                </div>
                <small class="member-hint">看不清楚時，請點擊認證碼圖片更新。</small>
            </div>

            <button type="submit" class="member-button member-button-primary member-button-block">登入</button>
        </form>

        <p class="member-card-switch">
            還沒有帳號？
            <a href="${pageContext.request.contextPath}/register">立即註冊</a>
        </p>
    </main>

    <jsp:include page="footer.jsp" />

    <script>
        (() => {
            const codeImage = document.getElementById('codeImage');
            const refreshButton = document.getElementById('refreshCodeButton');
            const codeUrl = '${pageContext.request.contextPath}/code';

            function refreshCodeImage() {
                if (codeImage) codeImage.src = codeUrl + '?t=' + Date.now();
            }

            if (refreshButton) refreshButton.addEventListener('click', refreshCodeImage);
            window.addEventListener('pageshow', refreshCodeImage);
        })();
    </script>
</body>
</html>
