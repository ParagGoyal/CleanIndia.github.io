package jdbc;
import java.sql.*;
import java.util.Vector;
public class ConnectionPool {
    static final int MAX_CONNECTION = 100;
    static Vector connection=null;
    static ConnectionPool instance=null;
    public static synchronized ConnectionPool getInstance(){
        if(instance==null)
            instance=new ConnectionPool();
        
        return instance;
    }
    public synchronized void initialize(){
        if(connection==null){
        try{
            String userName="root";
            String password="parag";
            String url="jdbc:mysql://localhost:3306/clean_india";
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Vector();
            int count=0;
            while(count<MAX_CONNECTION)
            {
                Connection con=DriverManager.getConnection(url,userName,password);
                connection.addElement(con);
                count++;
            }
        }
        catch(Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }
  }
    public synchronized Connection getConnection(){
        Connection c= null;
        if(connection==null)
        {
            return null;
        }
        if(connection.size()>0)
        {
            c=(Connection)connection.elementAt(0);
            connection.removeElementAt(0);
        }
        return c;
    }
    public synchronized void releaseConnection(Connection c){
        connection.addElement(c);
        notifyAll();
    }
}
