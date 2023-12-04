<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user info</title>
	</head>
	<body>
		<h1>사용자 조회</h1>
		<form action="UserServlet" method="post">
			<label for="searchId">사용자id</label>
			<input type="text" id="userId" name="userId" placeholder="아이디 검색" />
			<button type="submit">검색</button>
		</form>
	</body>
</html>