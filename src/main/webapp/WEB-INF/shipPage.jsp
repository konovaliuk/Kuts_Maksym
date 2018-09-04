<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: freakygeek
  Date: 8/21/18
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test='${sessionScope.get("lang") == null}'>
    <c:set var="lang" scope="session" value="uk"/>
</c:if>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="library" scope="session"/>
<html>
<head>
    <title>${requestScope.get('ship').title}</title>
    <link rel="stylesheet" type="text/css" href="../css/shipPage.css">
    <link rel="stylesheet" type="text/css" href="../css/buy.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".fade").hide();
            $(".buy_ticket").hide();
            $("#buy_button").prop('disabled', true);

            var radioChecked = false;

            var CheckValidation = function () {
                if (radioChecked && $('#count').val() > 0) {
                    $('#buy_button').prop('disabled', false);
                } else {
                    $('#buy_button').prop('disabled', true);
                }
            };

            $("#buy").on('click', function () {
                $(".fade").fadeIn();
                $(".buy_ticket").fadeIn();
            });
            $(".fade").on('click', function () {
                $(".fade").fadeOut();
                $(".buy_ticket").fadeOut();
            });

            $('input[type=radio][name=ticketType]').on('change', function () {
                radioChecked = true;
                CheckValidation();
            });
            $('#count').on('change', function () {
                if ($('#count').val() < 0) {
                    $('#count').val(0);
                }else {
                    CheckValidation();
                }
            });

            $('#buy_button').on('click',function () {
                var ticketTypeId = $('input[type=radio][name=ticketType]:checked').val();
                var countOfTickets = $('#count').val();
                var excursionArray = JSON.stringify($("input:checkbox[name=excursionOption]:checked").map(function(){return $(this).val()}).get());
                $.ajax({
                    type:'post',
                    data:{
                        command:'buyTicketCommand',
                        shipId:'${requestScope.get('ship').id}',
                        ticketTypeId:ticketTypeId,
                        count:countOfTickets,
                        excursionArray:excursionArray
                    },
                    url:'Controller',
                    success:function (data) {
                        alert(data);
                        window.location.replace("/profile");
                    }
                });
            });

        });
    </script>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div style="height: 16%;width: 100%;"></div>
<div class="main-content">

    <div class="cruise_title">
        ${requestScope.get('ship').title}
    </div>
    <div class="information">
        <div class="left_side">
            <div class="image">
                <img src="images/cruise_${requestScope.get('ship').id}.jpg"/>
            </div>
            <div class="buy_button">
                <c:choose>
                    <c:when test="${user.userRole == 'admin'}"></c:when>
                    <c:when test="${user.userRole == 'user'}">
                        <input id="buy" type="submit" value="<fmt:message key="buyTicket"/> ">
                    </c:when>
                    <c:otherwise>
                        <form action="/login">
                            <input type="submit" value="<fmt:message key="buyTicket"/> ">
                        </form>
                    </c:otherwise>

                </c:choose>

            </div>
        </div>
        <div class="right_side">
            <div class="des_title"><fmt:message key="description"/></div>
            <div class="des_holder">
                <div class="element">
                    <fmt:message key="humanCapacity"/> - ${requestScope.get('ship').humanCapacity}
                </div>
                <div class="element">
                    <fmt:message key="startDate"/> - ${requestScope.get('ship').startDate}
                </div>
                <div class="element">
                    <fmt:message key="endDate"/> - ${requestScope.get('ship').endDate}
                </div>
                <div class="element">
                    <fmt:message key="price"/> - ${requestScope.get('ship').price}
                </div>

            </div>
        </div>
    </div>
</div>
<div class="fade"></div>
<div class="buy_ticket">
    <div class="radio_holder">
        <%--  <input type="radio" name="ticketType">Simple
          <br>
          cinema,hsdfohf,sdgsdgjsdg,dhsdgjiogds
          <br>
          <input type="radio" name="ticketType">
          Vip
          <br>
          cinema,hsdfohf,sdgsdgjsdg,dhsdgjiogds
          <br>
          <input type="radio" name="ticketType">Vip+
          <br>
          cinema,hsdfohf,sdgsdgjsdg,dhsdgjiogds
          <br>--%>
        <c:forEach items="${requestScope.get('ticketTypeCount')}" var="item">
            <input type="radio" name="ticketType"
                   value="${item.key.id}"> "${item.key.ticketType}" - ${item.value}<br>
            <c:forEach items="${requestScope.get('ticketTypeAdditionalServices')[item.key.id]}" var="innerItem">
                ${innerItem.title},
            </c:forEach>
            <br>
        </c:forEach>
    </div>
    <div class="count">
        <input id="count" type="number" value="0">
    </div>
    <div class="excursion_list">
        <div class="select">
            <c:forEach items="${requestScope.get('excursionList')}" var="excursion">
                <input type="checkbox" name="excursionOption" value="${excursion.id}">${excursion.title} - ${excursion.price}<br>
            </c:forEach>
        </div>
    </div>
    <div class="button_holder">
        <button id="buy_button">Buy</button>
    </div>

</div>
</body>
</html>
