<%@ page import="model.entity.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">

<style>
    .header {
        background: #1f2937;
        color: white;
        padding: 12px 20px;
    }

    .header a {
        color: white;
        margin-right: 15px;
        text-decoration: none;
        font-weight: bold;
    }

    .header a:hover {
        text-decoration: underline;
    }

    .container {
        width: 900px;
        margin: 20px auto;
    }
</style>

<div class="header">
	<a href="/MemberWeb/login">登入</a>
	<a href="/MemberWeb/register">註冊</a>
	<a href="/MemberWeb/logout">登出</a>
	
</div>
