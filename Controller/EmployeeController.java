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

public class EmployeeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
        HttpSession session = request.getSession();
        if (op != null && op.equalsIgnoreCase("deleteEmployee")) {
            int empId = Integer.parseInt(request.getParameter("id"));
            System.out.println("id:" + empId);
            EmployeeDao udao = new EmployeeDao();
            if (udao.removeEmployeeById(empId)) {
                response.sendRedirect("admin/viewEmployee.jsp");
            }

        } else if (op != null && op.equalsIgnoreCase("updateStatus")) {
            int sreqId = Integer.parseInt(request.getParameter("sreqId"));
            System.out.println("id recieved from assignTo:" + sreqId);
            int empId = Integer.parseInt(request.getParameter("empId"));
            System.out.println("empId recieved from assignTo " + empId);
            EmployeeDao udao = new EmployeeDao();
            String loc_id = (String) session.getAttribute("loc_id");
            System.out.println(loc_id);
            if (udao.updateStatus(empId, sreqId, op) && udao.updateRequestStatus(empId, sreqId, op) && udao.addDispatchLocation(loc_id, empId)) {
                response.sendRedirect("admin/processingRequest.jsp?empId=" + empId);
            }

        } else if (op != null && op.equalsIgnoreCase("complete")) {
            int sreqId = Integer.parseInt(request.getParameter("sreqId"));
            System.out.println("id recieved from Employee Dashboard: " + sreqId + " and op = " + op);
            int empId = Integer.parseInt(request.getParameter("empId"));
            System.out.println("empId recieved from Employee Dashboard: " + empId);
            System.out.println("Date = " + java.time.LocalDate.now());
            EmployeeDao udao = new EmployeeDao();
            if (udao.updateStatus(empId, sreqId, op) && udao.updateRequestStatus(empId, sreqId, op)) {
                response.sendRedirect("Employee/employeeDashboard.jsp?empId=" + empId);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        PrintWriter out = response.getWriter();

        if (op != null && op.equalsIgnoreCase("add")) {

            HttpSession session = request.getSession();
            employee emp = (employee) session.getAttribute("emp");
            System.out.println("Employee Bean :" + emp);
            System.out.println("Employee name:" + emp.getName());
            EmployeeDao edao = new EmployeeDao();
            String ImagePath = "";
            ImagePath = FileUploader.getUploadedPath1(getServletContext(), "media/employeePic", request);
            emp.setPic(ImagePath);
            if (edao.addEmployee(emp)) {
                System.out.println("successfully inserted");
                out.println("<script> alert('Inserted Successfully!!');</script>");
                response.sendRedirect("admin/adminDashboard.jsp");
            } else {
                System.out.println("failure occurs");
            }
        } else if (op != null && op.equalsIgnoreCase("login")) {
            String emailId = request.getParameter("emailId");
            String password = request.getParameter("password");
            EmployeeDao edao = new EmployeeDao();
            employee emp = new employee();
            HttpSession session = request.getSession();
            if (edao.login(emailId, password)) {
                emp = edao.getEmployeeById(emailId);
                session.setAttribute("emp", emp);//"user"-- session name
                System.out.println("Login Succesfully!!!");
                response.sendRedirect("Employee/employeeDashboard.jsp");
            }
        } else if (op != null && op.equalsIgnoreCase("update")) {
            HttpSession session = request.getSession();
            employee emp = (employee) session.getAttribute("emp");
            EmployeeDao edao = new EmployeeDao();
            String imagePath = "";
            imagePath = FileUploader.getUploadedPath1(getServletContext(), "media/employeePic", request);
            if (imagePath.equals("media/employeePic/")) {
               emp.setPic(emp.getPic());
            } else {
                emp.setPic(imagePath);
            }
            if (edao.update(emp)) {
                session.removeAttribute("emp");
                out.println("Employee Updates Successfully !");
                response.sendRedirect("Employee/loginEmployee.jsp");
            }
        }
    }
}
