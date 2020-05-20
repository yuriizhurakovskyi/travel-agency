<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <title>Registration</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"
          rel="stylesheet">
    <link href="css/main.css" rel="stylesheet" media="all">
</head>
<body>
<div class="page-wrapper bg-dark p-t-100 p-b-50">
    <div class="wrapper wrapper--w900">
        <div class="card card-6">
            <div class="card-heading">
                <h2 class="title">Registration</h2>
            </div>
            <div class="card-body">
                <form method="POST" class="register-form">
                    <div class="form-row">
                        <div class="name">First name</div>
                        <div class="value">
                            <div class="input-group">
                                <input class="input--style-6 firstName" type="text" name="firstName"
                                       placeholder="First name">
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Last name</div>
                        <div class="value">
                            <div class="input-group">
                                <input class="input--style-6 lastName" type="text" name="lastName"
                                       placeholder="Last name">
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Email address</div>
                        <div class="value">
                            <div class="input-group">
                                <input class="input--style-6 email" type="email" name="email"
                                       placeholder="example@email.com">
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Phone number</div>
                        <div class="value">
                            <div class="input-group">
                                <input class="input--style-6 phoneNumber" type="text" name="phoneNumber"
                                       placeholder="Phone number">
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Password</div>
                        <div class="value">
                            <input class="input--style-6 password" type="password" name="password">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Confirm password</div>
                        <div class="value">
                            <input class="input--style-6 confirmPassword" type="password" name="confirmPassword">
                        </div>
                    </div>

                </form>
            </div>
            <div class="card-footer">
                <button class="btn btn--radius-2 btn--blue-2 register" type="submit">Register</button>
            </div>
        </div>
    </div>
</div>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/global.js"></script>
<script src="js/registration.js"></script>
</body>
</html>
