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
    <link href="${pageContext.request.contextPath}/css/addhotel.css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
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
            <li>
                <a href="manager"><span class="fa fa-home mr-3"></span> Homepage</a>
            </li>
            <li class="active">
                <a href="addhotel"><span class="fa fa-hotel mr-3"></span>Add hotels and rooms</a>
            </li>
            <li>
                <a href="statistic"><span class="fa fa-sticky-note mr-3"></span> Statistics</a>
            </li>
        </ul>

    </nav>
    <div id="content" class="p-4 p-md-5 pt-5">
        <form method="post" id="myForm">
            <h2 class="mb-4" id="${'hotel' + hotel.id}">Update Hotel</h2>
            <div class="form-group">
                <label for="hotelName">Hotel name</label>
                <input type="text" class="form-control hotelName" id="hotelName" aria-describedby="hotelName"
                       placeholder="Enter a hotel name" name="hotelName" value="${hotel.name}">
            </div>
            <div class="rating">
                Rating: &#160;
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="rating" id="inlineRadio1"
                           value="1" ${('inlineRadio' + hotel.rating) == id ? 'checked' : ''}>
                    <label class="form-check-label" for="inlineRadio1">1</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="rating" id="inlineRadio2"
                           value="2" ${('inlineRadio' + hotel.rating) == id ? 'checked' : ''}>
                    <label class="form-check-label" for="inlineRadio2">2</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="rating" id="inlineRadio3"
                           value="3" ${('inlineRadio' + hotel.rating) == id ? 'checked' : ''}>
                    <label class="form-check-label" for="inlineRadio3">3</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="rating" id="inlineRadio4"
                           value="4" ${('inlineRadio' + hotel.rating) == id ? 'checked' : ''}>
                    <label class="form-check-label" for="inlineRadio4">4</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="rating" id="inlineRadio5"
                           value="5" ${('inlineRadio' + hotel.rating) == id ? 'checked' : ''}>
                    <label class="form-check-label" for="inlineRadio5">5</label>
                </div>
            </div>
            <div class="form-group">
                <label for="country">Country</label>
                <input type="text" class="form-control country" id="country" name="country" aria-describedby="country"
                       placeholder="Enter a country" value="${hotel.country}">
            </div>
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" class="form-control city" name="city" id="city" aria-describedby="city"
                       placeholder="Enter a city" value="${hotel.city}">
            </div>
            <button type="submit" class="btn btn-primary btn-lg updatehotel" id="saveHotel">Save</button>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main1.js"></script>
<script src="${pageContext.request.contextPath}/js/updatehotel.js"></script>
</body>
</html>