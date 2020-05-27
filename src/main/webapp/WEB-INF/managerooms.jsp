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
        <h1><a href="index.jsp" class="logo">Travel Agency</a></h1>
        <ul class="list-unstyled components mb-5">
            <li class="active">
                <a href="manager"><span class="fa fa-home mr-3"></span> Homepage</a>
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
        <h2 class="mb-4">Hotel name: ${hotel.name}</h2>
        <h2 class="mb-4">Rooms:</h2>
        <c:forEach items="${rooms}" var="room">
            <div class="card w-75 myCard" id="${room.id}" style="margin: 20px">
                <div class="card-body">
                    <h1 class="card-title">${room.type}</h1>
                    <p class="card-text">Capacity: ${room.capacity}</p>
                    <p class="card-text">Wifi: ${room.wifiIncluded ? "Included" : "Not included"}</p>
                    <p class="card-text">Breakfast: ${room.breakfastIncluded ? "Included" : "Not included"}</p>
                    <p class="card-text">Price: ${room.price}</p>
                    <div style="float: right">
                        <a href="/travel_agency_war_exploded/deleteRoom?roomId=${room.id}&hotelId=${hotel.id}"
                           class="btn btn-danger">Delete</a>
                        <button class="btn btn-primary updateRooms">Update</button>
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