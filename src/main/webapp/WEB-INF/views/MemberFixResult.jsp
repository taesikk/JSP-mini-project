<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 수정 결과</title>
</head>
<body>
<div align="center">
<h2>멤버 수정 결과입니다.</h2><hr>
<table border="1">
  	<tr><td>CompanyId</td><td>Name</td><td>Id</td></tr>
  	<tr><td> ${companyId}</td><td>${name}</td><td>${id}</td></tr>
</table>
    <p><textarea col="50" rows="10"> ${result}</textarea></p>
</div>
</body>
</html>