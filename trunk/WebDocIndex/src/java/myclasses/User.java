/*
 * User.java
 *
 * Created on 25 de febrero de 2007, 21:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myclasses;

import init.Config;
import java.sql.*;

/**
 *
 * @author javier
 */
public class User {
    
    /** Creates a new instance of User */
    public User() {
    }
    
    public User(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }
    
    private String email=null;
    private String password=null;
    private int id_usuario=-1;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean saveToDb()
    {
        return MyDbConnector.executeQuery("insert into usuarios (email,password)values('"+this.getEmail()+"',AES_ENCRYPT('"+this.getPassword()+"','"+Config.getDbKey()+"'))");
    }
    
    public void readFromDb(int id_usuario)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rset=null;
        try 
        {
            conn = MyDbConnector.getConn();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("select * from usuarios where id_usuario="+this.getId_usuario()+" " );
            if (rset.next())//hay q crear un tabla con el estado //1 valido 2 signup2 3 signup3 ...
            {
                this.setId_usuario(rset.getInt("id_usuario"));
                this.setEmail(rset.getString("email"));
                this.setPassword(null);
            }
        }
        catch (SQLException ex) //sino puede conectar muestra una pagina donde dice que no puede conectar
        {
            ex.printStackTrace();
        }
        finally 
        {
            if (rset != null){try {rset.close();} catch (SQLException e) {}rset=null;}
            if (stmt != null) {try {stmt.close();} catch (SQLException e) {}stmt=null;}
            if (conn != null) {try {conn.close();} catch (SQLException e) {}conn=null;}
        }
    }
    
    public boolean isValid()
    {
        return MyDbConnector.hasNextQuery("select * from usuarios where email='"+this.getEmail()+"' and password=AES_ENCRYPT('"+this.getPassword()+"','"+Config.getDbKey()+"') ");
    }
    
    public boolean addDocument(MyDocument doc)
    {
        doc.setId_usuario(this.getId_usuario());
        return doc.saveToDb(); 
    }


}
