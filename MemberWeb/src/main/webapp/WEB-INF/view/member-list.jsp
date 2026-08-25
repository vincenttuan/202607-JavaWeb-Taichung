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
				<!-- 資料迴圈列表 -->
				<c:forEach var="member" items="${members}">
					<tr>
						<td>${member.id}</td>
						<td>${member.username}</td>
						<td>${member.email}</td>
						<td>${member.role}</td>
						<td>${member.createTime}</td>
						<td>
							<a href="/MemberWeb/delete?id=${member.id}" class="pure-button">Delete</a>
						</td>
					</tr>
				</c:forEach>
				
			</thead>
			<tbody></tbody>
		</table>
		
	</body> 
	
	<jsp:include page="footer.jsp" />
	
</html>