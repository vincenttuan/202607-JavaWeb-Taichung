<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>會員註冊 Register</title>
	</head>
	
	<jsp:include page="header.jsp" />
	
	<body class="container">
		
		<h2>會員註冊</h2>
		
		<form class="pure-form" method="post" action="/MemberWeb/register">
			帳號：<input type="text"     id="username" name="username" placeholder="請輸入帳號" required /><p />
			密碼：<input type="password" id="password" name="password" placeholder="請輸入密碼" required /><p />
			郵件：<input type="email"    id="email"    name="email"    placeholder="請輸入電子郵件信箱" required /><p />
			角色：<input type="radio"    id="role"     name="role"     value="USER" checked /> User(使用者)
				<input type="radio"    id="role"     name="role"     value="ADMIN" /> Admin(管理者)<p />
			
			<button type="submit" class="pure-button pure-button-primary">註冊</button>
			<button type="reset"  class="pure-button">重置</button>
		</form>
		
	</body>
	
	<jsp:include page="footer.jsp" />
	
</html>