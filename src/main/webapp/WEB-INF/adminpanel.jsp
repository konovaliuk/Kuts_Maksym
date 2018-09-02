<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: freakygeek
  Date: 8/22/18
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <title><fmt:message key="adminPanel"/></title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/admin.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".message").hide();
            $(".save_but").prop('disabled',true);

            var selectedShipId;
            var selectedData;

            var showMessage= function (message) {
                $(".message").html(message);
                $(".message").fadeIn().delay(2000).fadeOut();
            };
            var clearCheckboxes = function(){
              $(".checkbox").each(function () {
                  $(this).prop('checked',false);
              });
            };

            $('select').on('change', function() {
                clearCheckboxes();

                $("#ship_none").prop('disabled',true);
                selectedShipId = this.value;

                if(this.value == "ship_none"){
                    $(".save_but").prop('disabled',true);
                }else{
                    $(".save_but").prop('disabled',false);
                }
                $.ajax({
                    type: 'post',
                    data:{
                        command:'ticketTypeHasServiceCommand',
                        ship_id:this.value,
                    },
                    url:'Controller',
                    success:function (data) {
                        var result = JSON.parse(data);
                        selectedData = data;
                        for(var i=0;i<Object.keys(result).length;i++){
                            for(var j = 0;j<result[Object.keys(result)[i]].length;j++){
                                $("input[value="+Object.keys(result)[i]+"-"+result[Object.keys(result)[i]][j]+"]").prop('checked',true);
                            }
                        }
                    }
                });
            });

            $(".save_but").on('click', function () {
                var stringJSON = "{";
                var prevRow = -1;
                $(".checkbox").each(function () {

                    var index = $(this).attr('value');
                    var row = index.split('-')[0];
                    var column = index.split('-')[1];
                    if(prevRow == -1){
                        prevRow = row;
                        stringJSON += '\"'+row+'\":[';
                    }
                    if($(this).prop('checked') == true) {
                        if(prevRow != row){
                            stringJSON = stringJSON.substring(0,stringJSON.length-1);
                            stringJSON+='],\"'+row+'\":[';
                            prevRow = row;
                        }
                        stringJSON+=column+',';
                    }


                });
                stringJSON = stringJSON.substring(0,stringJSON.length-1);
                stringJSON +=']}';
                stringJSON.trim();
                var fileJSON = JSON.parse(stringJSON);
                console.log(selectedData);
                console.log(stringJSON);
                if(selectedData == stringJSON){
                    showMessage("Змін не виявлено")
                }else {
                    $.ajax({
                        type: 'post',
                        data: {
                            command: 'updateTypeHasService',
                            ship_id: selectedShipId,
                            json: stringJSON
                        },
                        url: 'Controller',
                        success: function (data) {
                            showMessage("<fmt:message key="success"/>");
                            selectedData = stringJSON;
                        }
                    });
                }
            });
        });
    </script>
</head>

<jsp:include page="navbar.jsp"/>

<body>
<div class="main-content">
    <div class="left">
        <div class="ship_title">
            <fmt:message key="chooseShip"/>
        </div>
        <select id="ships">
            <option id="ship_none" value="ship_none"><fmt:message key="chooseShip"/></option>
            <c:forEach items="${requestScope.get('ships')}" var="ship">
                <option value="${ship.id}">${ship.title}</option>
            </c:forEach>
        </select>
    </div>
    <div class="right">
        <table>
            <th>Ticket Type</th>
            <c:forEach items="${requestScope.get('services')}" var="service">
                <th id="${service.id}" class="rotate">${service.title}</th>
            </c:forEach>

            <c:forEach items="${requestScope.get('ticketTypes')}" var="type">

                <tr>
                    <td>${type.ticketType}</td>
                    <c:forEach items="${requestScope.get('services')}" var="service">
                        <td><input class="checkbox" value="${type.id}-${service.id}" type="checkbox"></td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>

        <button class="save_but"><fmt:message key="save"/></button>
    </div>
</div>
<div class="message">
</div>
</body>
</html>
