<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body class="product-admin-page">
    
    <jsp:include page="header.jsp" />

    <main class="admin-container admin-form-container">
		<header class="product-admin-page">
			<div>
				<span class="admin-eyebrow">PRODUCT MANAGEMENT</span>
				<h1>商品列表</h1>
				<p>管理餐點分類，價格與庫存狀態</p>
			</div>
		</header>
		
		${ products }
		
    </main>

    <jsp:include page="footer.jsp" />
	
	
</body>
</html>
