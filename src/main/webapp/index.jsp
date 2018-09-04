<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/myLib.tld" prefix="mytag" %>

<c:if test='${sessionScope.get("lang") == null}'>
    <c:set var="lang" scope="session" value="uk"/>
</c:if>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="library" scope="session"/>


<html>
<head>
    <title>World Discover</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
</head>
<body>
<jsp:include page="WEB-INF/navbar.jsp"/>
<div class="main-content">
    <div class="filter_holder">
        <h2><fmt:message key="searchDreamCruise"/></h2>
    </div>
</div>
<c:set var="shipArray" value="${requestScope.get('shipArray')}"/>
<div class="cruise_holder">
    <mytag:printships/>
</div>
</body>
</html>
