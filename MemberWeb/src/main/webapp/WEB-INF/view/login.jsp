<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>會員登入 Login</title>
	</head>
	
	<jsp:include page="header.jsp" />
	
	<body class="container">
		
		<h2>會員登入</h2>
		
		<form class="pure-form" method="post" action="/MemberWeb/login">
			帳號：<input type="text" id="username" name="username" placeholder="請輸入帳號" required /><p />
			密碼：<input type="password" id="password" name="password" placeholder="請輸入密碼" required /><p />
			
			<input type="number" id="code" name="code" placeholder="請輸入認證碼" />
			<img id="codeImage" 
				 src="/MemberWeb/code" 
				 valign="middle" 
				 alt="看到我表示目前沒有認證碼" 
				 title="點擊更新認證碼"
				 onmousemove="this.src='/MemberWeb/code?t='+new Date().getTime()"
				 style="cursor: pointer;"><p />
			
			<button type="submit" class="pure-button pure-button-primary">登入</button>
		</form>
		
	</body>
	
	<jsp:include page="footer.jsp" />
	
	<script>
		window.addEventListener("pageshow", function(e) {
			document.getElementById("codeImage").src="/MemberWeb/code?t=" + new Date().getTime();
		});
	</script>
	
	
</html>