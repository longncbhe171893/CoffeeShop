<%-- 
    Document   : SidebarBlog
    Created on : Apr 17, 2024, 3:59:24 PM
    Author     : PHU HAI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <body>
        <form action="Blog" class="search-form" method="post" >
            <div class="form-group">
                <div class="icon">
                    <span class="icon-search"></span>
                </div>
                <input type="text" class="form-control" name="search2" placeholder="Search...">
            </div>
        </form>
        <form action="Blog" method="post" class="my-form">
            <div class="custom-select-box" style="

                 color: wheat;
                 border-radius: 10px;

                 ">
                <h2>Category</h2>
                <select name="cid" onchange="this.form.submit()"  style="width: 100%;">
                    <option value="6" ${param.cid == 6 ? 'selected' : ''}>Preparation</option>
                    <option value="7" ${param.cid == 7 ? 'selected' : ''}>TradeMark</option>
                </select>

            </div>


        </form>
        </br>            </br>

        <h2 class="ftco-heading-2">Recent Blog</h2>
    <c:forEach var="bl" items="${bl}">
        <div class="block-21 mb-4 d-flex">
            <a href="BlogController?blogId=${bl.getBlog_id()}"><img class="blog-img mr-4" src="${bl.getBlog_image()}" alt="image"></a>
            <div class="text">
                <h3 class="heading"><a href="BlogController?blogId=${bl.getBlog_id()}">${bl.getBlog_title()}</a></h3>
                <div class="meta">
                    <div><a href="BlogController?blogId=${bl.getBlog_id()}"><span class="icon-calendar"></span> ${bl.getPost_date()}</a></div>
                    <div><a href="BlogController?blogId=${bl.getBlog_id()}"><span class="icon-person"></span> ${bl.getUser().getName()}</a></div>
                </div>
            </div>
        </div>
    </c:forEach>

</body>
</html>
