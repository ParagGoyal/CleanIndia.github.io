package daos;

import beans.*;
import java.sql.*;
import java.util.ArrayList;
import jdbc.ConnectionPool;

public class UserDao {

    public boolean addUser(users user) {
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {

                String sql = "insert into users(name,mobile,emailId,password) values(?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, user.getName());
                ps.setString(2, user.getMobile());
                ps.setString(3, user.getEmailId());
                ps.setString(4, user.getPassword());

                int n = ps.executeUpdate();
                System.out.println("n=" + n);
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

    public boolean validate(String emailId, String password) {
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(
                        "select * from users where emailId=? and password=?");
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

    public users getUserById(String emailId) {
        users user = null;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("select * from users where emailId=?");
                ps.setString(1, emailId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    //user bean mai set hua
                    user = new users();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setMobile(rs.getString("mobile"));
                    user.setEmailId(rs.getString("emailId"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;

    }

    public ArrayList<city> getCityName() {
        ArrayList<city> cityName = new ArrayList();

        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from city";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    city city_name = new city();
                    city_name.setCity_id(rs.getInt("city_id"));
                    city_name.setCity_name(rs.getString("city_name"));
                    cityName.add(city_name);
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        return cityName;
    }

    public ArrayList<location> getLocationName(String city_id) {
        ArrayList<location> locationName = new ArrayList();
        System.out.println("city_id in dao" + city_id);
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from location where city_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, city_id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    location loc_name = new location();
                    loc_name.setLoc_id(rs.getInt("loc_id"));
                    loc_name.setLoc_name(rs.getString("loc_name"));
                    loc_name.setCity_id(rs.getInt("city_id"));
                    locationName.add(loc_name);
                }
                for (location loc : locationName) {
                    System.out.println(loc.getLoc_name());
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        return locationName;
    }

    public boolean isEmailExist(String emailId) {
        boolean status = false;

        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from users where emailId=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, emailId);

                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    status = true;
                }
                cp.releaseConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return status;
    }

    public boolean addPickupDetail(servicereq serv) {
        System.out.println("Welcome in serviceReqDao Stage 4");
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println("Connection in request pickup dao" + con);
        try {
            if (con != null) {
                String emailId = serv.getEmailId();
                String mobile = serv.getMobile();
                int location = serv.getLocation();
                System.out.println("Email Id " + emailId + " Mobile " + mobile + " Location " + location);
                int userId = getIdByEmailId(emailId);
                System.out.println("userId " + userId + " Location " + serv.getLocation());
                System.out.println("garbage image " + serv.getGarbageImage());
                String sql = "insert into servicereq(userId,loc_id,date,garbageImage,type,locDetail,mobile) values(?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, location);
                ps.setString(3, serv.getDate());
                ps.setString(4, serv.getGarbageImage());
                ps.setString(5, serv.getType());
                ps.setString(6, serv.getLocDetail());
                ps.setString(7, serv.getMobile());
                int n = ps.executeUpdate();
                System.out.println("n=" + n);
                sql = "select id from servicereq order by id desc limit 1 ";
                ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                int sreqId = -1;
                if (rs.next()) {
                    sreqId = rs.getInt("id");
                    ps = con.prepareStatement("insert into reqdetails(reqStatus,sreqId) values('pending',?)");
                    ps.setInt(1, sreqId);
                    n = ps.executeUpdate();

                }
                if (n > 0) {
                    status = true;
                    System.out.println("request details inserted !");
                }

            }
        } catch (Exception e) {
            System.out.println("Exception Occurs" + e.getMessage());
        }

        return status;
    }

    public int getIdByEmailId(String emailId) {

        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        int userId = 0;
        try {
            if (con != null) {
                System.out.println("Stage 3");
                String sql = "select id from users where emailId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, emailId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    userId = rs.getInt("id");
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Exception Occurs" + e.getMessage());
        }

        return userId;
    }

   

    public int getTotalRequestCountByUser(int id) {
        int total = 0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from servicereq where userId=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,id);
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
    public ArrayList<users> getAllRecords() {

        ArrayList<users> userList = new ArrayList<users>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        if (con != null) {
            try {
                String sql = "select * from users";
                PreparedStatement smt = con.prepareStatement(sql);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    users user = new users();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmailId(rs.getString("emailId"));
                    user.setMobile(rs.getString("mobile"));
                                     userList.add(user);
                }
                smt.close();
                cp.releaseConnection(con);
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }

        return userList;
    }
    public ArrayList<reqDetails> getAllCompletedRequestByUserId(int userId) {
        System.out.println("welcome in getAllCompletedRequestByUserIdMethod()");
        System.out.println("Employee id =" + userId);
        ArrayList<reqDetails> reqList = new ArrayList<reqDetails>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from reqDetails where sreqId in (select id from servicereq where userId = ?) and reqStatus = 'Completed'";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, userId);
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
    public ArrayList<servicereq> getAllRequest() {
        System.out.println("welcome in getAllRequestMEthod");
        ArrayList<servicereq> reqList = new ArrayList<servicereq>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String reqStatus="";
                String sql = "select * from servicereq order by id DESC";
                PreparedStatement smt = con.prepareStatement(sql);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    servicereq serv = new servicereq();
                    serv.setId(rs.getInt("id"));
                    reqStatus=getRequestStatus(rs.getInt("id"));
                    System.out.println("rs.getINT : "+rs.getInt("id"));
                    System.out.println("Request Status : "+reqStatus);
                    serv.setUserId(rs.getInt("userId"));
                    serv.setLoc_id(rs.getInt("loc_id"));
                    serv.setLocDetail(rs.getString("locDetail"));
                    serv.setDate(rs.getString("date"));
                    serv.setType(rs.getString("type"));
                    serv.setReqStatus(reqStatus);
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
     public ArrayList<servicereq> getAllRequestByUserId(int userId) {
        System.out.println("welcome in getAllRequestMEthod");
        ArrayList<servicereq> reqList = new ArrayList<servicereq>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String reqStatus="";
                String sql = "select * from servicereq where userId=? order by id DESC";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, userId);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    servicereq serv = new servicereq();
                    serv.setId(rs.getInt("id"));
                    reqStatus=getRequestStatus(rs.getInt("id"));
                    System.out.println("rs.getINT : "+rs.getInt("id"));
                    System.out.println("Request Status : "+reqStatus);
                    serv.setUserId(rs.getInt("userId"));
                    serv.setLoc_id(rs.getInt("loc_id"));
                    serv.setLocDetail(rs.getString("locDetail"));
                    serv.setDate(rs.getString("date"));
                    serv.setType(rs.getString("type"));
                    serv.setReqStatus(reqStatus);
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
                 String reqDate="";
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
    public String getRequestStatus(int id){
        String reqStatus="";
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from reqdetails where sreqId=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, id);
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    reqStatus = rs.getString("reqStatus");
                }
                cp.releaseConnection(con);
                smt.close();
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }
        System.out.println("Request Status in reqStatus function "+reqStatus);
        return reqStatus;
    }
    public ArrayList<servicereq> getAllPendingRequest() {
        System.out.println("welcome in getAllPendingRequestMEthod");
        ArrayList<servicereq> reqList = new ArrayList<servicereq>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from servicereq where id in (select sreqId from reqdetails where reqStatus='pending') order by id desc";
                PreparedStatement smt = con.prepareStatement(sql);
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
    public ArrayList<servicereq> getAllPendingRequestByUserId(int userId) {
        System.out.println("welcome in getAllPendingRequestMEthodByUserId");
        ArrayList<servicereq> reqList = new ArrayList<servicereq>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from servicereq where id in (select sreqId from reqDetails where reqStatus='Pending') and userId=? order by id desc";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1,userId);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    servicereq serv = new servicereq();
                    serv.setId(rs.getInt("id"));
                    serv.setUserId(rs.getInt("userId"));
                    serv.setLoc_id(rs.getInt("loc_id"));
                    serv.setLocDetail(rs.getString("locDetail"));
                    serv.setDate(rs.getString("date"));
                    serv.setType(rs.getString("type"));
                    serv.setGarbageImage(rs.getString("garbageImage"));
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
    public ArrayList<servicereq> getAllRejectedRequestByUserId(int userId) {
        System.out.println("welcome in getAllRejectedRequestMEthodByUserId");
        ArrayList<servicereq> reqList = new ArrayList<servicereq>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = "select * from servicereq where id in (select sreqId from reqDetails where reqStatus='Rejected') and userId=? order by id desc";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1,userId);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    servicereq serv = new servicereq();
                    serv.setId(rs.getInt("id"));
                    serv.setUserId(rs.getInt("userId"));
                    serv.setLoc_id(rs.getInt("loc_id"));
                    serv.setLocDetail(rs.getString("locDetail"));
                    serv.setDate(rs.getString("date"));
                    serv.setType(rs.getString("type"));
                    serv.setGarbageImage(rs.getString("garbageImage"));
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
    public users getById(int userId) {
        users user = null;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        try {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("select * from users where id=?");
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    //user bean mai set hua
                    user = new users();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setMobile(rs.getString("mobile"));
                    user.setEmailId(rs.getString("emailId"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;

    }
    public boolean removeUserById(int id) {
        boolean status = false;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = "select id from servicereq where userId=?";
                int serv=-1;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    serv=rs.getInt("id");
                    System.out.println("Service Id Fetched");}
                sql="delete from reqdetails where sreqId=?";
                ps=con.prepareStatement(sql);
                ps.setInt(1,serv);
                int n=ps.executeUpdate();
            ps = con.prepareStatement("delete from servicereq where userId=?");
            ps.setInt(1, id);
            n = ps.executeUpdate();
            ps = con.prepareStatement("delete from users where id=?");
            ps.setInt(1, id);
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
}
