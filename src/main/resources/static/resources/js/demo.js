$(document).ready(function() {

	$("#submit-btn").click(function(e) {
		e.preventDefault();
		var forms = $('#form')[0];

		let formdata = new FormData(forms);
		$("#form").hide();
		$(".loader").show();

		for (var [key, value] of formdata.entries()) {
			console.log(key, value);
		}

		console.log(formdata);
		$.ajax({
			type: "POST",
			url: "submitform",
			data: formdata,
			enctype: 'multipart/form-data',

			success: function(response) {
				$("#form").show();
				$(".loader").hide();
				if (response.trim() === "Successfully Added...") {
					swal.fire("Successfully Added", "You clicked the submit!", "success");
				} else {
					swal.fire("Somthing Went Wrong please fill the all details", "You clicked the submit!", "warning");
				}
			},
			error: function() {
				alert("not ok");
				$("#form").show();
				$(".loader").hide();
				$("#massage").html("Somthing Went Wrong... :( ").css("color", "red");
			},
			processData: false,
			contentType: false
		});
	})
});