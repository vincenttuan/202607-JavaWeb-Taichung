<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 接收來自 servlet 的資料
Double h      = (Double)request.getAttribute("h");
Double w      = (Double)request.getAttribute("w");
Double bmi    = (Double)request.getAttribute("bmi");
String result = (String)request.getAttribute("result");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>BMI 計算結果</title>
		<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="0">
	</head>
	<body>
		
		<div>
			<fieldset>
				<legend>BMI 計算結果</legend>
				身高: <%=h %> <p />
				體重: <%=w %> <p />
				BMI: <%=bmi %> <p />
				診斷: <%=result %> <p />
			</fieldset>
		</div>
		
	</body>
</html>