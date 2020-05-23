<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Index page</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/result.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500" rel="stylesheet"/>
    <link href="css/searchform.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>

<section class="home">
</section>

<div style="height: 300px">
    <div class="s002"> <!-- This form templates was made by Colorlib (https://colorlib.com) -->
        <form class="myForm" method="post" action="hotelByCityAndDate">
            <fieldset>
                <legend>SEARCH HOTEL</legend>
            </fieldset>
            <div class="inner-form">
                <div class="input-field first-wrap">
                    <div class="icon-wrap">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
                        </svg>
                    </div>
                    <input id="search" type="text" placeholder="City" name="city"/>
                </div>
                <div class="input-field second-wrap">
                    <div class="icon-wrap">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                        </svg>
                    </div>
                    <input class="datepicker" id="depart" type="text" placeholder="29 Aug 2018" name="startDate"/>
                </div>
                <div class="input-field third-wrap">
                    <div class="icon-wrap">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                        </svg>
                    </div>
                    <input class="datepicker" id="return" type="text" placeholder="30 Aug 2018" name="endDate"/>
                </div>
                <div class="input-field fouth-wrap">
                    <div class="icon-wrap">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"></path>
                        </svg>
                    </div>
                    <select data-trigger="" name="choices-single-defaul">
                        <option placeholder="">1 Person</option>
                        <option>2 People</option>
                        <option>3 People</option>
                        <option>4 People</option>
                        <option>5 People</option>
                    </select>
                </div>
                <div class="input-field fifth-wrap">
                    <button class="btn-search" type="submit">SEARCH</button>
                </div>
            </div>
        </form>
    </div>
    <div class="myDiv">
        <c:forEach items="${hotels}" var="hotel">
            <div class="card w-75 myCard" id="${hotel.id}">
                <div class="card-body">
                    <h1 class="card-title">Name: ${hotel.name}</h1>
                    <p class="card-text">Rating: ${hotel.rating}/5</p>
                    <p class="card-text">Country: ${hotel.country}</p>
                    <p class="card-text">City: ${hotel.city}</p>
                    <p class="card-text">Count of rooms: ${hotel.room_count}</p>
                    <a href="/travel_agency_war_exploded/hotelDetails?id=${hotel.id}&startDate=${startDate}&endDate=${endDate}"
                       class="btn btn-primary myButton">Rooms</a>
                </div>
            </div>
        </c:forEach>
    </div>
    <br>
    <script src="js/extention/choices.js"></script>
    <script src="js/extention/flatpickr.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        flatpickr(".datepicker",
            {});

    </script>
    <script>
        const choices = new Choices('[data-trigger]',
            {
                searchEnabled: false,
                itemSelectText: '',
            });

    </script>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
