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
			
		<form class="admin-card admin-product-form" 
			method="post" 
			enctype="multipart/form-data" 
			action="${pageContext.request.contextPath}/products?action=${ formAction }">

			<input type="hidden" name="id" value="${ product.id }" />

			<div class="admin-form-grid">
				<label class="admin-field admin-field-wide" for="name">
					<span>商品名稱</span>
					<input id="name" name="name" type="text" maxlength="100" required value="${ product.name }" placeholder="請輸入商品名稱">
				</label>

				<label class="admin-field" for="category">
					<span>商品分類</span>
					<select id="category" name="category" required>
						<option value="BURGER" ${ product.category == 'BURGER' ? 'selected' : '' }>BURGER 漢堡</option>
						<option value="SNACK"  ${ product.category == 'SNACK' ? 'selected' : ''  }>SNACK 小點</option>
						<option value="DRINK"  ${ product.category == 'DRINK' ? 'selected' : ''  }>DRINK 飲品</option>
					</select>
				</label>

				<label class="admin-field" for="price">
					<span>商品價格</span>
					<div class="admin-input-prefix">
						<span>$</span>
						<input type="number" id="price" name="price" min="0" step="1" required value="${ product.price }">
					</div>
				</label>

				<label class="admin-field" for="stock">
					<span>商品庫存</span>
					<input type="number" id="stock" name="stock" min="0" step="1" required value="${ empty product.stock ? 10 : product.stock }">
				</label>

				<label class="admin-field admin-field-wide" for="imageFile">
					<span>商品圖片</span>
					<input class="admin-file-input" type="file" id="imageFile" name="imageFile" accept="image/png,image/jpeg">
					<small>支援 JPG、PNG，檔案上限 2 MB。</small>
				</label>
			</div>
			
			<div class="admin-image-preview-box">
				<span>圖片預覽</span>
				<img id="imagePreview"
					 class="image-preview ${empty product.imageBase64 ? 'image-preview-hidden' : ''}"
					 src="data:${ product.imageType };base64,${product.imageBase64}"
					 alt="${ product.name }">
				<p class="image-preview-hint ${not empty product.imageBase64 ? 'image-preview-hidden' : ''}">選擇圖片後會顯示在這裡</p>
			</div>

			<div class="admin-form-actions">
				<a href="/McdonaldOrder/products?action=list" class="admin-button admin-button-secondary">取消</a>
				<button type="submit" class="admin-button admin-button-primary">${ formTitle }商品</button>
			</div>
		</form>
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>
