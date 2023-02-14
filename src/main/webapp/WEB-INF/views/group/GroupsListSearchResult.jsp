<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="utf-8" xmlns:th="http://www.thymeleaf.org">
<head>
  <title>목록 조회 결과창</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div align="center">
<table border="1">
  	<tr><td>GroupId</td><td>GroupName </td><td>Description</td></tr>
  	<tr><td> ${id[0]} </td><td> ${name[0]} </td><td> ${description[0]} </td></tr>
  	<tr><td> ${id[1]} </td><td> ${name[1]} </td><td> ${description[1]} </td></tr>
</table>
    <p><textarea col="50" rows="10"> ${result}</textarea></p>

</div>
</body>
</html>