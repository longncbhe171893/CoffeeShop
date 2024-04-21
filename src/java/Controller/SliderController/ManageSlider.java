package Controller.SliderController;

import DAO.ProductDAO;
import Model.Product;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ManageSlider extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Object object = session.getAttribute("account");
            User u = (User) object;
            if (u.getSetting_id() == 2) {
                ProductDAO product = new ProductDAO();
                
                // Phân trang
                int page = 1;
                int recordsPerPage = 5;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                int offset = (page - 1) * recordsPerPage;

                ArrayList<Product> listP = product.getSliderWithPagination(offset, recordsPerPage);
                int totalProducts = product.getTotalSliderCount();
                int totalPages = (int) Math.ceil(totalProducts * 1.0 / recordsPerPage);
                
                request.setAttribute("listP", listP);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("currentPage", page);
                request.getRequestDispatcher("ManageSlider.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        String firstDate = request.getParameter("firstDate");
        String secondDate = request.getParameter("secondDate");

        ProductDAO productDAO = new ProductDAO();
        List<Product> listP = productDAO.getSlider();

        if (search != null) {
            listP = productDAO.searchProduct(search);
        } else if (firstDate != null && secondDate != null) {
            Date fdate = Date.valueOf(firstDate);
            Date sdate = Date.valueOf(secondDate);
            listP = productDAO.getProductByDate(fdate, sdate);
        } else {
            String status = request.getParameter("status");
            String productId = request.getParameter("productId");
            if (status != null && productId != null) {
                int id = Integer.parseInt(productId);
                int productStatus = Integer.parseInt(status);
                productDAO.UpdateStatusSlider(productStatus, id);
            }
        }

        request.setAttribute("listP", listP);
        request.getRequestDispatcher("ManageSlider.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
