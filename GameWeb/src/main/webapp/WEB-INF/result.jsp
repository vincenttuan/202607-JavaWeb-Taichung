<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>遊戲統計結果</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		
		<div class="pure-form">
			<fieldset>
				<legend>遊戲統計結果</legend>
				玩家: <%=request.getAttribute("username") %> <p />
				本局結果: <%=request.getAttribute("currentRecord") %> <p />
				累積勝場: <%=request.getAttribute("playerWins") %> <p /> 
				累積敗場: <%=request.getAttribute("serverWins") %> <p />
				累積平手: <%=request.getAttribute("draws") %> <p />
				累積勝率: <%=request.getAttribute("winRate") %> <p />
				歷史交手紀錄
				
			</fieldset>
		
		</div>
		
	</body>
</html>