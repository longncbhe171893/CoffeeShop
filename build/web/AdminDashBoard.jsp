<%-- 
    Document   : AdminDashBoard
    Created on : Apr 14, 2024, 2:55:25 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="CSSsimple/sellerDashbord.css">

        <title>Admin Dashboard</title>     
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerAdmin.jsp"/>

        <!-- CONTENT -->
        <section id="content">
            <!-- MAIN -->
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <form action="#">
                    <div class="form-input">                  
                    </div>
                </form>
                <a href="#" class="profile">
                </a>
            </nav>
        </section>
    </body>
</html>
