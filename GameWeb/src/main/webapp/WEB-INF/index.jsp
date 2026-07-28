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
		
		<form method="post" action="/GameWeb/game">
			<fieldset>
				<legend>猜拳遊戲</legend>
				使用者: <input type="text" name="username" value="Tom" placeholder="請輸入名字" required><p />
				猜拳: <input type="radio" name="player" value="0">石頭
					 <input type="radio" name="player" value="1">布
					 <input type="radio" name="player" value="2">剪刀<p />
				<button type="submit">猜拳</button>
			</fieldset>
		</form>
		
		
	</body>
</html>