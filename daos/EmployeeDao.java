package daos;

import beans.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import jdbc.ConnectionPool;

public class EmployeeDao {

    public boolean addEmployee(employee emp) {
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {

                String sql = "insert into employee(name,emailId,password,pic,mobile,city_id) values(?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, emp.getName());
                ps.setString(2, emp.getEmailId());
                ps.setString(3, emp.getPassword());
                ps.setString(4, emp.getPic());
                ps.setString(5, emp.getMobile());
                ps.setString(6, emp.getCity());

                int n = ps.executeUpdate();
                System.out.println("n=" + n);
                sql = "select empId from employee order by empId desc limit 1 ";
                ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                int empId = -1;
                if (rs.next()) {
                    empId = rs.getInt("empId");
                    ps = con.prepareStatement("insert into empdetails(empId,reqStatus,empStatus) values(?,'Pending','Free')");
                    ps.setInt(1, empId);
                    n = ps.executeUpdate();
                }
                if (n > 0) {
                    status = true;

                    System.out.println("record inserted !");
                }
            }

        } catch (Exception e) {
            System.out.println("Exception Occurs" + e.getMessage());
        }

        return status;
    }

    public boolean login(String emailId, String password) {
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(
                        "select * from employee where emailId=? and password=?");
                ps.setString(1, emailId);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                status = rs.next();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public employee getEmployeeById(String emailId) {
        employee emp = null;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("select * from employee where emailId=?");
                ps.setString(1, emailId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    //user bean mai set hua
                    emp = new employee();
                    emp.setEmpId(rs.getInt("empId"));
                    emp.setName(rs.getString("name"));
                    emp.setMobile(rs.getString("mobile"));
                    emp.setEmailId(rs.getString("emailId"));
                    emp.setCity_id(rs.getInt("city_id"));
                    emp.setPic(rs.getString("pic"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return emp;

    }

    public boolean updateStatus(int empId, int sreqId, String op) {
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();

        System.out.println("in update status" + con);

        try {
            if (con != null) {
                System.out.println("empId in daoStatus" + empId + "\n sreqId in daoStatus" + sreqId + "op= " + op);
                if (op.equalsIgnoreCase("updateStatus")) {
                    String sql = "update empdetails set empStatus = 'busy' , reqStatus='Processing' , sreqId = ? where empId = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, sreqId);
                    ps.setInt(2, empId);
                    int n = ps.executeUpdate();
                    System.out.println("n from 1st update" + n);
                    sql = "select * from empdetails";
                    ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        System.out.println("ReqStatus from empDetails " + rs.getString("reqStatus"));
                    }

                    if (n > 0) {
                        status = true;
                        System.out.println("status updated sucessfully!!");
                    }
                } else if (op.equalsIgnoreCase("complete")) {
                    String sql = "update empdetails set empStatus = 'Free' , reqStatus='Completed' where empId = ?";
                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setInt(1, empId);
                    int n = ps.executeUpdate();
                    System.out.println("n from 1st update" + n);
                    sql = "select * from empdetails";
                    ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        System.out.println("ReqStatus from empDetails " + rs.getString("reqStatus"));
                    }

                    if (n > 0) {
                        status = true;
                        System.out.println("status updated sucessfully!!");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Exception Occurs" + e.getMessage());
        }

        return status;
    }

    public boolean updateRequestStatus(int empId, int sreqId, String op) {
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println("in update request status" + con);
        try {
            if (con != null) {
                if (op.equalsIgnoreCase("updateStatus")) {
                    System.out.println("empId in daoStatus" + empId + "\n sreqId in daoStatus" + sreqId + "op= " + op);
                    String sql = "update reqdetails set reqStatus=(select reqStatus from empdetails where sreqId=?),assignTo = ? where sreqId = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, sreqId);
                    ps.setInt(2, empId);
                    ps.setInt(3, sreqId);
                    int n = ps.executeUpdate();
                    System.out.println("n from update" + n);
                    if (n > 0) {
                        status = true;
                        System.out.println("status updated sucessfully!!");
                    }
                } else if (op.equalsIgnoreCase("complete")) {
                    LocalDate pDate= java.time.LocalDate.now();
                    String processingDate=pDate.toString();
                    System.out.println("empId in daoStatus" + empId + "\n sreqId in daoStatus" + sreqId + "op= " + op);
                    String sql = "update reqdetails set reqStatus=(select reqStatus from empdetails where sreqId=?),processingDate=? where assignTo = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, sreqId);
                    ps.setString(2, processingDate);
                    ps.setInt(3, empId);
                   
                    int n = ps.executeUpdate();
                    System.out.println("n from update" + n);
                    if (n > 0) {
                        status = true;
                        System.out.println("status updated sucessfully!!");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Exception Occurs" + e.getMessage());
        }

        return status;
    }

    public ArrayList<employee> getAllEmployee() {

        ArrayList<employee> empList = new ArrayList<employee>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        if (con != null) {
            try {
                String sql = "select * from employee";
                PreparedStatement smt = con.prepareStatement(sql);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    employee emp = new employee();
                    emp.setEmpId(rs.getInt("empId"));
                    emp.setName(rs.getString("name"));
                    emp.setEmailId(rs.getString("emailId"));
                    emp.setMobile(rs.getString("mobile"));
                    emp.setCity_id(rs.getInt("city_id"));
                    empList.add(emp);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return empList;
    }

    public ArrayList<employee> getFreeEmployee(int locId) {
        String workStatus = "";
        ArrayList<employee> empList = new ArrayList<employee>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        if (con != null) {
            try {
                String sql = "select * from employee where city_id in (select city_id from location where loc_id = ?) && empId in (select empId from empdetails where empStatus = 'free')";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, locId);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    employee emp = new employee();
                    emp.setEmpId(rs.getInt("empId"));
                    emp.setName(rs.getString("name"));
                    emp.setPic(rs.getString("pic"));
                    workStatus = getWorkStatusById(rs.getInt("empId"));
                    emp.setWorkStatus(workStatus);
                    empList.add(emp);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return empList;
    }

    public String getWorkStatusById(int empId) {
        String workStatus = "";
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from empdetails where empId=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, empId);
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    workStatus = rs.getString("empStatus");
                }
                cp.releaseConnection(con);
                smt.close();
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }
        System.out.println("Request Status in reqStatus function " + workStatus);
        return workStatus;
    }

    public int getTotalEmployee() {
        int total = 0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from employee";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                    System.out.println("total records : " + total);
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return total;
    }

    public employee getById(int empId) {
        employee emp = null;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("select * from employee where empId=?");
                ps.setInt(1, empId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    //emp bean mai set hua
                    emp = new employee();
                    emp.setEmpId(rs.getInt("empId"));
                    emp.setName(rs.getString("name"));
                    emp.setMobile(rs.getString("mobile"));
                    emp.setEmailId(rs.getString("emailId"));
                    emp.setPassword(rs.getString("password"));
                    emp.setPic(rs.getString("pic"));
                    emp.setCity_id(rs.getInt("city_id"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return emp;

    }

    public boolean removeEmployeeById(int empId) {
        boolean status = false;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = "select id from empdetails where empId=?";
                int eId = -1;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, empId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    eId = rs.getInt("id");
                    System.out.println("Emp Details Id Fetched");
                }
                sql = "delete from empdetails where id=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, eId);
                int n = ps.executeUpdate();
                ps = con.prepareStatement("delete from employee where empId=?");
                ps.setInt(1, empId);
                n = ps.executeUpdate();
                con.setAutoCommit(true);

                if (n > 0) {
                    status = true;
                    System.out.println("alert('Record Removed...')");
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return status;
    }

    //Updation of employee's details
    public boolean update(employee emp) {

        boolean status = false;
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        if (con != null) {
            try {
                String sql = "update employee set name=?,mobile=?,emailId=?,pic=? where empId=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, emp.getName());
                smt.setString(2, emp.getMobile());
                smt.setString(3, emp.getEmailId());
                smt.setString(4, emp.getPic());
                smt.setInt(5, emp.getEmpId());
                if (smt.executeUpdate() > 0) {
                    status = true;
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("DBError :" + e.getMessage());
            }
        }

        return status;
    }

    public ArrayList<servicereq> getAllPendingRequestById(int empId) {
        System.out.println("welcome in getAllPendingRequestMEthod");
        System.out.println("Employee id =" + empId);
        ArrayList<servicereq> reqList = new ArrayList<servicereq>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = " select * from servicereq where id=(select sreqId from reqdetails where assignTo =? and reqStatus='Processing')";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, empId);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    servicereq serv = new servicereq();
                    serv.setId(rs.getInt("id"));
                    serv.setUserId(rs.getInt("userId"));
                    serv.setLoc_id(rs.getInt("loc_id"));
                    serv.setLocDetail(rs.getString("locDetail"));
                    serv.setDate(rs.getString("date"));
                    serv.setType(rs.getString("type"));
                    reqList.add(serv);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return reqList;
    }
    public ArrayList<reqDetails> getAllCompletedRequestByEmpId(int empId) {
        System.out.println("welcome in getAllPendingRequestByEmpIdMthod()");
        System.out.println("Employee id =" + empId);
        ArrayList<reqDetails> reqList = new ArrayList<reqDetails>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from reqDetails where assignTo=? and reqstatus='Completed'";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, empId);
                String reqDate="";
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    reqDetails req = new reqDetails();
                    req.setSreqId(rs.getInt("sreqId"));
                    req.setDispatchLocation(rs.getString("dispatchLocation"));
                    req.setProcessingDate(rs.getString("processingDate"));
                    reqDate= getReqDate(rs.getInt("sreqId"));
                    req.setReqDate(reqDate);
                    reqList.add(req);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return reqList;
    }
    public ArrayList<reqDetails> getAllCompletedRequest() {
        System.out.println("welcome in getAllPendingRequestMe thod()");
         ArrayList<reqDetails> reqList = new ArrayList<reqDetails>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from reqDetails where reqstatus='Completed'";
                PreparedStatement smt = con.prepareStatement(sql);
                ResultSet rs = smt.executeQuery();
                String reqDate="";
                while (rs.next()) {
                    reqDetails req = new reqDetails();
                    req.setSreqId(rs.getInt("sreqId"));
                    req.setDispatchLocation(rs.getString("dispatchLocation"));
                    req.setProcessingDate(rs.getString("processingDate"));
                   reqDate= getReqDate(rs.getInt("sreqId"));
                  req.setReqDate(reqDate);
                  
                    reqList.add(req);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }
        

        return reqList;
    }
    public String getReqDate(int sreqId) {
        System.out.println("welcome in getreqdate");
        String reqdate=null;
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select date from servicereq where id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,sreqId);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    reqdate=rs.getString("date");
                }
                ps.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }
        

        return reqdate;
    }
     
    public boolean addDispatchLocation(String loc_id, int empId) {
        int locId = Integer.parseInt(loc_id);
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {
                String sql = "select loc_name from location where loc_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, locId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String loc_name = rs.getString("loc_name");
                    String sql1 = " update reqdetails set dispatchLocation=? where assignTo = ?";
                    ps = con.prepareStatement(sql1);
                    if (loc_name.equalsIgnoreCase("Mata mandir") || loc_name.equalsIgnoreCase("manit") || loc_name.equalsIgnoreCase("new market")) {
                        ps.setString(1, "Kolar");
                    }
                    else if (loc_name.equalsIgnoreCase("mp nagar") || loc_name.equalsIgnoreCase("bharat mata square") || loc_name.equalsIgnoreCase("nehru nagar")) {
                        ps.setString(1, "Chunabhatti");
                    }
                    else if (loc_name.equalsIgnoreCase("AB Road") || loc_name.equalsIgnoreCase("khajrana")) {
                        ps.setString(1, "Adampur");
                    } else if (loc_name.equalsIgnoreCase("kamlanagar")) {
                        ps.setString(1, "Dayalbagh");
                    }
                    ps.setInt(2, empId);
                    int n = ps.executeUpdate();
                    if (n > 0) {
                        System.out.println("added dispatch location");
                        status = true;
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Exception Occurs" + e.getMessage());
        }

        return status;
    }

    public String getDispatchLocation(int empId) {
        System.out.println("welcome in getDispatchLocationMethod");
        System.out.println("Employee id =" + empId);
        String dispatchLocation = "";
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select dispatchLocation from reqDetails where assignTo=? and reqStatus='Processing'";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, empId);
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    dispatchLocation = rs.getString("dispatchLocation");
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return dispatchLocation;
    }

    public String getImages(int empId) {
        //   System.out.println("welcome in getDispatchLocationMethod");
        System.out.println("Employee id =" + empId);
        String images = "";
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = " select garbageImage from servicereq where id =(select sreqId from reqdetails where assignTo=? and reqStatus='Processing')";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, empId);
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    images = rs.getString("garbageImage");
                    System.out.println(images);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return images;
    }
     public ArrayList<reqDetails> getEmpNotification(int empId) {
        System.out.println("welcome in getEmpNotification()");
        System.out.println("Employee id =" + empId);
        ArrayList<reqDetails> reqList = new ArrayList<reqDetails>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from reqDetails where assignTo=? and reqstatus='Processing'";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, empId);
                String reqDate="";
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    reqDetails req = new reqDetails();
                    req.setSreqId(rs.getInt("sreqId"));
                    req.setDispatchLocation(rs.getString("dispatchLocation"));
                    reqDate= getReqDate(rs.getInt("sreqId"));
                    req.setReqDate(reqDate);
                    reqList.add(req);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return reqList;
    }
      public int getCountNotify(int empId) {
        System.out.println("welcome in getCountNotify()");
        System.out.println("Employee id =" + empId);
        int totNotification=0;
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = " select count(*) from reqdetails where assignTo=? and reqStatus='Processing'";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, empId);
                String reqDate="";
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    totNotification = rs.getInt(1);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return totNotification;
    }

}
