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
            .search-form {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .form-input {
                display: flex;
                align-items: center;
            }

            .search-input {
                width: 200px;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .search-btn {
                padding: 8px 12px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin-left: 8px;
            }

            .select-buttons {
                display: flex;
                justify-content: space-between;
                margin-top: 10px;
            }

            .select-buttons select {
                margin-right: 10px;
            }

            .select-buttons button {
                padding: 8px 16px;
            }
        </style>
    </head>

    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerAdmin.jsp"/>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content"> 
            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Manage Product</h1>
                    </div>
                </div>

                <form action="ManageProduct" method="post" class="search-form">
                    <input type="hidden" name="index" value="1">
                    <div class="form-input">
                        <input type="search" name="search" placeholder="Search..." class="search-input">
                    </div>
                    <div class="select-buttons">
                        <b>Category:</b>
                        <select class="form-control" name="category">
                            <option value="0" ${product.getSetting_id() == 0 ? "selected" : ""}>All</option>
                            <c:forEach var="c" items="${clist}">
                                <option value="${c.getId()}" ${product.getSetting_id()==c.getId()?"selected":""}>${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <b>Status:</b>
                        <select class="form-control" name="productstatus">
                            <option value="0" ${product.getProduct_status() == 0 ? "selected" : ""}>All</option>
                            <option value="1" ${product.getProduct_status() == 1 ? "selected" : ""}>Active</option>
                            <option value="2" ${product.getProduct_status() == 2 ? "selected" : ""}>Inactive</option>
                        </select>
                        <button type="submit" class="btn btn-success" value="submit">Submit</button>
                    </div>  
                </form>
                <div style="margin-top: 3rem;" class="col-md-12">       
                    <button class="button" onclick="window.location.href = 'AddProduct';">Add Product</button>

                    <table class="table" style="margin-top: 20px; margin-bottom: 20px;">
                        <thead >
                            <tr style="font-size: 20px;">
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Category</th>
                                <th scope="col">Image</th>
                                <th scope="col">Status</th>
                                <th scope="col" colspan="2" style="text-align: center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${productlist}">
                                <tr>
                                    <th scope="row">${p.getId()}</th>
                                    <td>${p.getName()}</td>
                                    <td>${p.getPrice()}</td>
                                    <td>
                                        <c:forEach var="c" items="${clist}">
                                            <c:if test="${p.getSetting_id() == c.getId()}">
                                                ${c.getName()}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td><img style="width:150px;height:150px;"src="${p.getImage()}"></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.getProduct_status() == 1}">
                                                Active
                                            </c:when>
                                            <c:when test="${p.getProduct_status() == 2}">
                                                Inactive
                                            </c:when>
                                        </c:choose>      
                                    <td>
                                        <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModalStatusActivate${p.getId()}">Change status</button>
                                    <td> <button type="button" class="btn btn-success btn-lg" onclick="window.location.href = 'EditProduct?productId=${p.getId()}&ProductDetail=false';"">Edit Product</button></td>
                                </tr> 
                            <div class="modal fade" id="myModalStatusActivate${p.getId()}" role="dialog">
                                <!-- Modal content -->
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Change Status:</h4>
                                        </div>
                                        <form action="UpdateStatusProduct" method="get">
                                            <div class="modal-body">
                                                <p>Do you want to change status this product</p>
                                                <input type="hidden" name="pid" value="${p.getId()}">
                                                <input type="hidden" name="psid" value="${p.getProduct_status()}">
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
                <div class="pagination">
                    <a href="ManageProduct?index=${backPage}&search=${param.search}&category=${param.category}&productstatus=${param.productstatus}">&laquo;</a>
                    <c:forEach begin="1" end="${ePage}" var="i">
                        <a href="ManageProduct?index=${i}&search=${param.search}&category=${param.category}&productstatus=${param.productstatus}">${i}</a>
                    </c:forEach>
                    <a href="ManageProduct?index=${nextPage}&search=${param.search}&category=${param.category}&productstatus=${param.productstatus}">&raquo;</a>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script>
            document.querySelectorAll('.pagination a').forEach(link => {
                link.addEventListener('click', function (event) {
                    event.preventDefault();
                    const searchInput = document.querySelector('input[name="search"]').value;
                    const categoryInput = document.querySelector('select[name="category"]').value;
                    const productStatusInput = document.querySelector('select[name="productstatus"]').value;
                    const url = new URL(link.href);
                    url.searchParams.set('search', searchInput);
                    url.searchParams.set('category', categoryInput);
                    url.searchParams.set('productstatus', productStatusInput);
                    window.location.href = url.href;
                });
            });
        </script> 
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