
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
       <link rel="stylesheet" href="CSSsimple/adminDashbord.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/nice-select.css" rel="stylesheet">
        <script src="ckeditor/ckeditor.js"></script> 
        <script src="ckfinder/ckfinder.js"></script>
        <title>Admin Dashboard</title> 
        <style>   
        .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border-radius: 5px;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
                border-radius: 5px;
            }
            </style>
    </head>
    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerDashbord.jsp"/>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <form action="ManagerUser" method="post">
                    <div class="form-input">
                        <input type="search" name="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                    </div>
                </form>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Manage User</h1> 
                    </div>
                </div>
 <!-- Modal -->
                                    
                <div style="margin-top: 3rem;" class="col-md-12">
                    <div style="margin-top: 3rem;" class="col-md-12">
                    <button class="button" onclick="window.location.href = 'EditUser';">Add User</button>
                    <table class="table">
                        <thead >
                            <tr style="font-size: 20px;">
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>                      
                                <th scope="col">Role</th>
                                <th scope="col">Status</th>
                                <th scope="col">Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${userlist}">
                                <tr>
                                    <th scope="row">${p.getId()}</th>
                                    <td>${p.getName()}</td>
                                    <td>${p.getEmail()}</td>                                
                                    <td>${p.getSetting_id()==2?"Seller":"User"}</td>
                                    <td><a onclick="return confirm('Do you want to change your account status?')" href="UpdateStatusUser?uid=${p.getId()}&sid=${p.getUserStatus()}">
                                            ${p.getUserStatus()==1?"Enable":"Disnable"}</a></td>    
                                   <td> <button type="button" class="btn btn-success btn-lg" onclick="window.location.href = 'EditUser?userId=${p.getId()}&UserDetail=false';"">Edit User</button></td>
                                </tr>   
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
 <div class="pagination">
                    <a href="ManagerUser?index=${backPage}">&laquo;</a>
                    <c:forEach begin="1" end="${ePage}" var="i">
                        <a href="ManagerUser?index=${i}">${i}</a>
                    </c:forEach>
                    <a href="ManagerUser?index=${nextPage}">&raquo;</a>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
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
