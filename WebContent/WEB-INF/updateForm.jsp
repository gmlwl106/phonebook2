<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호부</h1>
	<h2>수정폼</h2>
	<p>정보를 수정하려면<br>
		아래 항목을 기입하고 "수정" 버튼을 클릭하세요
	</p>
	<form action="./pbc" method="get">
		이름(name)      <input type="text" name="name" value="${person.name }"> <br>
		핸드폰(hp)        <input type="text" name="hp" value="${person.hp }"> <br>
		회사(company)     <input type="text" name="company" value="${person.company }"> <br>
		<!-- 코드(id)  -->     <input type="text" name="id" value="${person.personId }">
		<input type="hidden" name="action" value="update">
		<br>
		<button type="submit">수정</button>
	</form>
</body>
</html>