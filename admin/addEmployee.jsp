<%@page contentType="text/html" pageEncoding="UTF-8" import="beans.employee,beans.city,daos.*,java.util.ArrayList,java.sql.*"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adding Employee</title>
        <jsp:include page="base.jsp"></jsp:include>
        </head>

        <body id="page-top">

            <!-- Page Wrapper -->
            <div id="wrapper">

                <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"></jsp:include>
                <!-- End of Sidebar -->

                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                        <!-- Topbar -->
                    <jsp:include page="navbar.jsp"></jsp:include>
                        <!-- End of Topbar -->

                        <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h3 mb-0 text-gray-800">Fill the Employee Details Here </h1>
                            </div>

                            <!-- Content Row -->
                            <div class="row">

                            <jsp:useBean id="emp" class="beans.employee" scope="session"></jsp:useBean>
                                <div class="container">
                                     <div class="col col-md-3" style="position: fixed;right: 0px;">
                                    <% if (request.getParameter("submit") != null) {%>
                                    <jsp:setProperty name="emp" property="*"></jsp:setProperty>
                                        <form action="../EmployeeController?op=add" method="post" enctype="multipart/form-data">
                                            <input type="file" class="btn btn-success form-control" name="pic" id="pic"/>
                                            <input type="submit" value="Add to Database" class="btn btn-primary"/>
                                        </form>
                                    <%
                                            }%>
                                </div>
                                    <div class="row">
                                        <div class="col col-md-7">
                                            <form class="form">
                                                <table class="table">

                                                    <tr>
                                                        <td>Enter Employee Name </td>
                                                        <td><input type="text" id="name" name="name" required="required" value="${employee.name}" class="form-control" /></td>
                                                </tr> 


                                                <tr>
                                                    <td>Enter Employee's Contact </td>
                                                    <td><input type="text" name="mobile" value="${employee.mobile}" maxlength="13" id="mobile" class="form-control"  />

                                                    </td>
                                                </tr> 

                                                <tr>
                                                    <td>Enter Employee's Email </td>
                                                    <td><input type="email" name="emailId"id="emailId" value="${employee.emailId}" class="form-control"  />

                                                    </td>
                                                </tr> 

                                                <tr>
                                                    <th>Select Employee City</th>
                                                    <td>
                                                        <select style="height: 50px" class="col-md-12 " id="city" name="city" >
                                                            <option value="-1">Select City</option>
                                                            <%

                                                                UserDao cdo = new UserDao();
                                                                ArrayList<city> city_name = new ArrayList();
                                                                city_name = cdo.getCityName();

                                                                for (city city : city_name) {%>                                

                                                            <option value="<%=city.getCity_id()%>"> <%if (String.valueOf(city.getCity_id()).equals(String.valueOf(request.getParameter("city_id")))) {
                                                                    out.println("active");
                                                                }%><%=city.getCity_name()%> 
                                                            </option> 

                                                            <%}%>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Enter Employee's Password </td>
                                                    <td><input type="password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" name="password" id="password" value="${employee.password}" class="form-control"  />
                                                        </br>  password must contains atleast one lower and 8 char and one special char</td>
                                                </tr> 

                                                <tr>
                                                    <td>Confirm Password </td>
                                                    <td><input type="password" name="cpassword" value="${employee.password}" id="cpassword" class="form-control"  /></td>
                                                </tr> 


                                            </table>
                                            <input type="submit" value="save and Next" name="submit" id ="submit" class="btn btn-primary"/>
                                        </form>   
                                    </div>
                                                  

                                </div>
                             
                            </div>
                        </div>

                        <a class="scroll-to-top rounded" href="#page-top">
                            <i class="fas fa-angle-up"></i>
                        </a>

                        <!-- Logout Modal-->
                        <jsp:include page="LogoutModal.jsp"></jsp:include>

                        <jsp:include page="footer.jsp"></jsp:include>

                        </body>

                        </html>
