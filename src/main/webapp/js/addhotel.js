$(document).ready(function () {
    $("button.addhotel").click(function () {
        let hotelName = $("form input.hotelName").val();
        let rating = $("#myForm input[name=rating]");
        let country = $('form input.country').val();
        let city = $('form input.city').val();

        var checkedValue = rating.filter(":checked").val();

        let addHotel = {
            hotelName: hotelName,
            rating: checkedValue,
            country: country,
            city: city
        };

        $.post("addhotel", addHotel, function (data) {
            if (data === 'Success') {
                $("form")[0].reset();
            }
        });

    });
});