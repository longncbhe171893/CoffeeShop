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
        <title>Add Product</title> 

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
                        <h1>Manage Product ${title}</h1>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->
            <main>
                <div class="formAddProduct" >   


                    <form action="${action}" method="post" enctype="multipart/form-data">
                        <div class="modal-body">
                            <b>ID: </b><input type="text" ${disable} class="form-control" value="${product.getId()}" required name="id" readonly><br>  
                            <b>Name: </b><input type="text" class="form-control" value="${product.getName()}" name="name"><br>
                          <b>Price: </b><input type="number" min="0" max="4000" step="0.5" class="form-control" value="${product.getPrice()}" name="price"><br>
                          <b>Category:</b>
                          <div style="height: 50px; width: 100%">
                        <select class="form-control" style="display: block;" name="category">
                            <c:forEach var="c" items="${clist}">
                                <option value="${c.getId()}" ${product.getSetting_id()==c.getId()?"selected":""}>${c.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>  
                      <b>Description: </b><textarea class="form-control" name="descri">${product.getDecription()}</textarea><br>
                            <div>
                                <img style="width: 200px;" ${disable} <c:if test = "${product.getImage()==null}">hidden</c:if>  src = "${product.getImage()}"  alt = "Curent image"> <br>
                                <b>Image:</b><input type="file" ${disable}  class="form-control" required src="${product.getImage()}" name="img" accept="image/*"><br>
                            </div>

                            <b><input type="hidden" class="form-control" required  value="${product.getId()}" name="productId"></b>    
                            <b><input type="hidden" class="form-control" required  value="${sessionScope['account'].getId()}" name="user"></b>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="window.location.href = 'ManageProduct?index=1'">Close</button>
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