<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width">
<title>토큰 발급 결과</title>
</head>
<script language="javaScript">
        console.log("test");
        var acc = "${accessToken}";
        console.log(acc);
        var ref = "${refreshToken}";
        window.localStorage.setItem("accessToken",acc);
        window.localStorage.setItem("refreshToken",ref);
    </script>
<body>

<div align="center" width="80%">
    <h2>토큰발급 결과입니다</h2>
    <hr>
    <table align="center" width="50%">
            <tr><td>Access Token</td><td><textarea col="500" rows="10">${accessToken}</textarea></td></tr>
            <tr><td>Refresh Token</td><td><textarea col="500" rows="10">${refreshToken}</textarea></td></tr>
    </table>


</div>

</body>
</html>