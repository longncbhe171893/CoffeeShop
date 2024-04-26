

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Feedback Products</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <script src="js/Toast.js"></script>
        <link href="scss/ToastCss.css" rel="stylesheet">
        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .buttonn {
                background-color: #fde19a;
                border: none;
                color: #151111;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
            .button {
                text-decoration: none;
                line-height: 1;
                padding-left: 10px;
                padding-right: 10px;
                border-radius: 1.5rem;
                overflow: hidden;
                position: relative;
                box-shadow: 10px 10px 20px rgba(0,0,0,.05);
                background-color: #fff;
                color: #121212;
                border: none;
                cursor: pointer;
            }

            .button-decor {
                position: absolute;
                inset: 0;
                background-color: var(--clr);
                transform: translateX(-100%);
                transition: transform .3s;
                z-index: 0;
            }

            .button-content {
                display: flex;
                align-items: center;
                font-weight: 600;
                position: relative;
                overflow: hidden;
            }

            .button__icon {
                width: 48px;
                height: 40px;
                background-color: var(--clr);
                display: grid;
                place-items: center;
            }

            .button__text {
                display: inline-block;
                transition: color .2s;
                padding: 2px 1.5rem 2px;
                padding-left: .75rem;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
                max-width: 150px;
            }

            .button:hover .button__text {
                color: #fff;
            }

            .button:hover .button-decor {
                transform: translate(0);
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"/>  
        <!-- END nav -->
        <div id="toast"></div>
        <section class="home-slider owl-carousel">

            <div class="slider-item" style="background-image: url(images/bg_3.jpg);" data-stellar-background-ratio="0.5">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row slider-text justify-content-center align-items-center">

                        <div class="col-md-7 col-sm-12 text-center ftco-animate">
                            <h1 class="mb-3 mt-5 bread">Feedback Details</h1>
                            <p class="breadcrumbs"><span class="mr-2"><a href="Home">Home</a></span> <span>Cart</span>
                            </p>
                        </div>
                    </div>
                </div>


            </div>
        </section>

        <section class="ftco-section ftco-cart">
            <div class="container">
                <a href="OrderHistory?index=1&user=${sessionScope['account'].getId()}"  class="buttonn">Order history</a>
                <div class="row">
                    <div class="col-md-12 ftco-animate">
                        <div class="cart-list">
                            <h2>Feedback</h2>
                            
                            <form id="myForm" action="FeedbackDetail" method="post" name="SendFeddback" >
                                <b><input type="hidden" class="form-control"   value="${orderId}" name="orderId"></b>    
                                <b><input type="hidden" class="form-control"   value="${index}" name="index"></b>    
                                <b><input type="hidden" class="form-control"   value="${product.getId()}" name="productId"></b>    
                                <b><input type="hidden" class="form-control"   value="${sessionScope['account'].getId()}" name="user"></b>
                                <label>How do you rate your overall experience?</label>

                                <h3>${product.getName()}</h3>
                                <img src="${product.getImage()}" style="border-radius: 8px;" alt="Paris" width="300" height="300">
                                <h3>${product.getDescreption()}</h3>
                                <div class="mb-3 d-flex flex-row py-1">
                                    <div class="form-check mr-3">
                                        <input class="form-check-input" type="radio" name="rating" id="rating_bad" value="bad">
                                        <label class="form-check-label" for="rating_bad">
                                            Bad
                                        </label>
                                    </div>

                                    <div class="form-check mx-3">
                                        <input class="form-check-input" type="radio" name="rating" id="rating_good" value="good">
                                        <label class="form-check-label" for="rating_good">
                                            Good
                                        </label>
                                    </div>

                                    <div class="form-check mx-3">
                                        <input class="form-check-input"  type="radio" name="rating" id="rating_excellent" value="excellent">
                                        <label class="form-check-label" for="rating_excellent">
                                            Excellent!
                                        </label>
                                    </div>
                                </div>
                                <div class="mb-4">
                                    <label class="form-label"  for="feedback_comments">Comments:</label>
                                    <textarea class="form-control" required rows="1" name="comments" id="feedback_comments" ></textarea>
                                </div>

                                <button style="text-decoration: none;
                                        line-height: 1;
                                        padding-left: 25px;
                                        padding-right: 25px;
                                        border-radius: 15rem;
                                        overflow: hidden;
                                        position: relative;
                                        box-shadow: 10px 10px 20px rgba(0,0,0,.05);
                                        background-color: #fff;
                                        color: #121212;
                                        border: none;
                                        cursor: pointer;" type="button" onclick="showSuccessToast();" class="button" >Post</button>
                            </form>
                        </div>
                    </div>
                </div>               
            </div>
        </section>

        <jsp:include page="Footer.jsp"/>    

        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px">
            <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
            <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                    stroke="#F96D00" />
            </svg></div>

        <script>
            var form = document.getElementById("myForm");
            function showSuccessToast() {

                toast({
                    title: "Send feedback successfully!",
                    message: "Now you will back to Order History",
                    type: "success",
                    duration: 5000
                });
                reloadPageAfterXSeconds(2);
            }
            function reloadPageAfterXSeconds(seconds) {
                setTimeout(function () {
                    form.submit();
                }, seconds * 1000); // Convert seconds to milliseconds
            }

        </script>

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
        <script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>

    </body>

</html>
