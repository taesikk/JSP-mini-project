<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width">
<title>토큰 발급 결과</title>
</head>

<body>

<div align="center" width="80%">
    <h2>작성 가능한 템플릿 목록 결과입니다</h2>
    <hr>
    <table align="center" border="1">
        <th>form ID</th>
        <th>템플릿 이름</th>
        <th>작성자</th>
        <th>작성자 ID</th>

        <c:forEach var="item" items="${form_id}" varStatus="status">
        <c:if test="${fn:length(item) > 0}">
        <tr>
            <td><input type="button" value="${item}" onclick="location='Document_create.html?' + '${item}'"></td>
            <td>${name[status.index]}</td>
            <td>${createName[status.index]}</td>
            <td>${createId[status.index]}</td>
        <tr>
        </c:if>
        </c:forEach>
    </table>


</div>

</body>
</html>