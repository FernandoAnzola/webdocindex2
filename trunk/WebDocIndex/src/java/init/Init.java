/*
 * Init.java
 *
 * Created on 20 de junio de 2005, 0:59
 */

package init;


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import lucene.MyDocIndex;
import myclasses.MyDbConnector;
/**
 *
 * @author joliva1983
 * @version
 */


public class Init extends HttpServlet
{

    /**
     * Carga los parametros de la base de datos. Este metodo es invocado cuando se 
     * carga la servlet
     */
    public void init(ServletConfig sc) throws ServletException
    {
        super.init(sc);
        try 
        {
            Class.forName("org.gjt.mm.mysql.Driver");
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            throw new UnavailableException("Unable to load JDBC driver! ",5);
        }
        
        //Cargamos los distintos parametros de configuracion inicial del fichero web.xml
        Config c = new Config(sc.getInitParameter("Server"), 
                sc.getInitParameter("ServerPort"), 
                sc.getInitParameter("Application"),
                sc.getInitParameter("DbKey"),
                sc.getInitParameter("FileServer"),
                sc.getInitParameter("FilePath"));
        
        //Creamos el pool de conexiones que estara presente en toda la aplicacion al iniciarse en init  
        MyDbConnector myDbPool = new MyDbConnector("jdbc/webdocindex");
        
        
    }
    
     public String getServletInfo()
     {
         return "Carga y configuracion de parametros.";
     }
}