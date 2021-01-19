<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm</title>
</head>
<body>
		<h1>전화번호 등록</h1>
		<br>
		
		<p>
			전화번호를 등록하려면 아래 항목을 기입하고 <br>
			등록을 누르세요
		</p>
		
		<form action="/phonebook3/phone/insert" method="get">
		
			이름 : <input type="text" name="name"><br>
			핸드폰 번호 : <input type="text" name="hp"><br>
			회사 번호 : <input type="text" name="company"><br>
					
					
			<button type="submit">등록</button>
			
		</form>
		<br>
		<a href= "/phonebook3/phone/list">리스트로 가기</a>
		
</body>
</html>