<%@page contentType="text/html" pageEncoding="UTF-8" import="beans.employee,beans.city,daos.*,java.util.ArrayList,java.sql.*"%>
<script>
    function searchState(city_id, x)
    {

        ajax = new XMLHttpRequest();
        ajax.open("GET", "../UserController?op=searchState&city_id=" + city_id, true);
        ajax.send();

        ajax.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200)
                x.innerHTML = this.responseText;
        };
    }


</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule Pickup</title>
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
                                <h1 class="h3 mb-0 text-gray-800">Fill the Requested Details Here </h1>
                            </div>

                            <!-- Content Row -->
                            <div class="row">

                            <jsp:useBean id="emp" class="beans.employee" scope="session"></jsp:useBean>
                                <div class="container">
                                <jsp:useBean class="beans.servicereq" id="serv" scope="session" ></jsp:useBean>  
                                    <div class="container">
                                        <div class="col col-md-3" style="position: fixed;right: 0px;">
                                        <%
                                            if (request.getParameter("submit") != null) {
                                        %>
                                        <jsp:setProperty name="serv" property="*"></jsp:setProperty>

                                            <form action="../UserController?op=requestPickup" method="post" enctype="multipart/form-data">
                                                First Pic
                                                <input type="file" value="Browse File" id="garbagePic" name="garbagePic1" class="btn-primary"><br>
                                                Second Pic
                                                <input type="file" value="Browse File" id="garbagePic" name="garbagePic2" class="btn-primary"><br>
                                                Third Pic
                                                <input type="file" value="Browse File" id="garbagePic" name="garbagePic3" class="btn-primary"><br>
                                                Fourth Pic
                                                <input type="file" value="Browse File" id="garbagePic" name="garbagePic4" class="btn-primary"><br>
                                                Fifth Pic
                                                <input type="file" value="Browse File" id="garbagePic" name="garbagePic5" class="btn-primary"><br>
                                                <br>
                                                <button type="submit" value="Send Request" class="btn-primary">Send Request</button>

                                            </form>

                                        <% }
                                        %>
                                    </div>
                                    <div class="row">
                                        <form class="form">
                                            <table>

                                                <tr>                          
                                                    <th>Email Id </th>
                                                    <td><input type="email" required="required" name="emailId" id="emailId" class="form-control" placeholder="Email Address"></td>
                                                </tr>
                                                <tr>                          
                                                    <th>Mobile </th>
                                                    <td><input type="text" aria-required="true" size="30" value="" name="mobile" id="mobile" class="form-control" placeholder="Phone Number"></td>
                                                </tr>
                                                <tr>
                                                    <th>Select City</th>
                                                    <td>
                                                        <select style="height: 50px" class="col-md-12 " id="city" name="city" onchange="searchState(this.value, location)" >
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
                                                    <th>Select Location</th>
                                                    <td>
                                                        <select style="height: 50px" class="col-md-12" name="location" id="location">
                                                            <option value="-1">Select Location</option>                            
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>Date</th>
                                                    <td>
                                                        <input type="date" aria-required="true" value="Date" name="date" id="pickup-date" class="form-control" placeholder="Date">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>Select Type</th>
                                                    <td>
                                                        <select style="height: 50px" class="col-md-12" name="type" id="type">
                                                            <option value="-1">Select Type</option>
                                                            <option value="Road Side Garbage">Road Side Garbage</option>
                                                            <option value="Bio Waste">Bio Waste</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>Location Details</th>
                                                    <td>
                                                        <textarea aria-required="true" name="locDetail" id="locDetail" class="form-control" placeholder="Give Details of Location"></textarea>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input type="submit" id="submit" name="submit" value="Save And Next" class="btn btn-primary">
                                                    </td></tr>
                                            </table>
                                        </form>

                                    </div>

                                    <a class="scroll-to-top rounded" href="#page-top">
                                        <i class="fas fa-angle-up"></i>
                                    </a>

                                    <!-- Logout Modal-->
                                    <jsp:include page="LogoutModal.jsp"></jsp:include>

                                  

                                    </body>

                                    </html>
