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
		
		<div class="cart">
			<table>
				<thead>
					<tr>
						<th>商品</th>
						<th>名稱</th>
						<th>單價</th>
						<th>數量</th>
						<th>小計</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<!-- 初始化總計金額 -->
					<c:set var="totalPrice" value="0" />
					
					<c:forEach var="item" items="${ sessionScope.CART }">
						<tr>
							<td>
								<img width="100" src="data:${ item.key.imageType };base64,${ item.key.imageBase64}">
							</td>
							<td>
								${ item.key.name }
							</td>
							<td>
								${ item.key.price }
							</td>
							<td>
								<form method="post" action="${pageContext.request.contextPath}/order">
									
									<input type="hidden" name="action" value="update">
									<input type="hidden" name="productId" value="${ item.key.id }">
									
									<input  type="number" 
											class="quantity" 
											name="quantity" 
											value="${ item.value }"
											min="1"
											max="${ item.key.stock }"
											onkeydown="return false"
											onwheel="return false"
											ondrop="return false"
											oninput="this.form.submit()"/>
									<button type="submit">更新</button>			
									
								</form>
								
								
							</td>
							<td>
								${ item.key.price * item.value }
							</td>
							<td>
								<form method="post" action="${pageContext.request.contextPath}/order">
									
									<input type="hidden" name="action" value="remove">
									<input type="hidden" name="productId" value="${ item.key.id }">
									
									<button type="submit">移除</button>			
									
								</form>
							</td>
						</tr>
						<!-- 累計總計金額 -->
						<c:set var="totalPrice" value="${ totalPrice + (item.key.price * item.value) }" />
					</c:forEach>
				</tbody>
			</table>
			
			<!-- 總計 -->
			<div class="summary">
				<span>總計</span>
				<span>$${ totalPrice }</span>
			</div>
			
		</div>
		
		<!-- 前往結帳 -->
		<div class="actions">
			<a class="button" href="${pageContext.request.contextPath}/order">繼續選購</a>
			<a class="button checkout" href="${pageContext.request.contextPath}/order?action=checkout">前往結帳</a>
		</div>
		
    </main>

    <jsp:include page="footer.jsp" />
	
	
</body>
</html>
