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
	
	<!-- 商品分類 -->
	<c:forEach var="category" items="${ categories }">
		
		<section class="category-section">
			<!-- 分類 -->
			<div class="category-title">
				<h2>${ category[1] }</h2>
				<span>${ category[0] }</span>
			</div>
			
			<!-- 商品圖卡 -->
			<div class="product-grid">
				<c:forEach var="product" items="${ products }">
					
					<c:if test="${ product.category == category[0] }">
						
						<article class="product-card">
							<!-- 商品圖片 -->
							<div class="product-image-box">
								<span class="product-id">
									#${ product.id }
								</span>
								
								<img class="product-image" 
									 src="data:${ product.imageType };base64,${ product.imageBase64 }" >
							</div>
							
							<!-- 商品資訊 -->
							<div class="product-info">
								<!-- 商品名稱 -->
								<h3 class="product-name">
									${ product.name }
								</h3>
								
								<!-- 商品 meta 資料 -->
								<div class="product-meta">
									<div class="product-price">
										$${ product.price }
										<small>元</small>
									</div>
									
									<span class="stock">
										庫存 ${ product.stock }
									</span>
								</div>
								
							</div>
							
							
						</article>
						
					</c:if>
					
				</c:forEach>
			</div>
			
		</section>
		
	</c:forEach>
	
	<jsp:include page="footer.jsp" />
	
	
</body>
</html>
