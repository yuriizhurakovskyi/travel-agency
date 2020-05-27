<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <title>Hotel Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/result.css">
</head>
<body>
<c:choose>
    <c:when test="${userLogged=='yes'}">
        <jsp:include page="authheader.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp"/>
    </c:otherwise>
</c:choose>

<section class="home">
</section>
<div class="myDiv" style="margin-top: 90px">
    <c:forEach items="${rooms}" var="room">
        <div class="card w-75 myCard" id="${room.id}" style="margin: 20px">
            <div class="card-body">
                <h1 class="card-title">${room.type}</h1>
                <p class="card-text">Capacity: ${room.capacity}</p>
                <p class="card-text">Wifi: ${room.wifiIncluded ? "Included" : "Not included"}</p>
                <p class="card-text">Breakfast: ${room.breakfastIncluded ? "Included" : "Not included"}</p>
                <p class="card-text">Price: ${room.price}</p>
                <a href="/travel_agency_war_exploded/reserveRoom?id=${room.id}&startDate=${startDate}&endDate=${endDate}"
                   class="btn btn-primary myButton">Reserve</a>
            </div>
        </div>
    </c:forEach>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<jsp:include page="footer.jsp"/>
</body>
</html>
