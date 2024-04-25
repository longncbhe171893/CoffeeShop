
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

        <title>Seller Dashboard</title>     
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
            .row.content {
                height: 550px
            }

            /* Set gray background color and 100% height */
            .sidenav {
                background-color: #f1f1f1;
                height: 100%;
            }

            /* On small screens, set height to 'auto' for the grid */
            @media screen and (max-width: 767px) {
                .row.content {
                    height: auto;
                }
            }
        </style>
    </head>
    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerSeller.jsp"/>

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
            <!-- NAVBAR -->

            <main>
                <div class="container-fluid">
                    <div class="row content">


                        <div class="col-sm-9">
                            <div class="well">
                                <h4>Dashboard</h4>
                                <p>Some text..</p>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <div class="well">
                                        <h4>Users</h4>
                                        <p>1 Million</p> 
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="well">
                                        <h4>Pages</h4>
                                        <p>100 Million</p> 
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="well">
                                        <h4>Sessions</h4>
                                        <p>10 Million</p> 
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="well">
                                        <h4>Bounce</h4>
                                        <p>30%</p> 
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="well">
                                        <p>Text</p> 
                                        <p>Text</p> 
                                        <p>Text</p> 
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="well">
                                        <p>Text</p> 
                                        <p>Text</p> 
                                        <p>Text</p> 
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="well">
                                        <p>Text</p> 
                                        <p>Text</p> 
                                        <p>Text</p> 
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-8">
                                    <div class="well">
                                        <p>Text</p> 
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="well">
                                        <p>Text</p> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </section>
        <!-- CONTENT -->
        <script src="js/adminDashbord.js"></script>
    </body>
</html>
