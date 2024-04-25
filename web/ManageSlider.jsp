<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="CSSsimple/adminDashbord.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/nice-select.css" rel="stylesheet">
        <title>Coffee</title>
        <script src="ckeditor/ckeditor.js"></script>
        <script src="ckfinder/ckfinder.js"></script>
        <title>Seller Dashboard</title>

    </head>

    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerAdmin.jsp" />
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu'></i>
                <form action="ManageSlider" method="post">
                    <div class="form-input">
                        <input type="search" name="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                    </div>
                </form>
            </nav>
            <!-- NAVBAR -->
            
                
            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Manage Slider</h1>
                    </div>

                    
                </div>


                <div style="margin-top: 3rem;" class="col-md-12">
                    <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#myModalAddNew">Add Slider</button>
                    <!-- Modal -->
                    <div class="modal fade" id="myModalAddNew" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Add Slider</h4>
                                </div>
                                <form action="AddSlider" method="post" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <b>Title: </b><input type="text" class="form-control" value="" required name="title"><br>

                                        <b>Image:</b><input type="file" class="form-control" required value="" name="img"><br>
                                        <b>URL </b><input type="text" class="form-control" value="" required name="url"><br>
                                        <b><input type="hidden" class="form-control" required value="${sessionScope['account'].getId()}" name="user"></b>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-success" value="submit">Submit</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <table class="table" id="myTable" style="margin-top: 20px; margin-bottom: 20px;">

                        <thead >
                            <tr style="font-size: 20px;">
                                <th scope="col">ID</th>
                                <th scope="col">Title</th>
                                <th scope="col">Image</th>
                                <th scope="col">URL</th>

                                <th scope="col" >Status</th>


                                <th scope="col" colspan="3" style="text-align: center">Action</th>
                            </tr>

                        </thead>

                        <c:forEach var="s" items="${listP}">
                            <tr>
                                <th scope="row">${s.getSlider_id()}</th>
                                <td>${s.getTitle()}</td>
                                <td><img style="width:150px;height:150px;"src="${s.getImg()}"></td>
                                <td><a target="blank" href="${s.getUrl()}">${s.getUrl().substring(22)} </a></td>   



                                <td><b ${sessionScope['account'].getSetting_id()==2?'hidden':''} style="display: block;color: ${s.getStatus()==2?'red':'green'}; " >${s.getStatus()==1?"Is Slider":"Not SLider"}</b></td>
                                <td>
                                    <a href="UpdateStatusSlider?status=${s.getStatus()==1?2:1}&id=${s.getSlider_id()}" class="btn- btn-danger  btn-lg" style="display: block; background-color: ${s.getStatus()==2?'red':'green'}; " >${s.getStatus()==1?"Slider":"Not SLider"}</a>
                                </td>

                                <td> <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModal${s.getSlider_id()}">Edit</button></td>


                            </tr>
                            <div class="modal fade" id="myModal${s.getSlider_id()}" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Edit Slider:</h4>
                                        </div>
                                        <form action="EditSlider" method="post" enctype="multipart/form-data">
                                            <div class="modal-body">
                                                <b>ID: </b><input type="text" class="form-control" name="id" value="${s.getSlider_id()}" readonly=""><br>
                                                <b>Title: </b><input type="text" class="form-control" value="${s.getTitle()}" name="title"><br>

                                                <b>Current Image: </b><br>
                                                <img src="${s.getImg()}" alt="Current Image" style="max-width: 100%;"><br><br>

                                                <b>New Image: </b><input type="file" class="form-control" name="image">
                                                <b>URL: </b><input type="text" class="form-control" value="${s.getUrl()}" name="url"><br>
                                                <b>Status: </b><input type="hidden" class="form-control" name="status" value="${s.getStatus()}">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-success" value="submit">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>


                    </div>
                </c:forEach>
                </table>
                <!-- Pagination -->
                <c:if test="${totalPages > 1}">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                            <a class="page-link" href="ManageSlider?page=1">&laquo;</a>
                        </li>
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="ManageSlider?page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                            <a class="page-link" href="ManageSlider?page=${totalPages}">&raquo;</a>
                        </li>
                    </ul>
                </c:if>
                <!-- End Pagination -->
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->

        <script>
            function checkDate() {
                var firstDate = document.getElementsByName("firstDate")[0].value;
                var secondDate = document.getElementsByName("secondDate")[0].value;

                if (firstDate && secondDate && new Date(secondDate) < new Date(firstDate)) {
                    alert("Second date must be after the first date.");
                    return false;
                }
                return true;
            }
        </script>
        <script>

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
