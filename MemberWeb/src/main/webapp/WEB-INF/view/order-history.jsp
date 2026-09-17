<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
    
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>歷史訂單資料</title>
</head>
<body class="history-page">
    <jsp:include page="header.jsp" />

    <main>
    	${ orders } <p />
    	${ orderCount }
    	<hr />
    	
    	<div class="page-header">
    		<h1>歷史訂單資料</h1>
    		<p>訂單筆數：${ orderCount } 筆</p>
    	</div>
    	
    	<c:forEach var="order" items="${ orders }">
    		<article class="order-card">
    			<!-- 訂單主檔 -->
    			<div class="order-header">
    				<div>
    					<span class="label">訂單編號</span>
    					<strong>#${ order.orderId }</strong>
    				</div>
    				<div>
    					<span class="label">客戶名稱</span>
    					${ order.customerName }
    				</div>
    				<div>
    					<span class="label">客戶 Email</span>
    					${ order.customerEmail }
    				</div>
    				<div>
    					<span class="label">結帳時間</span>
    					${ order.createdAt }
    				</div>
    				<div>
    					<span class="label">總金額</span>
    					${ order.totalAmount }
    				</div>
    			</div>
    			
    			<!-- 訂單明細查詢 -->
    			<table>
    				<thead>
    					<tr>
    						<th>商品</th><th>單價</th><th>數量</th><th>小計</th>
    					</tr>
    				</thead>
    				<tbody>
    					<c:forEach var="item" items="${ order.items }">
    						<tr>
    							<td>${ item.productName }</td>
    							<td>${ item.unitPrice }</td>
    							<td>${ item.quantity }</td>
    							<td>${ item.subtotal }</td>
    						</tr>
    					</c:forEach>
    				</tbody>
    			</table>
    			
    		</article>
    	</c:forEach>
    	
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>
