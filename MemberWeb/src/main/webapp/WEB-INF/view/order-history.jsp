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
    			
    		</article>
    	</c:forEach>
    	
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>
