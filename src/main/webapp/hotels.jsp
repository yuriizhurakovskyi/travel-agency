<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Index page</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500" rel="stylesheet"/>
    <link href="css/searchform.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/result.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>

<section class="home">
</section>
<div class="myDiv card w-75 myCard">
    <div class="card-body">
        <div class="card w-75 myCard">
            <div class="card-body">
                <h1>Cities and countries:</h1>
                <c:forEach items="${citiesAndCountries}" var="cityAndCountry">
                    <h3 class="card-title">${cityAndCountry}</h3>
                </c:forEach>
            </div>
        </div>
        <div class="card w-75 myCard">
            <div class="card-body">
                <h1>Select city</h1>
                <div>
                    <select class="city">
                        <c:forEach items="${citiesAndCountries}" var="cityAndCountry">
                            <option value="${cityAndCountry}">${cityAndCountry}</option>
                        </c:forEach>
                    </select>
                    <div id="showHotels">Show results...</div>
                </div>
            </div>
        </div>
        <div id="hotelsByCity">
        </div>
    </div>
</div>

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    let hotels = null;
    $(document).ready(function () {
        $("select.city").change(function () {
            let selectedCity = $(this).children("option:selected").val();
            $.post("hotels", {'selectedCity': selectedCity},
                function (data) {
                    if (data != '') {
                        hotels = data;
                    }
                });
        });
        $("#showHotels").click(function () {
            let cardsContent = "";
            jQuery.each(hotels, function (i, hotel) {
                cardsContent += "<div class='card w-75 myCard' id='hotel.id'>" +
                    "<div class='card-body'>" +
                    "<h1 class='card-title'>" + "Name:" + hotel.name + "</h1>" +
                    "<p class='card-text'>" + "Rating:" + hotel.rating + "/5" + "</p>" +
                    "<p class='card-text'>" + "Country:" + hotel.country + "</p>" +
                    "<p class='card-text'>" + "City:" + hotel.city + "</p>" +
                    "<p class='card-text'>" + "Count of rooms: " + hotel.room_count + "</p>" +
                    "</div>" +
                    "</div>"
            });
            $('#hotelsByCity').html(cardsContent);
        });
    });
</script>
<jsp:include page="footer.jsp"/>
</body>
</html>
