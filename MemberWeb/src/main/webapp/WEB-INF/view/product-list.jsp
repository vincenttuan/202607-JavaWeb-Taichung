<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>     
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品列表</title>
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
		
		<div class="admin-card admin-table-card">
			<div class="admin-table-scroll">
				<table class="admin-table">
					<thead>
						<tr>
							<th>編號</th>
							<th>圖片</th>
							<th>名稱</th>
							<th>分類</th>
							<th>價格</th>
							<th>庫存</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${ products }">
							<tr>
								<td class="admin-id">#${ product.id }</td>
								<td><img class="product-thumbnail" src="data:${ product.imageType };base64,${ product.imageBase64 }"></td>
								<td class="admin-product-name">${ product.name }</td>
								<td>
									<span class="admin-category admin-category-${ product.category }">
										${ product.category }
									</span>
								</td>
								<td class="admin-name">$${ product.price }</td>
								<td><span class="admin-stock">${ product.stock }</span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		</div>
		
    </main>

    <jsp:include page="footer.jsp" />
	
	
</body>
</html>
