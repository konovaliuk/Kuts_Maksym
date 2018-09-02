<%--
  Created by IntelliJ IDEA.
  User: freakygeek
  Date: 8/6/18
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test='${sessionScope.get("lang") == null}'>
    <c:set var="lang" scope="session" value="uk"/>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="library" scope="session"/>
</c:if>


<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="../css/registryStyle.css">
    <script type="text/javascript" language="JavaScript">
        window.onload = function () {
            document.getElementById("password").onchange = validatePassword;
            document.getElementById("repeatPassword").onchange = validatePassword;
        };
        function validatePassword(){
            var pass2 = document.getElementById("password").value;
            var pass1 = document.getElementById("repeatPassword").value;
            if(pass1!=pass2)
                document.getElementById("repeatPassword").setCustomValidity("<fmt:message key="passValidation"/>");
            else
                document.getElementById("repeatPassword").setCustomValidity('');
        }
    </script>
</head>
<body>
<body>
<div class="form_holder">
    <h1><fmt:message key="registration"/></h1>
    <form action="/register" method="post">
        <input type="hidden" name="command" value="register">
        <input type="text" placeholder="<fmt:message key="username"/> "  name="username" required value="${requestScope.get("username")}" />
        <input type="email" placeholder="<fmt:message key="email"/>"  name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required value="${requestScope.get("userEmail")}"/>
        <input type="password" id="password" placeholder="<fmt:message key="password"/>" name="password" required minlength="6"/>
        <input type="password" id="repeatPassword" placeholder="<fmt:message key="repeatPassword"/>" name="repeatPassword" required minlength="6"/>
        <input type="text" placeholder="<fmt:message key="firstName"/>"  name="firstName" required value="${requestScope.get("firstName")}"/>
        <input type="text" placeholder="<fmt:message key="lastName"/> "  style="border-bottom: 1px solid #0984e3;" name="lastName" required value="${requestScope.get("lastName")}" />
        <c:if test="${requestScope.get('message') != null}">
            <fmt:message key="${requestScope.get('message')}"/>
        </c:if>
        <input class="btn" type="submit" value="<fmt:message key="signUp"/> ">
    </form>
    <div class="empty"></div>
</div>
</body>
</body>
</html>
