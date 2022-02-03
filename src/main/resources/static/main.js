$('document').ready(function () {
    $('.table .btn').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href,function(user, status){
            $('#idEdit').val(user.id);
            $('#firstNameEdit').val(user.firstName);
            $('#passwordEdit').val(user.password);
            $('#lastNameEdit').val(user.lastName);
            $('#sexEdit').val(user.sex);
            $('#smokerEdit').val(user.smoker);
            $('#adminEdit').val(user.roleAdmin);
        });

        $('#editModal').modal();
    });
    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        $('#deleteModal').modal();
    });
});