$(document).ready(function() {

	var count = 0;
	var isError = false;
	$(".add-btn").click(function() {
		count++;
		for (let i = 1; i <= count; i++) {
			$("#pincode_" + i).keyup(function(e) {
				$(".error").remove();
				console.log("#pincode_" + i)
				var pincode = $("#pincode_" + i).val();
				if (pincode.match("[^0-9]")) {
					isError = true;
				}
			})
			
		}
	});

	$("#submit-btn").click(function(event) {
		event.preventDefault();
		if (isError == true) {
			alert("not ok")
		}
		else {
			let formdata = new FormData(form);
			$("#form").hide();
			$(".loader").hide();

			$.ajax({
				type: "POST",
				url: "Registration",
				data: formdata,
				enctype: 'multipart/form-data',

				success: function(response) {
					$("#form").show();

					if (response.trim() === "Successfully Added...") {
						swal("Successfully Added", "You clicked the submit!", "success");
					} else {
						swal("Somthing Went Wrong please fill the all details", "You clicked the submit!", "warning");
					}
				},
				error: function(textStatus) {
					$("#form").show();
					$("#massage").html("Somthing Went Wrong... :( ").css("color", "red");
				},
				processData: false,
				contentType: false
			});
		}
	})

})