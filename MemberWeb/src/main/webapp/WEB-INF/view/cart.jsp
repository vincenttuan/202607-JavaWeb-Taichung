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
								${ item.value }
							</td>
							<td>
								${ item.key.price * item.value }
							</td>
							<td>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		
    </main>

    <jsp:include page="footer.jsp" />
	
	
</body>
</html>
