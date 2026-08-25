<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>會員列表</title>
	</head>
	
	<jsp:include page="header.jsp" />
	
	<body class="container">
		
		<h2>會員列表</h2>
		
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>username</th>
					<th>email</th>
					<th>role</th>
					<th>create time</th>
					<th>delete</th>
				</tr>
				
				
			</thead>
			<tbody></tbody>
		</table>
		
	</body> 
	
	<jsp:include page="footer.jsp" />
	
</html>