
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
                        <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#myModalAddNew" >Add User</button>
                    </div>
                </div>
 <!-- Modal -->
                    <div class="modal fade" id="myModalAddNew" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Add User</h4>
                                </div>
                                <form action="AddUser" method="post" enctype="multipart/form-data">
                                   <div class="modal-body">
                                     <b>Name: </b><input type="text" class="form-control" value="" required name="name"><br>  
                                     <b>Email: </b><input type="text" class="form-control" value="" required name="email"><br>  
                                    <b>Password: </b><input type="password" class="form-control" value="" name="password"><br>
                                     <b>Address: </b><input type="text" class="form-control" value="" name="address"><br>  
                                     <b>Phone: </b><input type="text" class="form-control" value="" name="phone"><br>  
                                     <b>Sex: </b><input type="text" class="form-control" value=""  name="sex"><br>  
                                     <b>User point: </b><input type="text" class="form-control" value=""  name="userpoint"><br> 
                                  </div>
                                    <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-success" value="submit">Submit</button>
                                </div>
                             </form>
                            </div>
                        </div>
                    </div>
                                    
                <div style="margin-top: 3rem;" class="col-md-12">
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
                            <c:forEach var="p" items="${pl}">
                                <tr>
                                    <th scope="row">${p.getId()}</th>
                                    <td>${p.getName()}</td>
                                    <td>${p.getEmail()}</td>                                
                                    <td>${p.getSetting_id()==2?"Seller":"User"}</td>
                                    <td><a onclick="return confirm('Do you want to change your account status?')" href="UpdateStatusUser?uid=${p.getId()}&sid=${p.getUserStatus()}">
                                            ${p.getUserStatus()==1?"Enable":"Disnable"}</a></td>    
                                  <td> <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModal${p.getId()}">Edit</button></td>
                                </tr>
                                 <div class="modal fade" id="myModal${p.getId()}" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">User details:</h4>
                                        </div>
                                        <form action="UserDetails" method="post">
                                            <div class="modal-body">   
                                                <b>ID: </b><input type="text" class="form-control" name="id" value="${p.getId()}" readonly=""><br>
                                                <b>Name: </b><input type="text" class="form-control" value="${p.getName()}" name="name"><br>
                                                <b>Email: </b><input type="text" class="form-control" value="${p.getEmail()}" name="email" ><br>    
                                                <b>Password: </b><input type="password" class="form-control" value="${p.getPassword()}" name="password" ><br> 
                                                <b>Address: </b><input type="text" class="form-control" value="${p.getAddress()}" name="address"><br>
                                                <b>Phone: </b><input type="text" class="form-control" value="${p.getPhone()}" name="phone"><br>
                                                <b>Sex: </b><input type="text" class="form-control" value="${p.getSex()}" name="sex"><br>
                                                <b>User point: </b><input type="text" class="form-control" value="${p.getPoint()}" name="userpoint"><br>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-success" value="submit">Submit</button>
                                            </div>
                                        </form>                                 
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                        </tbody>
                    </table>
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
