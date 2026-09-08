<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>     
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>購物車</title>
</head>
<body class="cart-page">
    
    <jsp:include page="header.jsp" />

    <main>
		<h1>購物車</h1>
		${ sessionScope.CART }
    </main>

    <jsp:include page="footer.jsp" />
	
	
</body>
</html>
