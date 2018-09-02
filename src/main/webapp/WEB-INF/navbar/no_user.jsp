<%--
  Created by IntelliJ IDEA.
  User: freakygeek
  Date: 8/18/18
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<form action="/login" method="post" class="push flex-item">
    <input class="button" type="submit" value=<fmt:message key="signin"/>>
</form>--%>
<a class="button" href="/register"><fmt:message key="register"/></a>
<a class="button" href="/login"><fmt:message key="signin"/></a>
<%--
<form action="/register" method="post" class="push flex-item">
    <input class="button" type="submit" value=<fmt:message key="register"/>>
</form>
--%>
