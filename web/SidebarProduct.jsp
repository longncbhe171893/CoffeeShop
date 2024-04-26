<%-- 
    Document   : SidebarProduct
    Created on : Apr 17, 2024, 4:50:58 PM
    Author     : PHU HAI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <body>
        <form action="ProductLists" class="search-form" method="post" >
            <div class="form-group">
                <div class="icon">
                    <span class="icon-search"></span>
                </div>
                <input type="text" class="form-control" name="search1" placeholder="Search...">
            </div>
        </form>
         <form action="ProductLists" method="post" class="my-form">
            <div class="custom-select-box" style="
                 background-color: black;
                 color: wheat;
                 border-radius: 10px;
                 margin-left: 7px;
                 margin-top: 53px;
                 padding: 10px;
                 ">
                <h2>Category</h2>
                <select name="cid" onchange="this.form.submit()"  style="width: 100%;">
                    <option value="4" ${param.cid == 4 ? 'selected' : ''}>Coffee</option>
                    <option value="5" ${param.cid == 5 ? 'selected' : ''}>Tea</option>
                </select>
                
            </div>


        </form>
        <form action="ProductLists"  method="get" style="
              margin-top: -14px;
              margin-bottom: -41px;">
            <div class="custom-radio-box" style="
                 background-color: black;
                 color: wheat;
                 border-radius: 10px;
                 margin-left: 7px;
                 margin-top: 53px;
                 padding: 10px;">
                <h2>Sort by</h2>
                <label><input type="radio" name="sort" value="0" ${param.sort == 0 ? 'checked' : ''} onchange="this.form.submit()"> Sort Default</label><br>
                <label><input type="radio" name="sort" value="1" ${param.sort == 1 ? 'checked' : ''} onchange="this.form.submit()"> Newest</label><br>
                <label><input type="radio" name="sort" value="2" ${param.sort == 2 ? 'checked' : ''} onchange="this.form.submit()"> Price ascending</label><br>
                <label><input type="radio" name="sort" value="3" ${param.sort == 3 ? 'checked' : ''} onchange="this.form.submit()"> Price descending</label>
            </div>

        </form>
       

        
    </body>
</html>
