<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>Manager page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>

<div class="wrapper d-flex align-items-stretch">
    <nav id="sidebar">
        <div class="custom-menu">
            <button type="button" id="sidebarCollapse" class="btn btn-primary">
                <i class="fa fa-bars"></i>
                <span class="sr-only">Toggle Menu</span>
            </button>
        </div>
        <h1><a href="index" class="logo">Travel Agency</a></h1>
        <ul class="list-unstyled components mb-5">
            <li class="active">
                <a href="#"><span class="fa fa-home mr-3"></span> Homepage</a>
            </li>
            <li>
                <a href="addhotel"><span class="fa fa-hotel mr-3"></span>Add hotels and rooms</a>
            </li>
            <li>
                <a href="statistic"><span class="fa fa-sticky-note mr-3"></span> Statistics</a>
            </li>
        </ul>

    </nav>
    <div id="content" class="p-4 p-md-5 pt-5">
        <h2 class="mb-4">Hotels:</h2>
        <c:forEach items="${hotels}" var="hotel">
            <div class="card w-75 myCard hotelId" id="${hotel.id}">
                <div class="card-body">
                    <h1 class="card-title">Name: ${hotel.name}</h1>
                    <p class="card-text">Rating: ${hotel.rating}/5</p>
                    <p class="card-text">Country: ${hotel.country}</p>
                    <p class="card-text">City: ${hotel.city}</p>
                    <p class="card-text">Count of rooms: ${hotel.room_count}</p>
                    <a href="/travel_agency_war_exploded/managerooms?hotelId=${hotel.id}"
                       class="btn btn-primary">Rooms</a>
                    <a href="/travel_agency_war_exploded/addroom?hotelId=${hotel.id}" class="btn btn-primary">Add
                        Rooms</a>
                    <div style="float: right">
                        <a href="/travel_agency_war_exploded/deleteHotel?hotelId=${hotel.id}" class="btn btn-danger">Delete</a>
                        <a href="/travel_agency_war_exploded/updateHotel?hotelId=${hotel.id}"
                           class="btn btn-primary updateRooms">Update</a>
                    </div>
                </div>

            </div>
        </c:forEach>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main1.js"></script>
</body>
</html>