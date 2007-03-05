/*
 * MyDbConnector.java
 *
 * Created on 14 de octubre de 2006, 14:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myclasses;
import java.sql.*;
import javax.naming.*;
import javax.naming.*;
import javax.sql.DataSource;
/**
 *
 * @author universidad
 */
public class MyDbConnector {
    
    /** Creates a new instance of MyDbConnector */
    public MyDbConnector(String url)
    {
        try
        {
            ctx = new InitialContext();
            myDbPool = (DataSource) ctx.lookup(url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ctx=null;
            myDbPool=null;
        }
    }
    
    public static boolean executeQuery(String query)
    {
        Connection conn=null;
        Statement stmt=null;
        try 
        {
            conn = MyDbConnector.getConn();
            stmt = conn.createStatement( );
            stmt.execute(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally 
        {
            if (stmt != null) {try {stmt.close();} catch (SQLException e) {}stmt=null;}
            if (conn != null) {try {conn.close();} catch (SQLException e) {}conn=null;}
        }
        return true;
    }
    
    public static boolean hasNextQuery(String query)
    {
        boolean val=false;
        Connection conn=null;
        Statement stmt=null;
        ResultSet rset=null;
        try 
        {
            conn = MyDbConnector.getConn();
            stmt = conn.createStatement( );
            rset = stmt.executeQuery(query);
            if(rset.next())
                val=true;
            else
                val=false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            val=false;
        }
        finally 
        {
            if (rset != null) {try {rset.close();} catch (SQLException e) {}rset=null;}
            if (stmt != null) {try {stmt.close();} catch (SQLException e) {}stmt=null;}
            if (conn != null) {try {conn.close();} catch (SQLException e) {}conn=null;}
        }
        return val;
    }
    
    public static int getSizeQuery(String query)
    {
        int val=0;
        Connection conn=null;
        Statement stmt=null;
        ResultSet rset=null;
        try 
        {
            conn = MyDbConnector.getConn();
            stmt = conn.createStatement( );
            rset = stmt.executeQuery(query);
            while(rset.next())
                val++;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            val=0;
        }
        finally 
        {
            if (rset != null) {try {rset.close();} catch (SQLException e) {}rset=null;}
            if (stmt != null) {try {stmt.close();} catch (SQLException e) {}stmt=null;}
            if (conn != null) {try {conn.close();} catch (SQLException e) {}conn=null;}
        }
        return val;
    }
    
    private static Context ctx = null;
    private static DataSource myDbPool = null;
        
    public static Connection getConn()
    {
        try 
        {
            return myDbPool.getConnection();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            return null;
        }
    }
    
}
