

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <script src="js/selectBox.js"></script>
        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="CSSsimple/selectBox.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .paginationn {
                display: inline-block;
                margin-left: 500px;
            }

            paginationn a {
                color: black;
                float: left;
                padding: 16px 16px;
                text-decoration: none;
            }

            .paginationn a.active {
                background-color: #4CAF50;
                color: white;
                border-radius: 5px;
            }

            .paginationn a:hover:not(.active) {
                background-color: #ddd;
                border-radius: 5px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"/>  
        <!-- END nav -->

        <section class="home-slider owl-carousel">

            <div class="slider-item" style="background-image: url(images/bg_3.jpg);" data-stellar-background-ratio="0.5">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row slider-text justify-content-center align-items-center">

                        <div class="col-md-7 col-sm-12 text-center ftco-animate">
                            <h1 class="mb-3 mt-5 bread">Order History</h1>
                            <p class="breadcrumbs"><span class="mr-2"><a href="Home">Home</a></span> <span>Cart</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-section ftco-cart">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 ftco-animate">
                        <div class="cart-list">
                            <table class="table" id="myTable">
                                <thead class="thead-primary">
                                    <tr class="text-center">

                                        <th>Order name</th>
                                        <th>
                                            <select id="selectCategory"  name="Category" onchange="doFilterCategory(value);" >
                                                <option selected value="">All status</option>
                                                <option value="Pending">Pending</option>
                                                <option value="Approve">Approve</option>
                                                <option value="Paid">Paid</option>
                                                <option value="Cancel">Cancel</option>
                                            </select>
                                        </th>
                                        <th>Order discount</th>
                                        <th>Order Date</th>
                                        <th>Seller approve</th>
                                        <th>Action</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listOrder}" var="listOrder" >
                                        <tr class="text-center">
                                            <td class="product-name">
                                                <p type="text">${listOrder.getUser().getName()}</p>
                                            </td>
                                            <td class="product-name">
                                                <p  type="text" style="font-weight: bold;color: ${listOrder.getStatus()==1?'#f89f3c':listOrder.getStatus()==2?'green':listOrder.getStatus()==4?'blue':'red'};">${listOrder.getStatus()==1?"Pending":listOrder.getStatus()==2?"Approve":listOrder.getStatus()==4?"Paid":"Cancel"}</p>
                                            </td>
                                            <td class="product-name">
                                                <p type="text" style="color: white;">${listOrder.getDiscount()} %</p>
                                            </td>
                                            <td class="product-name">
                                                <p type="text" >${listOrder.formatDate()}</p>
                                            </td>
                                            <td class="product-name">
                                                <p type="text">${listOrder.getSeller_approve()}</p>
                                            </td>
                                            <td class="product-name">
                                                <a href="SendFeedback?index=${index}&orderId=${listOrder.getId()}&user=${sessionScope['account'].getId()}">${listOrder.getStatus()==4?'Send feedback':''}</a>
                                            </td>

                                        </tr>
                                    </c:forEach>

                                </tbody>

                            </table>
                            <div class="paginationn">
                                <a href="OrderHistory?index=${backPage}&user=${sessionScope['account'].getId()}">&laquo;</a>
                                <c:forEach begin="1" end="${ePage}" var="i">
                                    <a href="OrderHistory?index=${i}&user=${sessionScope['account'].getId()}">${i}</a>
                                </c:forEach>
                                <a href="OrderHistory?index=${nextPage}&user=${sessionScope['account'].getId()}">&raquo;</a>
                            </div>
                        </div>

                    </div>
                </div>                
            </div>

        </section>

        <script type="text/javascript">
            function doFilterCategory(txtValue) {
                var input, filter, table, tr, td, i;
                input = document.getElementById("selectCategory");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[1];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }

            }
        </script>
        <jsp:include page="Footer.jsp"/>    

        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px">
            <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
            <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                    stroke="#F96D00" />
            </svg>
        </div>


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
