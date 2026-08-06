<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>遊戲首頁</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		
		<form class="pure-form" method="post" action="/GameWeb/game">
			<fieldset>
				<legend>猜拳遊戲</legend>
				<span style="font-size: 50px">👩</span> 
				<input type="text" name="username" value="${sessionScope.username}" placeholder="請輸入名字" required><p />
				${pageContext.session.id}<p />
				<label>
					<input type="radio" name="player" value="2"><span style="font-size: 100px">✌</span>
				</label>
				<label>
					<input type="radio" name="player" value="0"><span style="font-size: 100px">✊</span>
				</label>
				<label>
					<input type="radio" name="player" value="1"><span style="font-size: 100px">🤚</span><p />
				</label>
				<button type="submit" class="pure-button pure-button-primary">猜拳</button>
			</fieldset>
		</form>
		
	</body>
</html>