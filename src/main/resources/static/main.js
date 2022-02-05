$('document').ready(function () {
    $('.table .btne').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('#idEdit').val(user.id);
            $('#firstNameEdit').val(user.firstName);
            // $('#passwordEdit').val(user.password);
            $('#lastNameEdit').val(user.lastName);
            $('#sexEdit').val(user.sex);

            $('#smokerEdit').prop("checked", user.smoker);
            // $('#smokerEdit').val(value = user.smoker);


            // $('#adminEdit').val(user.roleAdmin);
            // $('#adminEdit').prop("checked", а);

        });

        $('#editModal').modal();
    });
    $('.table .btnd').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('#idDelete').val(user.id);
            $('#firstNameDelete').val(user.firstName);
            $('#lastNameDelete').val(user.lastName);
            $('#sexDelete').val(user.sex);
            $('#smokerDelete').prop("checked", user.smoker);
            // $('#adminEdit').val(user.roleAdmin);


        });
    //
        $('#deleteModal').modal();

    });
});


// $('document').ready(function () {
//     $('.table .btnd').on('click', function (event) {
//         event.preventDefault();
//         var href = $(this).attr('href');
//         $.get(href, function (user, status) {
//             $('#idDelete').val(user.id);
//             $('#firstNameDelete').val(user.firstName);
//             $('#passwordEdit').val(user.password);
//             $('#lastNameDelete').val(user.lastName);
//             $('#sexDelete').val(user.sex);
//             $('#smokerDelete').prop("checked", user.smoker);
//             $('#adminDelete').modal();
//         });
//     });
// });