<%--
  Created by IntelliJ IDEA.
  User: freakygeek
  Date: 8/18/18
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h5><fmt:message key="welcome"/>  ${user.lastName} ${user.firstName}</h5>
<%--<form action="/adminPage" method="post" class="push flex-item">
    <input type="hidden" name="command" value="goToPage">
    <input type="hidden" name="url" value="adminpanel.jsp">
    <input class="button" type="submit" value= "<fmt:message key="adminPanel"/>" >--%>
<a class="button" href="/adminPage"><fmt:message key="adminPanel"/></a>
<form action="/Controller" method="post" class="push flex-item">
    <input type="hidden" name="command" value="logout">
    <input class="button" type="submit" value="<fmt:message key="signout"/>">
</form>

