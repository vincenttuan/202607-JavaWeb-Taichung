<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 核心庫 -->    
<%@ taglib uri="jakarta.tags.core" prefix="c" %> 
    
<%!
	int x = 10; // 物件變數
	class Ball {
	
	
	}
	
	
	String getGameName(int player) {
		/*
		// Java 14 以前的寫法
		switch(player) {
			case 0: return "石頭";
			case 1: return "布";
			case 2: return "剪刀";
			default:
				return "";
		}
		*/
		// Java 14(含) 以後的寫法
		String name = switch(player) {
			case 0 -> "石頭";
			case 1 -> "布";
			case 2 -> "剪刀";
			default -> "";
		};
		return name;
	}

%>    
<%-- 我是註解 --%>
<%
	int x = 10; // 區域變數
	if (x % 2 == 0) {
		// ...
	}
%>
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
				玩家: ${username} <p />
				本局結果: ${currentRecord} <p />
				<ul>
					<li>玩家: ${currentRecord.player}</li>
					<li>電腦: ${currentRecord.server}</li>
					<li>結果: ${currentRecord.result}</li>
				</ul>
				累積勝場: ${playerWins} <p /> 
				累積敗場: ${serverWins} <p />
				累積平手: ${draws} <p />
				累積勝率: ${winRate} <p />
				歷史交手紀錄
				
			</fieldset>
		
		</div>
		
	</body>
</html>