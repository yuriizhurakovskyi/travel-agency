$(document).ready(function () {
    $("button.updatehotel").click(function () {
        let id = $('form h2').attr('id');
        let hotelName = $("form input.hotelName").val();
        let rating = $("#myForm input[name=rating]");
        let country = $('form input.country').val();
        let city = $('form input.city').val();

        var checkedValue = rating.filter(":checked").val();

        let updateHotel = {
            id: id,
            hotelName: hotelName,
            rating: checkedValue,
            country: country,
            city: city
        };

        $.post("updateHotel", updateHotel, function (data) {
            if (data === 'Success') {
                $("form")[0].reset();
            }
        });

    });
});