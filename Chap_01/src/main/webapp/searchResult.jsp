<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.model.dao.DAO" %>
<%@ page import = "com.kh.model.vo.DTO" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>search Result</title>
	</head>
	<body>
		<h1>검색 결과</h1>
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>나이</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<DTO> userList = (List<DTO>)request.getAttribute("userList");
					
					for(DTO d : userList){
				%>
						<tr>
							<td><%= d.getUser_number() %></td>
							<td><%= d.getUser_id() %></td>
							<td><%= d.getUser_name() %></td>
							<td><%= d.getUser_age() %></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</body>
</html>