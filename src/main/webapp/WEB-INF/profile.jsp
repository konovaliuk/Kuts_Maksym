<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="myLib.tld" prefix="mytag" %>

<c:if test='${sessionScope.get("lang") == null}'>
    <c:set var="lang" scope="session" value="uk"/>
</c:if>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="library" scope="session"/>
<html>
<head>
    <title><fmt:message key="profile"/></title>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <link rel="stylesheet" type="text/css" href="../css/profile.css"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="main_holder">
    <div class="left_side">
        <div class="element"><h4>Login</h4><input type="text" value="${user.username}" disabled></div>
        <div class="element"><h4>Email</h4><input type="text" value="${user.email}" disabled></div>
        <div class="element"><h4>First Name</h4><input type="text" value="${user.firstName}" disabled></div>
        <div class="element"><h4>Last Name</h4><input type="text" value="${user.lastName}" disabled></div>
    </div>
    <div class="right_side">
        <%--<div class="ticket">
            Ticket # <h14>673467</h14>
            <br>
            Date start trip: <h14>01.02.2018</h14>
            <br>
            Date end trip: <h14>01.02.2018</h14>
            <br>
            Type : <h14>vip</h14>
            <br>
            Additional Services:<h14>ad 1, ad2, ad3</h14>
            <br>
            Excursion: <h14>ex1, ex2, ex3, ex4, ex5</h14>
        </div>--%>
      <%--  <mytag:printTicket ticketId="41"/>
        <mytag:printTicket ticketId="42"/>
        <mytag:printTicket ticketId="43"/>--%>

        <c:forEach items="${requestScope.get('tickets')}" var="ticket">
            <mytag:printTicket ticketId="${ticket.id}"/>
        </c:forEach>



    </div>
</div>
</body>
</html>
