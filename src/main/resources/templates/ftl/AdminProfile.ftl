<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>User Profile Page</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="../resources/css/UserHome.css">
</head>


<body>
    <#include "AdminHeader.ftl">
        <section style="background-color: #eee;">
            <div class="container py-5">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">FirstName</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
                                        	<#if Session.Admin??>
                                        		<p>${Admin.firstName}</p>
                                        	</#if>
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">LastName</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
	                                        <#if Session.Admin??>
	                                        		<p>${Admin.lastName}</p>
	                                        </#if>
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Dob</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
	                                        <#if Session.Admin??>
	                                        		<p>${Admin.dob}</p>
	                                        </#if>
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Gender</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
	                                        <#if Session.Admin??>
	                                        		<p>${Admin.gender}</p>
	                                        </#if>
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Email</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
	                                        <#if Session.Admin??>
	                                        		<p>${Admin.email}</p>
	                                        </#if>
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Mobaile No</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
	                                        <#if Session.Admin??>
	                                        		<p>${Admin.mobaileNo}</p>
	                                        </#if>
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Language</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
	                                        <#if Session.Admin??>
	                                        		<p>${Admin.language}</p>
	                                        </#if>
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Profile</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
                                       <#if Admin??>
                                       	 <#list AdminProfile as profile>
                                        	<img src="data:image/jpg;base64,${profile.base64Image}" class="image" width="100" height="100" />
                                        </#list>
                                       </#if>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <#include "footer.ftl">
</body>

</html>