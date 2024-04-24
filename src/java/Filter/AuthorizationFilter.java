/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Filter;

import Model.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author HC
 */
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // Khởi tạo Filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //Get infor user 
        User user = (User) httpRequest.getSession().getAttribute("account");
        String servletPath = httpRequest.getServletPath();
        // CHeck role
        if (user != null) {
            switch (user.getSetting_id()) {
                case 1:
                    if (servletPath.equals("/UsersList")
                            || servletPath.equals("/UserDetails")
                            || servletPath.equals("/SettingLists")
                            || servletPath.equals("/SettingDetails")
                            || servletPath.equals("/NewUser")
                            || servletPath.equals("/SellerDashboard")
                            || servletPath.equals("/OrderDetails")
                            || servletPath.equals("/ManageBlog")
                            || servletPath.equals("/ManageOrder")
                            || servletPath.equals("/AdminDashbord")) {
                        chain.doFilter(request, response);
                    }
                    break;
                case 2: {
                    if (servletPath.equals("/OrderList")
                            || servletPath.equals("/UserProfile")
                            || servletPath.equals("/PasswordChange")
                            || servletPath.equals("/ManageBlog")
                            || servletPath.equals("/ManageOrder")
                            || servletPath.equals("/SellerDashboard")) {
                        chain.doFilter(request, response);
                        
                    }
                    break;
                }
            }
        } else {
            sendAuthor(response);
        }

    }

    public void sendAuthor(ServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Access Denied</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("}");
        out.println(".message-container {");
        out.println("    max-width: 500px;");
        out.println("    margin: 50px auto;");
        out.println("    padding: 20px;");
        out.println("    border: 1px solid #ccc;");
        out.println("    border-radius: 5px;");
        out.println("    background-color: #f9f9f9;");
        out.println("}");
        out.println("h1 {");
        out.println("    font-size: 24px;");
        out.println("    color: #333;");
        out.println("}");
        out.println("p {");
        out.println("    font-size: 16px;");
        out.println("    color: #555;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <div class=\"message-container\">");
        out.println("    <h1>Access Denied</h1>");
        out.println("    <p>You do not have permission to access this page.</p>");
        out.println("  </div>");
        out.println("</body>");
        out.println("</html>");

    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (IOException ex) {
        }
        return stackTrace;
    }

    @Override
    public void destroy() {

    }
}
