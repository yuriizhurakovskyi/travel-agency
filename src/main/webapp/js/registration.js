$(document).ready(function () {
    $("button.register").click(function () {
        let firstName = $("form.register-form input.firstName").val();
        let lastName = $("form.register-form input.lastName").val();
        let email = $("form.register-form input.email").val();
        let phoneNumber = $("form.register-form input.phoneNumber").val();
        let password = $("form.register-form input.password").val();
        let cpassword = $("form.register-form input.confirmPassword").val();
        if (firstName == '' || lastName == '' || email == '' || password == '' || cpassword == '' || phoneNumber == '') {
            alert("Please fill all fields...!!!!!!");
        } else if ((password.length) < 8) {
            alert("Password should atleast 8 character in length...!!!!!!");
        } else if (!(password).match(cpassword)) {
            alert("Your passwords don't match. Try again?");
        } else {
            let userRegistration = {
                firstName: firstName,
                lastName: lastName,
                email: email,
                phoneNumber: phoneNumber,
                password: password
            };

            $.post("registration", userRegistration, function (data) {
                if (data === 'Success') {
                    $("form")[0].reset();
                }
            });
        }
    });
});