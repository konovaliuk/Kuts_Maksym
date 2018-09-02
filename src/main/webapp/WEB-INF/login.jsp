<%--
  Created by IntelliJ IDEA.
  User: freakygeek
  Date: 8/6/18
  Time: 1:09 PM
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
    <title><fmt:message key="logining"/></title>
    <link rel="stylesheet" href="../css/loginPage.css">
</head>
<body>
<div class="form_holder">
    <h1><fmt:message key="logining"/> </h1>
    <form action="/login" method="post">
        <input type="hidden" name="command" value="login">
        <input type="text" placeholder="<fmt:message key="username"/>"  name="username" required value="${requestScope.get('UserUsername')}" />
        <input type="password" id="password" placeholder="<fmt:message key="password"/>" name="password" style="border-bottom: 1px solid #0984e3;" required minlength="6"/>
        <c:if test="${requestScope.get('message') != null}">
            <h2><fmt:message key="userNotFound"/></h2>
        </c:if>
        <input class="btn" type="submit" value="<fmt:message key="signin"/>">
    </form>
    <div class="empty"></div>
</div>
</body>
</html>