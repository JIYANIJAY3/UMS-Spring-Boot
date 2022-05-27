<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>UserHome</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/UMS/resources/css/UserHome.css">
</head>

<body>
<#include "Header.ftl">
    <div class="section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 style="color: aliceblue;">
                        <#if Session.User??>
                            <h1>Welcome ${User.firstName}</h1>
                        </#if>
                    </h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="head-desc">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor deleniti corrupti dolore repellat error aliquam eius quaerat commodi, rem voluptatum maiores consequatur saepe repellendus nulla quam veritatis dicta delectus ea?
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="head-desc">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor deleniti corrupti dolore repellat error aliquam eius quaerat commodi, rem voluptatum maiores consequatur saepe repellendus nulla quam veritatis dicta delectus ea?
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="head-desc">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor deleniti corrupti dolore repellat error aliquam eius quaerat commodi, rem voluptatum maiores consequatur saepe repellendus nulla quam veritatis dicta delectus ea?
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="head-desc">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor deleniti corrupti dolore repellat error aliquam eius quaerat commodi, rem voluptatum maiores consequatur saepe repellendus nulla quam veritatis dicta delectus ea?
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="head-desc">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor deleniti corrupti dolore repellat error aliquam eius quaerat commodi, rem voluptatum maiores consequatur saepe repellendus nulla quam veritatis dicta delectus ea?
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="result"></div>
    <script src="../resources/js/getdata.js"></script>
    <#include "footer.ftl">
</body>

</html>