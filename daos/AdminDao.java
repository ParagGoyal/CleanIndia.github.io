package daos;

import beans.*;
import java.sql.*;
import java.util.ArrayList;
import jdbc.ConnectionPool;

public class AdminDao {

    public String getImagesById(int sreqId) {
        System.out.println("welcome in getImagesById");
        System.out.println("SreqId = " + sreqId);
        String images = "";
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println("AdminDaoCon " + con);
        if (con != null) {
            try {
                String sql = " select garbageImage from servicereq where id =?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, sreqId);
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

    public int getTotalRequestCount() {
        int total = 0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from servicereq";
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
//reqdetails bean bnadi th maine 

    public ArrayList<reqDetails> getTotalCompleteRequest() {
        ArrayList<reqDetails> completeRequest = new ArrayList();
        //int total=0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from reqdetails where reqStatus='Completed'";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    reqDetails req = new reqDetails();
                    req.setSreqId(rs.getInt("sreqId"));
                    req.setDispatchLocation(rs.getString("dispatchLocation"));
                    req.setProcessingDate(rs.getString("processingDate"));
                    req.setReqStatus(rs.getString("reqStatus"));
                    completeRequest.add(req);
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return completeRequest;
    }

    public int getTotalUsers() {
        int total = 0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from users";
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

    public boolean updateRequestStatus(int sreqId) {
        boolean status = false;
        ConnectionPool cp = new ConnectionPool().getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println("in update request status" + con);
        try {
            if (con != null) {

                String sql = "update reqdetails set reqStatus= 'Rejected' where sreqId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, sreqId);
                int n = ps.executeUpdate();
                System.out.println("n from update" + n);
                if (n > 0) {
                    status = true;
                    System.out.println("status updated sucessfully!!");
                }

            }

        } catch (Exception e) {
            System.out.println("Exception Occurs" + e.getMessage());
        }

        return status;
    }
    public ArrayList<servicereq> getAllRejectedRequest() {
        System.out.println("welcome in getAllRejctedRequestMEthod");
       
        ArrayList<servicereq> reqList = new ArrayList<servicereq>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = " select * from servicereq where id in (select sreqId from reqdetails where reqStatus='Rejected')";
                PreparedStatement smt = con.prepareStatement(sql);
         
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    servicereq serv = new servicereq();
                    serv.setId(rs.getInt("id"));
                    serv.setUserId(rs.getInt("userId"));
               
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
     public ArrayList<reqDetails> getAllProcessingRequest() {
        System.out.println("welcome in getAllProcessingRequestMEthod");
       
        ArrayList<reqDetails> reqList = new ArrayList<reqDetails>();
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println(con);
        if (con != null) {
            try {
                String sql = " select * from reqdetails where reqStatus='Processing'";
                PreparedStatement smt = con.prepareStatement(sql);
         
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    reqDetails req = new reqDetails();
                    req.setSreqId(rs.getInt("sreqId"));
                    req.setAssignTo(rs.getInt("assignTo"));
                    req.setDispatchLocation(rs.getString("dispatchLocation"));
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
}
