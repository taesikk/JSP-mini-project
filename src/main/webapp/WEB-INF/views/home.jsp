<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>eformsign 시작하기</title>
</head>
<script language="javaScript">
    console.log("test");
    var acc = "${accessToken}";
    console.log(acc);
    var ref = "${refreshToken}";
    var userId = "${userId}";
    var APIKey = "${APIKey}";
    var secret = "${secret}";
    window.localStorage.setItem("accessToken",acc);
    window.localStorage.setItem("refreshToken",ref);
    window.localStorage.setItem("userId", userId);
    window.localStorage.setItem("APIKey", APIKey);
    window.localStorage.setItem("secret", secret);

</script>
<frameset cols="20%,*">
    <frame src="Frame1.html"></frame>
    <frame src="Frame2.html" name="right"></frame>
</frameset>
<body>


</body>
</html>