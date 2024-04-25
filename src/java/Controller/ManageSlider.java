package Controller;


import DAO.SliderDAO;

import Model.Slider;
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
            if (u.getSetting_id() == 1) {   
                SliderDAO product = new SliderDAO();
                
                // Phân trang
                int page = 1;
                int recordsPerPage = 5;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                int offset = (page - 1) * recordsPerPage;

                ArrayList<Slider> listP = product.getSliderWithPagination(offset, recordsPerPage);
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
        

        SliderDAO SliderDAO = new SliderDAO();
        List<Slider> listP = SliderDAO.getAllSlider();

        if (search != null) {
            listP = SliderDAO.searchSlider(search);
        } 
         else {
            String status = request.getParameter("status");
            String slider_id = request.getParameter("slider_id");
            if (status != null && slider_id != null) {
                int id = Integer.parseInt(slider_id);
                int Status = Integer.parseInt(status);
                SliderDAO.UpdateStatusSlider(Status, id);
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
