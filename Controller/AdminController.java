package Controller;

import Utility.FileUploader;
import beans.*;
import java.io.IOException;
import daos.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
        HttpSession session = request.getSession();
        if (op != null && op.equalsIgnoreCase("rejected")) {
            int sreqId = Integer.parseInt(request.getParameter("sreqId"));
            System.out.println("id recieved from Admin Dashboard " + sreqId + " and op = " + op);
            AdminDao adao = new AdminDao();
            if (adao.updateRequestStatus(sreqId)) {
                response.sendRedirect("admin/adminDashboard.jsp");
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
