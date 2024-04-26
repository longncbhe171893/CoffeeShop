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
        <jsp:include page="headerAdmin.jsp"/>
        <!-- SIDEBAR -->

        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <div class="head-title">
                    <div class="left">
                        <h1>Contact Details </h1>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->
            <main>
                <div class="formAddBlog" >   


                    <form action="ContactDetails" method="post" >
                        <div class="modal-body">
                            <b>Subject </b><input type="text"  class="form-control" value="${contact.getSubject()}" required name="subject" readonly><br> 
                            <b>Name </b><input type="text"  class="form-control" value="${contact.getName()}" required name="name" readonly><br>  
                            <b>Email </b><input type="text"  class="form-control" value="${contact.getEmail()}" required name="email" readonly><br> 
                            <b>Phone </b><input type="text"  class="form-control" value="${contact.getPhone()}" required name="phone" readonly><br>
                            <b>Type </b><input type="text"  class="form-control" value="${contact.getSetting().getName()}" required name="type" readonly><br>
                            <b>Message </b>
                            <textarea id="" rows="5"  name="message" class="form-control"  required="" readonly>${contact.getMessage()}</textarea>

                            <br>
                            <b>Note </b>
                            <textarea id="" rows="5"  name="note" class="form-control"  >${contact.getNote()}</textarea>
                            <b>Status </b>
                                            <c:choose>
                                                <c:when test="${contact.getStatus() eq 1}">
                                                    <input type="radio" id="status_enable" name="status" value="1" checked>
                                                    <label for="status_enable">New</label>

                                                    <input type="radio" id="status_disable" name="status" value="0">
                                                    <label for="status_disable">Solved</label><br>
                                                </c:when>
                                                <c:when test="${contact.getStatus() eq 0}">
                                                    <input type="radio" id="status_enable" name="status" value="1">
                                                    <label for="status_enable">New</label>

                                                    <input type="radio" id="status_disable" name="status" value="0" checked>
                                                    <label for="status_disable">Solved</label><br>
                                                </c:when>
                                            </c:choose>
                                        </div>
                            <p style="color: red">${mess}</p>

                            <b><input type="hidden" class="form-control" required  value="${contact.getContact_id()}" name="contact_id"></b>
                            <b><input type="hidden" class="form-control" required  value="${contact.getSetting().getId()}" name="setting_id"></b>
                            
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="window.location.href = 'ContactList'">Close</button>
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

