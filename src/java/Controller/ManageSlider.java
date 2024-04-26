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
                SliderDAO sliderDAO = new SliderDAO();

                // Phân trang
                int page = 1;
                int recordsPerPage = 5;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                int offset = (page - 1) * recordsPerPage;

                String search = request.getParameter("search");

                List<Slider> sliderList;
                int totalSliderCount;

                if (search != null && !search.isEmpty()) {
                    sliderList = sliderDAO.searchSlider(search);
                    totalSliderCount = sliderList.size();
                } else {
                    String status = request.getParameter("status");
                    String sliderId = request.getParameter("slider_id");
                    if (status != null && sliderId != null) {
                        int id = Integer.parseInt(sliderId);
                        int sliderStatus = Integer.parseInt(status);
                        sliderDAO.UpdateStatusSlider(sliderStatus, id);
                    }
                    sliderList = sliderDAO.getSliderWithPagination(offset, recordsPerPage);
                    totalSliderCount = sliderDAO.getTotalSliderCount();
                }

                int totalPages = (int) Math.ceil(totalSliderCount * 1.0 / recordsPerPage);

                List<Slider> currentSliderList = getCurrentPageSliders(sliderList, offset, recordsPerPage);

                request.setAttribute("listP", currentSliderList);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("currentPage", page);
                request.getRequestDispatcher("ManageSlider.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            // Xử lý lỗi nếu cần thiết
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private List<Slider> getCurrentPageSliders(List<Slider> sliderList, int offset, int limit) {
        int endIndex = Math.min(offset + limit, sliderList.size());
        return sliderList.subList(offset, endIndex);
    }
}