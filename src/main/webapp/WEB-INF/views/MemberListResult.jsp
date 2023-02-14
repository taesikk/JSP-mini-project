<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <tr><td>Name</td><td>Id</td><td>Department</td><td>Position</td></tr>
            <tr><td>${username[0]}</td><td><center>${result[0]}</td><td>${userDepartment[0]}</td><td>${userPosition[0]}</td></tr>
            <tr><td>${username[1]}</td><td><center>${result[1]}</td><td>${userDepartment[1]}</td><td>${userPosition[1]}</td></tr>
            <tr><td>${username[2]}</td><td align="right">${result[2]}</td></tr>
            <tr><td>${username[3]}</td><td align="right">${result[3]}</td></tr>
            <tr><td>${username[4]}</td><td align="right">${result[4]}</td></tr>
            <tr><td>${username[5]}</td><td align="right">${result[5]}</td></tr>
            <tr><td>${username[6]}</td><td align="right">${result[6]}</td></tr>
            <tr><td>${username[7]}</td><td align="right">${result[7]}</td></tr>
            <tr><td>${username[8]}</td><td align="right">${result[8]}</td></tr>
            <tr><td>${username[9]}</td><td align="right">${result[9]}</td></tr>
            <tr><td>${username[10]}</td><td align="right">${result[10]}</td></tr>
    </table>
    <p><textarea col="500" rows="10">응답 json : ${json}</textarea></p>

</div>

</body>
</html>