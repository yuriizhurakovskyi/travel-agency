$("button.login").click(function () {
    let email = $("form input.email").val();
    let password = $("form input.password").val();

    if (email === '' || password === '') {
        alert("Please fill login form!");
    } else {
        let userLogin = {
            email : email,
            password : password
        };
        $.post("login", userLogin, function(data) {
            if(data !== ''){
                let customUrl = '';
                let urlContent = window.location.href.split('/');
                for (let i = 0; i < urlContent.length-1; i++) {
                    customUrl+=urlContent[i]+'/'
                }
                customUrl+=data.destinationUrl;
                window.location = customUrl;
            }
            $("form")[1].reset();
        });
    }
});