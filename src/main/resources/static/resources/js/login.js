$(document).ready(function() {
	$.validator.addMethod('regex', function(value, element, regex) {
		return this.optional(element) || regex.test(value);
	}, "Formate Not Match");

	$("#form").validate({
		rules: {
			email: {
				required: true,
				regex: /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/
			},
			password: {
				required: true
			}
		},
		messages: {
			email: {
				required: "Email Requierd",
				reges: "Enter Valid Email Formate Enter Email like xxx@xxx.xxx"
			},
			password: {
				required: "Enter Password"
			}
		}
	})
});