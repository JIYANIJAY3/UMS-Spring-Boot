<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/UMS/resources/css/ForgetPassword.css">
</head>

<body>
    <div class="container">
        <div class="row">

            <div class="col-md-6">
            <#if updatePassword??>
 				 <div class="alert alert-success">
 					 ${updatePassword}
 				 </div>
 			 </#if>
 			 <#if error??>
 				 <div class="alert alert-danger">
 					 ${error}
 				 </div>
 			 </#if>
                <div class="form-field">
                    <form action="ForgotPassword" method="post" id="form">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label> <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email">
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Question</label> <select class="form-control" id="exampleFormControlSelect1">
								<option>Question1</option>
							</select>
                        </div>
                        <div class="form-outline">
                            <label class="form-label select-label">answer</label> <input type="text" name="answer" id="answer" class="form-control form-control-lg">
                        </div>
                        <div class="mt-2">
                            <input type="hidden" name="UserId">
                            <label class="form-label select-label">Password</label> <input type="password" name="password" id="password" class="form-control form-control-lg">
                        </div>
                        <div class="mt-2">
                            <input type="submit" class="btn btn-primary btn-block" value="submit">
                        </div>
                    </form>
                    <div>
                        <a href='index'>Login</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="/UMS/resources/jquery-validation/dist/jquery.validate.min.js"></script>
	<script src="/UMS/resources/jquery-validation/dist/additional-methods.min.js"></script>
    <script src="/UMS/resources/js/ResetPassword.js"></script>
</body>

</html>