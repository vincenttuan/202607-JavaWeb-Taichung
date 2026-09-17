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
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>
