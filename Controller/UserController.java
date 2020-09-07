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
import java.util.ArrayList;

public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
        if (op != null && op.equalsIgnoreCase("searchState")) {
            System.out.println("stage 1");

            response.setContentType("text/html");
            String city_id = request.getParameter("city_id");
            System.out.println("city id in doGet controller " + city_id);
            UserDao udao = new UserDao();
            System.out.println("user dao in controller " + udao);
            ArrayList<location> loc_name = new ArrayList();
            loc_name = udao.getLocationName(city_id);
            String result = "<option value=\"-1\">Select Location</option>";
            for (location loc : loc_name) {
                result += "<option value= '" + loc.getLoc_id() + "'>" + loc.getLoc_name() + "</option>";
            }
            System.out.println(result);
            out.println(result);
        } else if (op != null && op.equalsIgnoreCase("check_email")) {
            String emailId = request.getParameter("emailId");
            UserDao ud = new UserDao();
            if (emailId == null || emailId.equals("")) {
                out.println("<font size='4'>Please provide Email</font>");
                return;
            }
            if (ud.isEmailExist(emailId)) {
                out.println("<font color='red' size='4'>Sorry This Email already exist!!!</font>");
            } else {
                out.println("<font color='blue' size='4'>Congratulations! This Email is not registered!!!</font>");
            }
        } 
        else if (op != null && op.equalsIgnoreCase("deleteUser")) 
        {
            
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("id:" + id);
            UserDao udao = new UserDao();
            if (udao.removeUserById(id)) {
                response.sendRedirect("admin/viewUsers.jsp");
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        PrintWriter out = response.getWriter();

        if (op != null && op.equalsIgnoreCase("add")) {
        /*    
            HttpSession session = request.getSession();
            users user = (users) session.getAttribute("user");
            System.out.println("User Bean :" + user);
            UserDao udao = new UserDao();
            if (udao.addUser(user)) {
                System.out.println("successfully inserted");
                response.sendRedirect("Login.jsp");
            } else {
                System.out.println("failure occurs");
            }
*/
        } else if (op != null && op.equalsIgnoreCase("login")) {
            String emailId = request.getParameter("emailId");
            String password = request.getParameter("password");
            UserDao udao = new UserDao();
            users user = new users();
            HttpSession session = request.getSession();
            if (udao.validate(emailId, password)) {
                user = udao.getUserById(emailId);
                session.setAttribute("user", user);//"user"-- session name
                System.out.println("Login Succesfully!!!");
                response.sendRedirect("User/userDashboard.jsp");
            }
        } else if (op != null && op.equalsIgnoreCase("requestPickup")) {
            System.out.println("stage 1 in request Pickup");
            HttpSession session = request.getSession();
            servicereq serv = (servicereq) session.getAttribute("serv");
            System.out.println("User Bean in requestPickupController :" + serv);
            System.out.println("Session in requestPickupController :" + session);
            UserDao udao = new UserDao();
            ArrayList<String> imagePath = new ArrayList();
            imagePath = FileUploader.getUploadedPath(getServletContext(), "media/garbagePic", request);
            String images = "";
            for (String image : imagePath) {
                images += image + ",";
            }

            System.out.println("stage 3 imagepath " + imagePath);
            serv.setGarbageImage(images);
            System.out.println("imagepath from beans " + serv.getGarbageImage());
            if (udao.addPickupDetail(serv)) {
                System.out.println("successfully inserted");
                response.sendRedirect("User/userDashboard.jsp");
            } else {
                System.out.println("failure occurs");
            }

        } else {
            System.out.println("something went wrong!!");
        }

    }

}
