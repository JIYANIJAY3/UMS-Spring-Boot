$(document).ready(function() {

    var table = $('#table_id').DataTable({

        "ajax": {
            "url": "getAllUser",
            "type": "POST",
            "datatype": "json"
        },
        "columns": [
            { "data": "UserId" },
            { "data": "firstName" },
            { "data": "lastName" },
            { "data": "dob" },
            { "data": "mobaileNo" },
            { "data": "gender" },
            { "data": "language" },
            { "data": "email" },
            { "defaultContent": "	<a class='btn btn-success' href='updateUserController' id='edit-btn' role='button'>Edit</a>" },
            { "defaultContent": "	<a class='btn btn-danger' id='delete-btn' role='button'>Delete</a>" }
        ]
    });

    $('#table_id').on('click', '#delete-btn', function(event) {
        event.preventDefault();

        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                var data = table.row($(this).parents('tr')).data();
                var UserId = data.UserId;
                console.log(UserId);

                $.ajax({
                    type: "post",
                    url: "DeleteUser",
                    data: { "UserId": UserId },
                    success: function() {
                        Swal.fire(
                            'Deleted!',
                            'Your Profile has been deleted.',
                            'success'
                        )
                        table.ajax.reload();
                    },
                    error: function(textStatus) {
                        alert("not call")
                    },
                })
            }
        })


    });

    $('#table_id').on('click', '#edit-btn', function() {
        var data = table.row($(this).parents('tr')).data();
        var Email = data.email;
        console.log("Email "+Email)
        var UserId = data.UserId;
        console.log("UserId "+UserId)
        $.ajax({
            type: "GET",
            url: "GetAllUserDetails",
            data: { email: Email, userId: UserId },
            success: function(response) {
                //alert("done");
            },
            error: function() {
                alert("Error")
            }
        });
    })
});