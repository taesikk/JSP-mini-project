<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>멤버 목록 조회 결과</title>
</head>
<body>

<div align="center">
    <h2>멤버 목록 조회 결과입니다</h2>
    <h3>(최대 10명의 정보만 출력됩니다.)</h3><hr>
    <table align="center" border="1">
            <th>이름</th>
            <th>ID</th>
            <th>그룹</th>
            <th>직책</th>
            <c:forEach var="item" items="${username}" varStatus="status">
            <c:if test="${fn:length(item) > 0}">
            <tr>
                <td>${item}</td>
                <td>${result[status.index]}</td>
                <td>${userDepartment[status.index]}</td>
                <td>${userPosition[status.index]}</td>
            </tr>
            </c:if>
            </c:forEach>
    </table>
    <p><textarea col="500" rows="10">응답 json : ${json}</textarea></p>

</div>

</body>
</html>