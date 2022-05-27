<#import "/spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->

    <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/UMS/resources/image-uploader-master/dist/image-uploader.min.css">
    <link rel="stylesheet" href="/UMS/resources/css/index.css">
    <link rel="stylesheet" href="/UMS/resources/css/address.css">
</head>

<body>
<#if role?? && role=="User"><#include "Header.ftl">
<#elseif role?? && role=="Admin"><#include "AdminHeader.ftl">
</#if>
<br><br>
<div class="container">
<div class="row">
<div  class="alert alert-danger" id="error-box" style="display:none">
<p id="error-msg"></p>
</div>
</div>
</div>
    <section>
        <div class="container text-center">
            <div class="row">
                <div class="col-md-12">
                    <div class="spinner-border loader" style='display:
                            none'></div>
                </div>
            </div>
        </div>
    </section>
    <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row justify-content-center align-items-center
                    h-100">
                <div class="col-12 col-lg-9 col-xl-7">
                    <div class="card shadow-2-strong card-registration" style="border-radius:
                            15px;">
                        <div class="card-body p-4 p-md-5">
                            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 heading" id="form-heading">Registration Form
                            </h3>
                            <div class="container text-center"></div>

                            <form action="submitform" method="post" id="form" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                	<input type="hidden" id="UserId" value="<#if Session.User??> ${User.userId}<#else>0 </#if>" name="userId">
                                        <div class="form-outline">
                                            <label class="form-label" for="firstName" name="fname">First
                                                    Name</label> <input type="text" id="firstName" class="form-control
                                                    form-control-lg" value= '<#if Session.User??>${User.firstName?trim}</#if>' name="firstName"> <label for="fname" class="error"></label>
                                        </div>
                                    </div>

                                    <div class="col-md-6 mb-4">

                                        <div class="form-outline">
                                            <label class="form-label" for="lastName">Last Name</label>
                                            <input type="text" id="lastName" class="form-control
                                                    form-control-lg" name="lastName" value= '<#if Session.User??>${User.lastName?trim}</#if>'>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">

                                    <div class="col-md-6 mb-4 d-flex
                                            align-items-center">

                                        <div class="form-outline datepicker
                                                w-100">
                                            <label for="birthdayDate" class="form-label">Birthday</label>
                                            <input type="text" name="dob" id="datepicker" class="form-control" value= '<#if Session.User??>${User.dob?trim}</#if>' autocomplete="off">
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-6 mb-4" id="radio">
                                        <h6 class="mb-2 pb-1">Gender:</h6>
                                        <div class="form-check
                                                form-check-inline">
                                            <label class="form-check-label" for="femaleGender">Female</label>
                                            <input class="form-check-input" type="radio" value= "Female" <#if Session.User??> <#if User.gender=="Female"> checked </#if> </#if>  name="gender" id="femaleGender" />
                                            <#--  <#if Session.User??> <#if User.gender=="Female"> checked </#if> </#if>  -->
                                        </div>

                                        <div class="form-check
                                                form-check-inline">
                                            <label class="form-check-label" for="maleGender">Male</label>
                                            <input class="form-check-input" type="radio" value= "Male" <#if Session.User??> <#if User.gender=="Male"> checked </#if> </#if> name="gender" id="maleGender">
                                        </div>
                                        <div class='GenderValidation' style="color: #ee8929;"></div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-4 pb-2">

                                        <div class="form-outline">
                                            <label class="form-label" for="emailAddress">Email</label>
                                            <input type="email" id="emailAddress" class="form-control
                                                    form-control-lg" name="email" value= '<#if Session.User??> ${User.email?trim}</#if>'>
                                        </div>
                                        <p id="isEmailPresent"></p>
                                    </div>
                                   <#if !Session.User??>
                                        <div class="col-md-6 mb-4 pb-2">
                                            <div class="form-outline">
                                                <label class="form-label" for="phoneNumber">password</label>
                                                <input type="password" id="password" class="form-control
                                                        form-control-lg" name="password">
                                            </div>
                                        </div>
                                   </#if>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-4 pb-2">
                                        <div class="form-outline">
                                            <label class="form-label" for="mobail">MobailNo</label>
                                            <input type="tel" id="mobail" class="form-control
                                                    form-control-lg" name="mobaileNo" value= '<#if Session.User??> ${User.mobaileNo?trim}</#if>'>
                                        </div>
                                    </div>

                                    <div class="col-md-6 mb-4 pb-2" id="checkbox">
                                        <label class="form-label" for="mobail">Language</label>
                                        <div class="form-outline">
                                            <div class="form-check
                                                    form-check-inline">
                                                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" <#if User?? && User.language?contains("JAVA")>checked</#if> value="JAVA" name="language">
                                                <label class="form-check-label" for="inlineCheckbox1">JAVA</label>
                                            </div>
                                            <div class="form-check
                                                    form-check-inline">
                                                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" <#if User?? && User.language?contains("JavaScript")>checked</#if> value="JavaScript" name="language">
                                                <label class="form-check-label" for="inlineCheckbox1">JavaScript</label>
                                            </div>
                                            <div class="form-check
                                                    form-check-inline">
                                                <input class="form-check-input" type="checkbox" id="inlineCheckbox3" <#if User?? && User.language?contains("C++")>checked</#if> value="C++" name="language">
                                                <label class="form-check-label" for="inlineCheckbox1">C++</label>
                                            </div>
                                        </div>
                                        <label class="ferror" for="mobail"></label>
                                    </div>
                                </div>

                                <div class="row">
                                    <!-- <label class="form-label select-label">Security Question</label> -->
                                    <div class="col-md-6 mb-4 pb-2">
                                        <label class="form-label
                                                select-label">Security
                                                Question</label> <select class="select form-control-lg" name="select-question">
                                                <option value="1" disabled>Choose
                                                    option</option>
                                                <option value="2">Question 1</option>
                                            </select>
                                    </div>

                                    <div class="col-md-6 mb-4 pb-2">
                                        <div class="form-outline">
                                            <label class="form-label
                                                    select-label">answer</label>
                                            <input type="text" name="answer" id="answer" class="form-control
                                                    form-control-lg" value= '<#if Session.User??> ${User.answer?trim}</#if>'>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div style="margin-bottom:
                                                    47px">
                                                <label>Profile:</label>
                                            </div>
                                       <#if Session.User??>
                                        <#list UserProfile as profile>
                                        	<span id="${profile.imageId}" class="delete-profile">
															<span class="uploadedimage"><img
																src="data:image/jpg;base64,${profile.base64Image}"
																class="image" width="100" height="100" /> <span
																class="del-image"><i class="material-icons">clear</i></span></span>
														</span>
                                        </#list>
                                       </#if>
                                       <br>
                                           		 <div class="input-images"></div>
                                            <br>
                                            <span class="image-error" style="display: none">Please select Profile</span>
                                        </div>
                                    </div>
                                </div>

                                <div id="main-container">
                                    <div class="panel card container-item">
                                        <div class="panel-body">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="hello">
                                                    <div class="form-group">
                                                            <input type="hidden" name="userAddress[0].addressId" id="addressId_0" required/>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label class="control-label" for="address_line_one_0">Country
                                                                </label> <input type="text" id="country_0" class="form-control unique country" name="userAddress[0].country" required>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label class="control-label" for="address_line_two_0">State
                                                                </label> <input type="text" id="state_0" class="form-control
                                                                    unique state" name="userAddress[0].state" required>
                                                            <p class="help-block
                                                                    help-block-error"></p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label class="control-label" for="city_0">City</label>
                                                            <input type="text" id="city_0" class="form-control city" name="userAddress[0].city" required>
                                                            
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label class="control-label" for="city_0">PinCode</label>
                                                            <input type="text" id="pincode_0" class="form-control
                                                                    pincode" name="userAddress[0].pinCode" required>
                                                            <p class="help-block
                                                                    help-block-error"></p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <div class="form-group">
                                                            <label class="control-label" for="city_0">Address</label>
                                                            <input type="text" id="address_0" class="form-control" name="userAddress[0].address" required>
                                                            <p class="help-block
                                                                    help-block-error
                                                                    error"></p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <div>
                                                            <a href="javascript:void(0)" id="remove-btn_0" class="remove-item
                                                                    btn btn-sm
                                                                    btn-danger
                                                                    remove-social-media
                                                                    remove-btn">Remove</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div>
                                        <a class="btn btn-primary btn-sm
                                                add-btn" id="add-more" href="javascript:;" role="button"><i class="fa
                                                    fa-plus"></i>
                                                Add more address</a>
                                    </div>
                                </div>

                                <div class="mt-4 pt-2">
                                    <input class="btn btn-success btn-lg" id="submit-btn" type="submit" value="Submit" />
                                </div>
                            </form>
                            <br>
                            <#if !role??>
                           		 <a href="index">Login</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
    <script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

	<script src="/UMS/resources/jquery-validation/dist/jquery.validate.min.js"></script>
	<script src="/UMS/resources/jquery-validation/dist/additional-methods.min.js"></script>
    <script src="/UMS/resources/address-plugin/cloneData.js" type="text/javascript"></script>
    <script src="/UMS/resources/image-uploader-master/dist/image-uploader.min.js"></script>
 	<script src="/UMS/resources/js/getdata.js"></script>

 	<#if Session.User??><script src="/UMS/resources/js/editProfile.js"></script>
 		<#else>  
 			<script src="/UMS/resources/js/postdata.js"></script>
 	</#if>

    <script>
        $('.input-images').imageUploader({
            imagesInputName: 'userProfile.profiles',
        });
    </script>
    
    <script>
        $(function() {
            $("#datepicker").datepicker({
                changeMonth: true,
                changeYear: true,
                maxDate: new Date(),
                dateFormat: "yy-mm-dd"
            });
        });
    </script>
    <script>
        $('a#add-more').cloneData({
            mainContainerId: 'main-container', // Main container Should be ID
            cloneContainer: 'container-item', // Which you want to clone
            removeButtonClass: 'remove-item', // Remove button for remove cloned HTML
            removeConfirm: true, // default true confirm before delete clone item
            removeConfirmMessage: 'Are you sure want to delete?', // confirm delete message
            //append: '<a href="javascript:void(0)" class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>', // Set extra HTML append to clone HTML
            minLimit: 1, // Default 1 set minimum clone HTML required
            maxLimit: 20, // Default unlimited or set maximum limit of clone HTML
            defaultRender: 1,
            init: function() {
                console.info(':: Initialize Plugin ::');
            },
            beforeRender: function() {
                console.info(':: Before rendered callback called');
            },
            afterRender: function() {
                console.info(':: After rendered callback called');
                //$(".selectpicker").selectpicker('refresh');
            },
            afterRemove: function() {
                console.warn(':: After remove callback called');
            },
            beforeRemove: function() {
                console.warn(':: Before remove callback called');
            }

        });
    </script>
</body>