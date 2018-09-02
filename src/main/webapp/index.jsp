<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/myLib.tld" prefix="mytag"%>

<c:if test='${sessionScope.get("lang") == null}'>
    <c:set var="lang" scope="session" value="uk"/>
</c:if>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="library" scope="session"/>


<html>
<head>
    <title>World Discover</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" >
</head>
<body>
<jsp:include page="WEB-INF/navbar.jsp"/>
<div class="main-content">
    <div class="filter_holder">
        <h2><fmt:message key="searchDreamCruise"/></h2>
        <div class="filter">
            <form>
                <div class="departure_select h100">
                    <select>
                        <option id="1" >Select Departuere Port</option>
                        <option id="1">Port 2</option>
                        <option id="1">Port 3</option>
                        <option id="1">Port 4</option>
                        <option id="1">Port 5</option>
                        <option id="1">Port 6</option>
                    </select>
                </div>
                <div class="destination_select flex-item h100">
                    <select>
                        <option id="1" >Select Departuere Port</option>
                        <option id="1">Port 2</option>
                        <option id="1">Port 3</option>
                        <option id="1">Port 4</option>
                        <option id="1">Port 5</option>
                        <option id="1">Port 6</option>
                    </select></div>
                <input class="flex-item h100" type="text" placeholder="start price" pattern="^[ 0-9]+$">
                <input class="flex-item h100" type="text" placeholder="end price" pattern="^[ 0-9]+$">
                <input class="filter_submit flex-item h100" type="submit" value="search">
            </form>
        </div>
    </div>
</div>
<c:set var="shipArray" value="${requestScope.get('shipArray')}"/>
<div class="cruise_holder">
<mytag:printships/>
</div>
</body>
</html>
