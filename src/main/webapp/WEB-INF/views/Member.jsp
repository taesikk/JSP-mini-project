<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Member</title>
</head>
<body>
<div align="center">
  <h3>Member와 관련된 메뉴입니다</h3><hr>
    <button onclick="location.href='MemberList.jsp'" style="padding:40px 40px">구성원 목록 조회</button><br><br>
    <button onclick="location.href='MemberFix.html'" style="padding:50px 50px">구성원 수정</button><br><br>
    <button onclick="location.href='MemberDelete.html'" style="padding:50px 50px">구성원 삭제</button><br><br>
</div>
</body>
</html>