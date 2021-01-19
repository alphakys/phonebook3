<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm</title>
</head>
<body>

	<h1>정보 수정하기</h1>


		<form action="/phonebook3/phone/update" method="get">
		
			이름 : <input type="text" name="name" value="${updatePerson.name }"><br>
			핸드폰 번호 : <input type="text" name="hp" value="${updatePerson.hp }"><br>
			회사 번호 : <input type="text" name="company" value="${updatePerson.company }"><br>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="id" value="${param.id }">
			<button type="submit">수정</button>
			
		</form>
		<br>
		<a href= "/phonebook3/phone/list">리스트로 가기</a>
</body>
</html>