

<%--[addBook.jsp] 新規書籍の検索（GoogleAPI）画面--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規書籍の検索</title>
<link rel="stylesheet" type="text/css" href="/YourShelf/css/addBook.css">
<link rel="icon" type="image/png" sizes="32x32" href="img/favicon-32x32.png">
</head>
<body>
<div class="addbook">
<p class="booksearch">新規書籍の検索</p>
<form action="/YourShelf/AddBookServlet?value=search" method="post">
<input type="text" name="isbn" placeholder="ISBN" class="search-isbn"><br>
<input type="text" name="title" placeholder="検索キーワード" class="search-keyword"><br>
<input type="submit" value="検索" class="search-submit">
<input type="reset" value="リセット" class="reset-submit"><br>
</form>
<a class="backtomain" href="/YourShelf/ControllerServlet?value=loginpage">担当者メニューに戻る</a>
<p class="footerCopy">Copyright YourShelf All Rights Reservsed.</p>
</div>
<div class="underlay-photo"></div>
<div class="underlay-black"></div>
</body>

</html>