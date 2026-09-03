<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>     
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品訂購</title>
</head>
<body class="order-page">
    
    <jsp:include page="header.jsp" />
	
    <main class="order-container">
		<header class="page-title">
			<div>
				<h1>商品訂購</h1>
				<p>請選擇您要訂購的餐點</p>
			</div>
		</header>
    </main>
	
	${ products }
	<hr />
	${ categories }
	
    <jsp:include page="footer.jsp" />
	
	
</body>
</html>
