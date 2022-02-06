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
            $('#adminEdit').prop("checked", ((user.roles).length > 1));

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
            // $('#adminDelete').prop("checked", ((user.roles).length > 1));
            $('#adminDelete').prop("checked", ((user.roles).filter(function(e) { return e.roleName === 'ROLE_ADMIN'; }).length > 0));
        });

        $('#deleteModal').modal();
    });
});
