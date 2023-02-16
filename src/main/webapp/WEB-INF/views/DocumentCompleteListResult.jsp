<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width">
<title>완료 문서 목록 조회 결과</title>
</head>

<body>

<div align="center" width="80%">
    <h2>완료 문서 목록 조회 결과입니다.</h2>
    <hr>
    <table align="center" border="1">
        <th>doc ID</th>
        <th>문서 이름</th>
        <th>작성자</th>
        <th>작성자 ID</th>
        <th>삭제하기</th>

        <c:forEach var="item" items="${doc_id}" varStatus="status">
        <c:if test="${!empty item}">
        <tr>
            <td>${doc_id[status.index]}</td>
            <td>${doc_name[status.index]}</td>
            <td>${create_name[status.index]}</td>
            <td>${create_id[status.index]}</td>
            <td><input type="button" value="삭제하기" onclick="location.href='Document_delete.html?' + '${item}'"</td>
        <tr>
        </c:if>
        </c:forEach>
    </table>


</div>

</body>
</html>