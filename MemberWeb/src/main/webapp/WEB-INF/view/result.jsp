<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${ title }</title>
	</head>
	
	<jsp:include page="header.jsp" />
	
	<body class="container">
		
		<h2>${ title }</h2>
		
		<div class="pure-form">
			${ message }	
		</div>
		
	</body>
	
	<jsp:include page="footer.jsp" />
	
</html>