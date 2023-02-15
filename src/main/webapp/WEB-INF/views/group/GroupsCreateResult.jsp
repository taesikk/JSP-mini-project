<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="utf-8" xmlns:th="http://www.thymeleaf.org">
<head>
  <title>생성 결과창</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div align="center">
<h2>그룹 생성 결과입니다.</h2><hr>
<table border="1">
  	<tr><td>GroupId</td><td>GroupName</td></tr>
  	<tr><td> ${idJsp} </td><td> ${nameJsp} </td></tr>
</table>
    <p><textarea col="50" rows="10"> ${result}</textarea></p>
</div>
</body>
</html>