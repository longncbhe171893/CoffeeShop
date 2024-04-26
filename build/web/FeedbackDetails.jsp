<%-- 
    Document   : ContactDetails
    Created on : Apr 25, 2024, 1:43:27 AM
    Author     : HP
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="ckeditor/ckeditor.js"></script> 
        <script src="ckfinder/ckfinder.js"></script>
        <!-- My CSS -->
        <link rel="stylesheet" href="CSSsimple/adminDashbord.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/nice-select.css" rel="stylesheet">
        <title>Contact Details</title> 

    </head>
    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerSeller.jsp"/>
        <!-- SIDEBAR -->

        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <div class="head-title">
                    <div class="left">
                        <h1>Feedback Details </h1>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->
            <main>
                <div class="formAddBlog" >   


                    <form action="FeedbackDetails" method="post" >
                        <div class="modal-body">
                            <b>Name </b><input type="text"  class="form-control" value="${feedback.getUser().getName}" required name="name" readonly><br> 
                            <b>Content </b><input type="text"  class="form-control" value="${feedback.getContent()}" required name="content" readonly><br>  
                            <b>Product </b><input type="text"  class="form-control" value="${feedback.getProduct().getName}" required name="product" readonly><br> 
                            
                            <b>Note </b>
                            <textarea id="" rows="5"  name="note" class="form-control"  >${contact.getNote()}</textarea>
                            
                                        </div>
                            <p style="color: red">${mess}</p>

                            <b><input type="hidden" class="form-control" required  value="${feedback.getFeedback_id()}" name="feedback_id"></b>
                            
                            <b><input type="hidden" class="form-control" required  value="${sessionScope['account'].getId()}" name="user"></b>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="window.location.href = 'ManageFeedback'">Close</button>
                            <button type="submit" ${hidden} class="btn btn-success" value="submit">Submit</button>
                        </div>
                    </form>
                </div>
            </main>
        </section>
        

        <script >

            CKEDITOR.replace('edit', {
                filebrowserBrowseUrl: 'ckfinder/ckfinder.html',
                filebrowserUploadUrl: 'ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files'
            });
        </script>
        <script src="js/adminDashbord.js"></script>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>

