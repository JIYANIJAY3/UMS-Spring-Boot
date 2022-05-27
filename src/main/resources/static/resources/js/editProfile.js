$(document).ready(function() {


	var UserId = $("#UserId").val();
	$.ajax({
		url: "GetAllUserAddress",
		type: "get",
		dataType: "json",
		data: { userId: UserId },
		success: function(data) {
			var dataLength = Object.keys(data).length;
			for (var i = 0; i < dataLength - 1; i++) {
				$("#add-more").trigger('click');
			}
			for (var i = 0; i < dataLength; i++) {
				console.log("hello" + i);
				$("#addressId_" + i).val(data[i].addressId);
				$("#country_" + i).val(data[i].country);
				$("#state_" + i).val(data[i].state);
				$("#city_" + i).
					val(data[i].city);
				$("#pincode_" + i).val(data[i].pinCode);
				$("#address_" + i).val(data[i].address);
			}

		},
		error: function() {
			alert("This Is Error from editprofile")
		}
	})

	$('#form').on('click', '.delete-profile', function() {
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
				var UserId = $("#UserId").val();
				var ProfileId = +this.id;
				console.log(ProfileId)
				$.ajax({
					url: "DeleteUserProfile",
					type: "POST",
					data: {
						profileId: ProfileId,
						userId: UserId,
					},
					success: function() {
						$("#" + ProfileId).remove();
						Swal.fire(
							'Deleted!',
							'Your Profile has been deleted.',
							'success'
						)
					}
				});
			}
		})
	});

	$("#submit-btn").attr('id', 'edit-btn-id').val("Edit")
	$("#form-heading").html("Update Profile")



	$("input[type='email']").prop({
		readonly: true
	})
	$("#mobail").prop("readonly", true);

	var count = 0;
	var isError = false;

$("#add-more").click(function(e) {
		$('.country').each(function() {
			$(this).rules("add", {
				required: true,
				lettersonly: true,
			});
		});
		$('.state').each(function() {
			$(this).rules("add", {
				required: true,
				lettersonly: true,
			});
		});
		$('.city').each(function() {
			$(this).rules("add", {
				required: true,
				lettersonly: true,
			});
		});
		$('.pincode').each(function() {
			$(this).rules("add", {
				required: true,
				digits: true,
				minlength: 6,
				maxlength: 6
			});
		});
		e.preventDefault();
	});
	
	$("#edit-btn-id").click(function() {
		$("#form").validate();
	})

	$("#form").validate({
		rules: {
			'userProfile.profiles[]': {
				accept: "image/*"
			},
			firstName: {
				required: true,
				minlength: 2,
				lettersonly: true
			},
			lastName: {
				required: true,
				minlength: 2,
				lettersonly: true
			},
			dob: "required",
			gender: "required",
			email: {
				required: true,
				email: true
			},
			language: {
				required: true
			},
			profile: {
				required: true
			},
			country: {
				required: true,
				lettersonly: true
			},
			state: {
				required: true,
				lettersonly: true
			},
			city: {
				required: true,
				lettersonly: true
			},
			pincode: {
				required: true,
				digits: true,
				minlength: 6,
				maxlength: 6
			},
			address: {
				required: true
			}
		},
		messages: {
			firstName: {
				required: "* FirstName Is Required",
				minlength: "Minimun length is 2",
				lettersonly: "* Enter Only Character"
			},
			lastName: {
				required: "* LastName Is Required",
				minlength: "Minimun length is 2",
				lettersonly: "* Enter Only Character"
			},
			dob: "Date Is Requierd",
			gender: "* Plese Select One",
			email: {
				required: "* Email Is Required",
				email: "* Enter Valide Email"
			},
			language: {
				required: "* Select atleast One"
			},
			profile: {
				required: "*Please Choose Profile"
			},
			country: {
				required: "* Enter Country Name",
				lettersonly: "* Enter Only Character"
			},
			state: {
				required: "* Enter State Name",
				lettersonly: "* Enter Only Character"
			},
			city: {
				required: "* Enter City Name",
				lettersonly: "* Enter Only Character"
			},
			pincode: {
				required: "* PinCode Is Required",
				digits: "* Enter Only Digits"
			},
			address: {
				required: "* Enter Address"
			}
		}, errorPlacement:
			function(error, element) {
				if (element.is(":radio")) {
					// error append here
					error.appendTo('#radio');
				}
				else if (element.is(":checkbox")) {
					error.appendTo('#checkbox');
				}
				else {
					error.insertAfter(element);
				}
			},
		submitHandler: function(form, event) {
			event.preventDefault();
			let formdata = new FormData(form);
			$.ajax({
				type: "post",
				url: "UpdateUserDetails",
				data: formdata,
				enctype: 'multipart/form-data',

				success: function(data) {
					if (data === "Update") {

						Swal.fire({
							title: 'Are You Redirect Home Page Press Ok',
							text: " ",
							icon: 'warning',
							showCancelButton: true,
							confirmButtonColor: '#3085d6',
							cancelButtonColor: '#d33',
							confirmButtonText: 'Yes!'
						}).then((result) => {
							if (result.isConfirmed) {
								var successUrl = "AdminHome";
								window.location.href = successUrl;
							}
						});
					}
					else if (data == "Update User") {
						Swal.fire({
							title: 'Are You Redirect Home Page Press Ok',
							text: " ",
							icon: 'warning',
							showCancelButton: true,
							confirmButtonColor: '#3085d6',
							cancelButtonColor: '#d33',
							confirmButtonText: 'Yes'
						}).then((result) => {
							if (result.isConfirmed) {
								var successUrl = "UserHome";
								window.location.href = successUrl;
							}
						});
						/*var successUrl = "UserHome.jsp";
						window.location.href = successUrl;*/
						//swal.fire("Successfully updated", "You clicked the Edit!", "success");
					}
					else {
						swal.fire("Somthing Went Wrong", "You clicked the Edit! Please Fill The Details", "warning");
					}
				},
				error: function() {
					swal.fire("Somthing Went Wrong", " ", "warning");
				},
				processData: false,
				contentType: false
			});
		},
	})
})
