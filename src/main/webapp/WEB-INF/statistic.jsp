<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>Statistic page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/result.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
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
                <a href="manager"><span class="fa fa-home mr-3"></span> Homepage</a>
            </li>
            <li>
                <a href="addhotel"><span class="fa fa-hotel mr-3"></span>Add Hotels</a>
            </li>
            <li>
                <a href="statistic"><span class="fa fa-sticky-note mr-3"></span> Statistics</a>
            </li>
        </ul>

    </nav>

    <div id="content" class="p-4 p-md-5 pt-5">

        <div class="card w-75 myCard">
            <div class="card-body">
                <h2>Select Hotel</h2>
                <div>
                    <select class="hotel" onchange="location = this.value;">
                        <option>Select Hotel</option>
                        <c:forEach items="${hotels}" var="hotel">
                            <option value="/travel_agency_war_exploded/statistic?id=${hotel.id}">${hotel.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div class="card w-75 myCard">
            <div class="card-body">
                <div id="chart_div"></div>
            </div>
        </div>

        <div class="card w-75 myCard">
            <div class="card-body">
                <div id="chart_div_average"></div>
            </div>
        </div>

        <div class="card w-75 myCard">
            <div class="card-body">
                <div id="chart_div_clients"></div>
            </div>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main1.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
    google.charts.load('current', {packages: ['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawColColors);

    function drawColColors() {
        var data = new google.visualization.DataTable();

        data.addColumn('number', 'Hotel ID');
        data.addColumn('number', 'Days Booked');

        data.addRows([
            <c:forEach items="${roomsUsage}" var="entry">
            [{v: ${entry.key}, f: '${entry.key}'}, ${entry.value}],
            </c:forEach>
        ]);

        var options = {
            title: "Rooms usage for 30 days",
            colors: ['#9575cd', '#33ac71'],
            hAxis: {
                title: "Room ID",
            },
            vAxis: {
                title: 'Number of days'
            }
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);

        var dataForAvg = new google.visualization.DataTable();

        dataForAvg.addColumn('string', 'Hotel');
        dataForAvg.addColumn('number', 'Avg Reservation');

        dataForAvg.addRows([
            <c:forEach items="${averageUsage}" var="entry">
            [{v: '${entry.key}', f: '${entry.key}'}, ${entry.value}],
            </c:forEach>
        ]);

        var optionsForAvg = {
            title: "Avg Resrevation In hotels",
            colors: ['#9575cd', '#33ac71'],
            hAxis: {
                title: "Hotel",
            },
            vAxis: {
                title: 'Avg Reservation'
            }
        };

        var chartAvg = new google.visualization.ColumnChart(document.getElementById('chart_div_average'));
        chartAvg.draw(dataForAvg, optionsForAvg);

        var dataForClient = new google.visualization.DataTable();

        dataForClient.addColumn('string', 'Hotel');
        dataForClient.addColumn('number', 'Avg Reservation');

        dataForClient.addRows([
            <c:forEach items="${clientCount}" var="entry">
            [{v: '${entry.key}', f: '${entry.key}'}, ${entry.value}],
            </c:forEach>
        ]);

        var optionsForClient = {
            title: "Clients In hotels",
            colors: ['#9575cd', '#33ac71'],
            hAxis: {
                title: "Hotel",
            },
            vAxis: {
                title: 'Client Amount'
            }
        };

        var chartClient = new google.visualization.ColumnChart(document.getElementById('chart_div_clients'));
        chartClient.draw(dataForClient, optionsForClient);

    }
</script>


</body>
</html>