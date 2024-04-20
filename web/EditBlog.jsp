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
        <title>Add Blog</title> 

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
                        <h1>Manage Blogs ${title}</h1>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->
            <main>
                <div class="formAddBlog" >   


                    <form action="${action}" method="post" enctype="multipart/form-data">
                        <div class="modal-body">
                            <b>Title: </b><input type="text" ${disable} class="form-control" value="${blog.getBlog_title()}" required name="title"><br>  
                            <b>Content: </b>
                            <textarea id="edit" rows="5" ${disable} name="content" class="form-control"  required="">${blog.getContent()}</textarea>

                            <b>Category :</b>
                            <select   name="category" ${disable} >                            
                                <c:forEach var="categoryBlog" items="${categoryBlog}" >
                                    <option value="${categoryBlog.getId()}" <c:if test = "${blog.getSetting().getSetting_id()==categoryBlog.getId()}">selected</c:if>>${categoryBlog.getName()}</option>
                                </c:forEach>

                            </select>
                            <br>
                            <b>Short description : </b><input type="text" ${disable}  class="form-control" value="${blog.getDescription()}" required name="short_description"><br>
                            <div>
                                <img style="width: 200px;" ${disable} <c:if test = "${blog.getBlog_id()==null}">hidden</c:if>  src = "${blog.getBlog_image()}"  alt = "Curent image"> <br>
                                <b>Image:</b><input type="file" ${disable}  class="form-control" required src="${blog.getBlog_image()}" name="img" accept="image/*"><br>
                            </div>

                            <b><input type="hidden" class="form-control" required  value="${blog.getBlog_id()}" name="blogId"></b>    
                            <b><input type="hidden" class="form-control" required  value="${sessionScope['account'].getId()}" name="user"></b>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="window.location.href = 'ManageBlog'">Close</button>
                            <button type="submit" class="btn btn-success" value="submit">Submit</button>
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
