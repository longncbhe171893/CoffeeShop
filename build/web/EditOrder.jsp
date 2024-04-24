<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="ckeditor/ckeditor.js"></script> 
        <script src="ckfinder/ckfinder.js"></script>
        <!-- My CSS -->
        <link rel="stylesheet" href="CSSsimple/adminDashbord.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/nice-select.css" rel="stylesheet">
        <title>Edit Order </title> 
        <style>
            #productDetails {
                font-family: Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #productDetails td, #productDetails th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            #productDetails tr:nth-child(even){
                background-color: #f2f2f2;
            }

            #productDetails tr:hover {
                background-color: #ddd;
            }

            #productDetails th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #029ef3;
                color: white;
            }
            .wrapper {
                position: relative;
                width: 20 px;
                min-height: 20 px ;
                border-radius: 20px;
                background: hsl(20, 100%, 64%);

                display: flex;
                justify-content: center;
                align-items: center;

                .arrow {
                    width: 40px;
                    height: 40px;
                    border-radius: 20px;
                    background: hsl(40, 100%, 30%);
                    background-color: #ff413c;
                    transition: 0.2s ease-in-out;

                    .line {
                        position: absolute;
                        top: 50%;
                        left: 50%;
                        width: 20px;
                        height: 4px;
                        border-radius: 2px;
                        background: hsl(30, 100%, 80%);
                        transform-origin: center;
                        transform: translate(-50%, -50%) rotate(45deg);

                        &:nth-child(2) {
                            transform: translate(-50%, -50%) rotate(-45deg);
                        }
                    }

                    &:hover {
                        transform: rotate(25deg);
                    }
                }

            }
        </style>
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
                        <h1 >Manage Order ${tittle}</h1>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->
<<<<<<< HEAD

            <main>
                <div class="formAddBlog" id="customers">   


                    <div class="modal-body" >
                        <form action="EditOrder" method="post" enctype="multipart/form-data">
                            <b>Order ID : </b><input type="text"  class="form-control" disabled value="${order.getId()}" name="orderId"><br>
                            <b>Order name : </b><input type="text"  class="form-control" value="${order.getOrderName()}" required name="orderName"><br> 
                            <b>Order Date Time : </b><input type="text"  class="form-control" value="${order.getDate()}" required name="orderDate"><br> 
                            <b>Discount : </b><input type="text"  class="form-control" value="${order.getDiscount()}" required name="orderDiscount"><br> 
                            <b>Note : </b><input type="text"  class="form-control" value="${order.getNotes()}" name="orderNote"><br> 
=======
            <main>
                <div class="formAddBlog" id="customers">   
                    <form action="EditBlog" method="post" enctype="multipart/form-data">
                        <div class="modal-body" >
                            <b>Order ID : </b><input type="text"  class="form-control" disabled value="${order.getId()}" required name="orderId"><br>
                            <b>Order name : </b><input type="text"  class="form-control" value="${order.getOrderName()}" required name="orderName"><br> 
                            <b>Order Date Time : </b><input type="text"  class="form-control" value="${order.getDate()}" required name="orderDate"><br> 
                            <b>Discount : </b><input type="text"  class="form-control" value="${order.getDiscount()}" required name="orderDiscount"><br> 
                            <b>Note : </b><input type="text"  class="form-control" value="${order.getNotes()}" required name="orderNote"><br> 
                            <b>Product details : </b><br>
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
                            <table name="listProductOrder" id="productDetails">
                                <thead>
                                    <tr style="font-size: 17px;">

                                        <th scope="col">Product name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Amount</th>                                      
                                        <th scope="col" style="text-align: center">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach  var="listPO" items="${orderDetails}">

                                        <tr>
                                            <td hidden>
<<<<<<< HEAD
                                                <input type="text" hidden name="orderDetailId" value="${listPO.getId()}">
                                            </td>
                                            <td>
                                                <input type="text" disabled name="productName" value="${listPO.getProduct().getName()}">
                                            </td>
                                            <td>
                                                <input type="text" name="productPrice" value="${listPO.getProduct().getPrice()}">

                                            </td>
                                            <td>
=======
                                                ${listPO.getId()}
                                            </td>
                                            <td>
                                                ${listPO.getProduct().getName()}
                                            </td>
                                            <td>
                                                ${listPO.getProduct().getPrice()}
                                            </td>

                                            <td>

>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
                                                <input type="number"  id="${listPO.getId()}"  name="quantity" min="1" max="20" value="${listPO.getQuanlity()}">
                                                <button type="button"  class="${listPO.getId()}" data-blog-id="${listPO.getId()}"  style="border-radius: 10px;">
                                                    Update
                                                </button>
<<<<<<< HEAD
                                                <script>
                                                    const quantity = document.getElementById("${listPO.getId()}");
                                                    // Lắng nghe sự kiện khi nút "View" được bấm
                                                    var viewButtons = document.getElementsByClassName("${listPO.getId()}");


                                                    for (var i = 0; i < viewButtons.length; i++) {
                                                        viewButtons[i].addEventListener("click", function () {
                                                            // Lấy giá trị blog_id từ thuộc tính data-blog-id
                                                            var blogId = this.dataset.blogId;

                                                            // Mở một cửa sổ mới với URL BlogController và tham số blogId
                                                            window.location.href = "UpdateOrderDetail?index=${index}&orderId=${order.getId()}&size=${listPO.getSize()}&productPrice=${listPO.getProduct().getPrice()}&orderDetail=" + blogId + "&quantity=" + quantity.value;
                                                        });
                                                    }
                                                </script>
=======
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
                                            </td>

                                            <td>
                                                ${String.format("%.3f",listPO.getAmount())}
                                            </td>
                                            <td style="display: flex;">
<<<<<<< HEAD
                                                <button type="button"  ${o.getStatus()==3?'hidden':o.getStatus()==4?'hidden':''} onclick="window.location.href = 'DeleteProductFromOrder?orderDetailId=${listPO.getId()}&orderId=${order.getId()}'"style="border-radius: 100%;">
=======
                                                <button type="button"  ${o.getStatus()==3?'hidden':o.getStatus()==4?'hidden':''} onclick="window.location.href = 'ChangeOrderStatus?orderId=${o.getId()}&ost=3'"style="border-radius: 100%;">
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
                                                    <div class="wrapper">
                                                        <div class="arrow">
                                                            <div class="line"></div>
                                                            <div class="line"></div>
                                                        </div>
                                                    </div>
                                                </button>
                                            </td>
<<<<<<< HEAD

                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table><br>
                            <b>Total amount : </b><input type="text" disabled class="form-control" value="${String.format("%.3f",totalAmount)}" name="orderTotalAmount"><br> 

                            <input type="hidden" class="form-control" required  value="${index}" name="index"> 
                            
                            <div class="modal-footer">
                                ${message}
                                <button type="button" class="btn btn-default" onclick="window.location.href = 'ManageOrder?index=${index}'">Close</button>
                                <button type="submit" class="btn btn-success" value="submit">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
=======
                                    <script>
                                        const quantity = document.getElementById("${listPO.getId()}");
                                        // Lắng nghe sự kiện khi nút "View" được bấm
                                        var viewButtons = document.getElementsByClassName("${listPO.getId()}");


                                        for (var i = 0; i < viewButtons.length; i++) {
                                            viewButtons[i].addEventListener("click", function () {
                                                // Lấy giá trị blog_id từ thuộc tính data-blog-id
                                                var blogId = this.dataset.blogId;

                                                // Mở một cửa sổ mới với URL BlogController và tham số blogId
                                                window.location.href = "UpdateOrderDetail?orderId=${order.getId()}&size=${listPO.getSize()}&productPrice=${listPO.getProduct().getPrice()}&orderDetail=" + blogId + "&quantity=" + quantity.value;
                                            });
                                        }
                                    </script>
                                    </tr>


                                </c:forEach>

                                </tbody>
                            </table><br>
                            <b>Total amount : </b><input type="text" disabled class="form-control" value="${String.format("%.3f",totalAmount)}" required name="orderTotalAmount"><br> 

                        </div>

                        <b><input type="hidden" class="form-control" required  value="${blog.getBlog_id()}" name="blogId"></b>    
                        <b><input type="hidden" class="form-control" required  value="${sessionScope['account'].getId()}" name="user"></b>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="window.location.href = 'ManageBlog'">Close</button>
                    <button type="submit" class="btn btn-success" value="submit">Submit</button>
                </div>
                </form>


            </main>

>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
        </section>


        <script src="js/adminDashbord.js"></script>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
