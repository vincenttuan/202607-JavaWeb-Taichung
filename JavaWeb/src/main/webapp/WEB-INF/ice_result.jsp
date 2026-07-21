<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// 接收來自 servlet 的資料
	String iceName      = (String)request.getAttribute("iceName");
	String size         = (String)request.getAttribute("size");
	String sweet        = (String)request.getAttribute("sweet");
	String ice          = (String)request.getAttribute("ice");
	String toppings     = (String)request.getAttribute("toppings");
	String bowlColor    = (String)request.getAttribute("bowlColor");
	String qty          = (String)request.getAttribute("qty");
	String pickupDate   = (String)request.getAttribute("pickupDate");
	String birthday     = (String)request.getAttribute("birthday");
	String memo         = (String)request.getAttribute("memo");
	String qrCodeBase64 = (String)request.getAttribute("qrCodeBase64");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>胖胖冰果店訂單</title>
		<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="0">
	</head>

	<body>

		<h1>胖胖冰果店訂單</h1>

		冰品：<%=iceName %><br>
		大小：<%=size %><br>
		甜度：<%=sweet %><br>
		冰量：<%=ice %><br>
		配料：<%=toppings %><br>
		碗色：<input type="color" name="bowlColor" value="<%=bowlColor %>"><br>
		數量：<%=qty %><br>
		取餐日期：<%=pickupDate %><br>
		壽星：<%=birthday %><br>
		備註：<%=memo %>

		<p>

		<h3>訂單 QRCode</h3>

		<img src="<%=qrCodeBase64 %>" width="250">

	</body>

</html>