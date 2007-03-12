/*
 * BaseServlet.java
 *
 * Created on 12 de marzo de 2007, 0:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package servlet;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import myclasses.MyDbConnector;

/**
 *
 * @author javier
 */
public class BaseServlet extends HttpServlet  {
    
    public void destroy() 
    {
        super.destroy();
    }
    
    /** Creates a new instance of BaseServlet */
    public boolean checkEmail(String email)
    {
        Pattern p = Pattern.compile("^([0-9a-zA-Z]([-.a-zA-Z_0-9])*[0-9a-zA-Z])*@([0-9a-zA-Z][-a-zA-Z_0-9]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9}$");
        Matcher m = p.matcher(email);
	if (m.find()) 
        {
            return true;
	} 
        else 
        {
            return false;
	}
    }
    
    public boolean checkLetraNumeroGuionPunto(String cadena)
    {
        Pattern p = Pattern.compile("^([0-9a-z._])+$");
        Matcher m = p.matcher(cadena);
	if (m.find()) 
        {
            return true;
	} 
        else 
        {
            return false;
	}
    }
    
    public boolean checkLenghtString(String cadena, int min, int max)
    {
        if((cadena.length()>=min)&&(cadena.length()<max))
            return true;
        else
            return false;
    }
    
    public boolean checkSession(HttpSession session, String sessionName)
    {
        if (session.isNew()||session.getAttribute(sessionName)==null||estaSessionBD(session.getId())==false)
        {
            session.invalidate();
            return true;
        }
        return false;
    }
    
    public boolean estaSessionBD(String session)
    {
        return (MyDbConnector.hasNextQuery("select * from sesiones where codigo='"+session+"' "));
    }
    
    public void BorrarSessionInactivas()
    {
        java.util.Date actual = new java.util.Date();
        long tiempo_resta = (15*60*1000);
        long tiempo_total;
        tiempo_total = (actual.getTime() - tiempo_resta);
        MyDbConnector.executeQuery("delete from sesiones where tiempo < "+tiempo_total+"");
    }
    
    public void ActualizarSession(String session)
    {
        java.util.Date actual = new java.util.Date();
        MyDbConnector.executeQuery("update sesiones set tiempo = "+actual.getTime()+" where codigo = '"+session+"' ");
    }
    
}
