<%--
  Created by IntelliJ IDEA.
  User: freakygeek
  Date: 8/18/18
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<nav>
    <div class="logo">
        <a href="index.jsp"><h1>World Discover</h1></a>
    </div>
    <c:choose>
        <c:when test="${user.userRole == 'user'}">
            <jsp:include page="navbar/user.jsp"/>
        </c:when>
        <c:when test="${user.userRole == 'admin'}">
            <jsp:include page="navbar/admin.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="navbar/no_user.jsp"/>
        </c:otherwise>
    </c:choose>
    <ul class="languagepicker">
        <c:if test="${lang == 'uk'}">
        <a href="/?command=changeLanguage&language=uk"><li><img src="../images/uk.png"/>Українська</li></a>
        <a href="/?command=changeLanguage&language=us"><li><img src="../images/us.png"/>English</li></a>
        </c:if>
        <c:if test="${lang == 'us'}">
            <a href="/?command=changeLanguage&language=us"><li><img src="../images/us.png"/>English</li></a>
            <a href="/?command=changeLanguage&language=uk"><li><img src="../images/uk.png"/>Українська</li></a>
        </c:if>
    </ul>

</nav>
