<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 삭제 결과</title>
</head>
<body>
<div align="center">
<h2>멤버 삭제 결과입니다.</h2>
<hr>
<table border="1">
  	<tr><td>Code</td><td>Message</td><td>Status</td></tr>
  	<tr><td>${code}</td><td>${message}</td><td>${status}</td></tr>
</table>
    <p><textarea col="50" rows="10"> ${result}</textarea></p>
</div>
</body>
</html>