<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="utf-8" xmlns:th="http://www.thymeleaf.org">
<head>
  <title>목록 조회 결과창</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div align="center">
<table border="1">
    <th>ID</th>
    <th>이름</th>
    <th>부서</th>
    <c:forEach var="item" items="${id}" varStatus="vs">
    <c:if test="${!empty item}">
  	<tr>
  	<td>${item}</td>
  	<td>${name[vs.index]}</td>
  	<td>${description[vs.index]}</td>
  	</tr>
  	</c:if>
    </c:forEach>

</table>
    <p><textarea col="50" rows="10"> ${result}</textarea></p>

</div>
</body>
</html>