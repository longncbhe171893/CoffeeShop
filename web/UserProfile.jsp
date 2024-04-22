<%-- 
    Document   : UserProfile
    Created on : Apr 12, 2024, 9:08:52 AM
    Author     : HP
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSSsample/userProfile.css">
        <style>

            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            /* Add some styling to the header */
            header {
                background-color: #333;
                color: #fff;
                padding: 10px;
                text-align: center;
            }

            /* Add some styling to the form container */
            .ipform {
                margin: 100px auto;
                max-width: 600px;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            /* Style the form elements */
            form {
                display: flex;
                flex-direction: column;
            }

            h3 {
                text-align: center;
                margin-bottom: 20px;
            }

            label {
                font-weight: bold;
                margin-bottom: 5px;
            }

            input[type="text"],
            input[type="email"] {
                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            

            .change {
                color: #fff;
                font-size: 18px;
                font-weight: normal; /* Adjust the font weight as needed */
                text-decoration: none;
                margin-left: 350px;
            }

            .change:hover {
                text-decoration: underline;
            }


            .editprofile {
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .editprofile:hover {
                background-color: #0056b3;
            }

            p {
                color: #d50000; /* Red color for error messages */
                margin-bottom: 10px;
            }

            /* Add some styling to the footer */
            footer {
                background-color: #333;
                color: #fff;
                padding: 10px;
                text-align: center;
            }

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div style="margin-top: 200px; margin-left: 462px" class="ipform">
    <form method="post" action="UserProfile" >
        <h3>My Profile</h3>
        <!-- Profile Image -->
        <div class="profile-image-container">
            <img src="${sessionScope['account'].getImage()}" class="profile-image" alt="Profile Image">
            <input class="imageprofile1" type="file" accept="image/*" name="image">
        </div><br/>
        <!-- Name -->
        <label class="nameprofile">Name</label><input class="nameprofile1" type="text" value="${sessionScope['account'].getName()}" name="name"><br/>
        <!-- Email -->
        <label class="emailprofile">Email</label><input readonly class="emailprofile1" value="${sessionScope['account'].getEmail()}" name="email"><br/>
        <!-- Gender -->
        <label class="genderprofile">Gender</label><input class="genderprofile1" type="text" value="${sessionScope['account'].getSex()}" name="sex"><br/>
        <!-- Phone -->
        <label class="phoneprofile">Phone</label><input class="phoneprofile1" type="number" value="${sessionScope['account'].getPhone()}" name="phone"><br/>
        <!-- Address -->
        <label class="addressprofile">Address</label><input class="addressprofile1" type="text" value="${sessionScope['account'].getAddress()}" name="address"><br/>
        <!-- Change password link -->
        <a href="ChangePassword.jsp" class="change-container">
            <span class="change">Change password</span>
        </a>
        <!-- Hidden input for user id -->
        <input value="${sessionScope['account'].getId()}" name="id" type="hidden">
        <!-- Success message -->
        <p style="color: greenyellow">${mess}</p>
        <!-- Edit button -->
        <input style="background: #c49b63; color: white" class="editprofile" type="submit" value="Edit"> 
    </form>
</div>
<div style="margin-bottom: 100px"></div>

        <jsp:include page="Footer.jsp"/>

        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/jquery.timepicker.min.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
