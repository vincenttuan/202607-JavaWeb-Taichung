<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${ formTitle }商品</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body class="product-admin-page">
    
    <jsp:include page="header.jsp" />

    <main class="admin-container admin-form-container">
		<header class="product-admin-page">
			<div>
				<span class="admin-eyebrow">PRODUCT MANAGEMENT</span>
				<h1>${ formTitle }商品</h1>
				<p>填寫商品資料並確認庫存與售價</p>
			</div>
		</header>
			
		<form>
			
		</form>
        
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>
