$(document).ready(function() {

	$("#emailAddress").change(function(e) {
		e.preventDefault();

		var email = $("#emailAddress").val();

		$.ajax({
			type: "POST",
			url: "CheckEmailIsPresent",
			data:{ email : email},

			success: function(data) {
				if (data.trim() === "done") {
					$("#isEmailPresent").html("Email Already Present").css("color", "red");
				} else {
					$("#isEmailPresent").html(" ");
				}
			},
			error: function(textStatus) {
				swal.fire("Somthing Went Wrong", " ", "warning");
			},
		
		});
	});
})