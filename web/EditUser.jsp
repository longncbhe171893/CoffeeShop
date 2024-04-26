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
        <title>Add User</title> 
        <style>
        .col-md-6 {
    width: 50%;
    float: left;
}

/* Clearing float to prevent overlapping */
.row::after {
    content: "";
    clear: both;
    display: table;
}
.button-container {
    text-align: center; /* Căn giữa các phần tử bên trong theo chiều ngang */
}

.button-container button {
    margin: 0 10px; /* Khoảng cách giữa các nút */
}
</style>
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
                        <h1>Manage User ${title}</h1>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->
            <main>
                <div class="formAddUser" >   


                    <form action="${action}" method="post" enctype="multipart/form-data">
                        <div class="modal-body">
                          <div class="container">
    <div class="row">
        <div class="col-md-6">
            <!-- First column of form controls -->
            <b>ID: </b><input type="text" ${disable} class="form-control" value="${user.getId()}" required name="id" readonly><br>  
            <b>Name: </b><input type="text" ${disable} class="form-control" value="${user.getName()}" required name="name"><br>  
            <b>Email : </b><input type="text" ${disable}  class="form-control" value="${user.getEmail()}" required name="email"><br>
            <b>Password : </b><input type="password" ${disable}  class="form-control" value="${user.getPassword()}" required name="password"><br> 
            <b>Address : </b><input type="text" ${disable}  class="form-control" value="${user.getAddress()}" required name="address"><br>         
            <b>Phone : </b><input type="text" ${disable}  class="form-control" value="${user.getPhone()}" required name="phone"><br>
        </div>
        <div class="col-md-6">
            <!-- Second column of form controls -->
            <b>Sex :</b>
            <select class="form-control" style="display: block;" name="sex">
                <option value="1" ${user.getSex() == 1 ? "selected" : ""}>Nam</option>
                <option value="2" ${user.getSex() == 2 ? "selected" : ""}>Nữ</option>
            </select><br>
            <b>Role :</b>
            <select class="form-control" style="display: block;" name="role">
                <c:forEach var="r" items="${rlist}">
                    <option value="${r.getId()}" ${user.getSetting_id()==r.getId()?"selected":""} >${r.getName()} </option>
                </c:forEach>
            </select><br>
            <b>User point: </b><input type="text"${disable} class="form-control" value="${user.getPoint()}" name="point"><br>
            <div>
                <img style="width: 200px;" ${disable} <c:if test = "${user.getImage()==null}">hidden</c:if>  src = "${user.getImage()}"  alt = "Curent image"> <br>
                <b>Image:</b><input type="file" ${disable}  class="form-control" required src="${user.getImage()}" name="img" accept="image/*"><br>
            </div>
        </div>
    </div>
</div>  
                        </div>

                        <div class="modal-footer">
    <div class="button-container"> 
        <button type="button" class="btn btn-default" onclick="window.location.href = 'ManagerUser?index=1'">Close</button>
        <button type="submit" class="btn btn-success" value="submit">Submit</button>
    </div> 
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