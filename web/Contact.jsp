<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Contact</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="./CSSsimple/register.css">
        <link rel="stylesheet" href="css/style.css">
    </head>                      
    <body style="background:url(image/login.jpg); background-size: cover; "  >
        <div id="wrapper">
            <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
                <div class="container">
                    <a class="navbar-brand" href="Home">Coffee<small>Blend</small></a>
                </div>
            </nav>  
            <form action="Contact" method="Post" id="form-login">
                <h1 class="form-heading">Contact</h1>

                <div class="form-group">
                    <i class="far fa-user"></i>
                    <input type="text" class="form-input" name="name" value="${name}" placeholder="Name" required>
                </div>

                <div class="form-group">
                    <i class="far fa-user"></i>
                    <input type="text" class="form-input" name="email" value="${email}" placeholder="Email" required>
                </div>

                <div class="form-group">
                    <i class="far fa-user"></i>
                    <input type="text" class="form-input" name="phone" value="${phone}" placeholder="Phone" required>
                </div>

                <div class="form-group">
                    <i class="far fa-user"></i>
                    <textarea class="form-input" name="message" placeholder="Message" style="width: 100%; height: 150px;" required>${message}</textarea>
                </div>

                <p style="color: red;"> ${messregis}</p>

                <input type="submit" value="Send" class="form-submit" onclick="window.location.reload();">
            </form>
        </div>
    </body>
</html>
