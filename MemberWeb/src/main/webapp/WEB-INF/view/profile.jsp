<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>會員 profile</title>
	</head>
	
	<jsp:include page="header.jsp" />
	
	<body class="container">
		
		<h2>會員 profile</h2>
		
		<form class="pure-form" method="post" action="/MemberWeb/profile">
			序號：${sessionScope.member.id}<p />
			帳號：${sessionScope.member.username}<p />
			密碼：<input type="password" id="password" name="password" placeholder="請輸入新密碼" />(不修改密碼毋需填寫)<p />
			郵件：<input type="email" id="email" name="email" value="${sessionScope.member.email}" placeholder="請輸入電子郵件信箱" required /><p />
			角色：${sessionScope.member.role}<p />
			建立：${sessionScope.member.createTime}<p /> 
			<button type="submit" class="pure-button pure-button-primary">修改</button>
		</form>
		
	</body> 
	
	<jsp:include page="footer.jsp" />
	
</html>