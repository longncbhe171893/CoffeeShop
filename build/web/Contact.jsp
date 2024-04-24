<%-- 
    Document   : BlogList
    Created on : May 20, 2023, 8:48:37 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSSsimple/register.css"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

        <style>
            
body{
    background: url('../img/bg.jpg');
    background-size: cover;
    background-position-y: -80px;
    font-size: 16px;
}
#wrapper {
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

.form-group{
    border-bottom: 1px solid #fff;
    margin-top: 15px;
    margin-bottom: 20px;
    display: flex;
}
.form-group i{
    color: #fff;
    font-size: 14px;
    padding-top: 5px;
    padding-right: 10px;
}
.form-input{
    background: transparent;
    border: 0;
    outline: 0;
    color: #f5f5f5;
    flex-grow: 1;
}
.form-input::placeholder{
    color: #f5f5f5;
}

.form-submit{
    background: transparent;
    border: 1px solid #f5f5f5;
    color: #fff;
    width: 100%;
    text-transform: uppercase;
    padding: 6px 10px;
    transition: 0.25s ease-in-out;
    margin-top: 15px;
}
.form-submit:hover{
    border: 1px solid orange;
}
select.form-input {
    background: #101012; /* Màu nền của phần chọn option */
    border: 0;
    outline: 0;
    color: #f5f5f5;
    flex-grow: 1;
}
.register p a:hover{
    text-decoration: underline;
}


.forget{
    color: #fff;
    display: flex;
    float: right; 
}

.forget label input{
    margin-right: 3px;
    
}
.forget label a{
    color: #fff;
    text-decoration: none;
}
.forget label a:hover{
    text-decoration: underline;
    color: orange; 
}
        </style>

    </head>
    <body>
        
        <jsp:include page="header.jsp"/>
        <div style="margin-top: 150px"></div>
            
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <form action="Contact" method="post" class="bg-black p-5 contact-form">
                        <h1 style="text-align: center; color: #FF8C00">Contact</h1>
                        <div class="form-group">
                            <i class="far fa-user"></i>
                            <input type="text" class="form-input" name="name" value="${name}" placeholder="Your Name" required>
                        </div>
                        <div class="form-group">
                            <i class="far fa-envelope"></i>
                            <input type="email" class="form-input" name="email" value="${email}" placeholder="Your Email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" 
                                   title="Please input valid email!!" required>
                        </div>
                        <div class="form-group">
                            <i class="fas fa-phone"> </i>
                            <input type="text" class="form-input" name="phone" value="${phone}" placeholder="Your Phone" pattern=".{10,11}"
                            title="phone must be at least 10 to 11 characters!!!" required>
                        </div>
                        <div class="form-group">
                            <i class="fas fa-question"></i>
                            <select class="form-input" name="contact_type" value="contact_type">
                                <option value="Question">Question</option>
                                <option value="Feedback">Feedback</option>
                                <option value="Support">Support</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <i class="far fa-comment-dots"></i>
                            <input type="text" class="form-input" name="subject" value="${subject}"" placeholder="Subject" required>
                        </div>
                        <div class="form-group">
                            <i class="far fa-comment"></i>
                            <textarea name="message" id="" cols="30" rows="6" class="form-input" placeholder="Message" required>${message}</textarea>
                        </div>
                        <input type="submit" value="Send Message" class="form-submit">
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp"/>
    </body>
</html>
