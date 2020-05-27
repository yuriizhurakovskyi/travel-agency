<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<nav class="nav">
    <div class="container">
        <div class="logo">
            <a href="#">Travel Agency</a>
        </div>
        <div id="mainListDiv" class="main_list">
            <ul class="navlinks">
                <li><a href="index">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="hotels">Hotels</a></li>
                <li><a href="login">Login</a></li>
                <li><a href="registration">Registration</a></li>
            </ul>
        </div>
        <span class="navTrigger">
                <i></i>
                <i></i>
                <i></i>
            </span>
    </div>
</nav>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>

<!-- Function used to shrink nav bar removing paddings and adding black background -->
<script>
    $(window).scroll(function () {
        if ($(document).scrollTop() > 50) {
            $('.nav').addClass('affix');
            console.log("OK");
        } else {
            $('.nav').removeClass('affix');
        }
    });
</script>