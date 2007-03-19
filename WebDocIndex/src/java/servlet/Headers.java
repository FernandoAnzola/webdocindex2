/*
 * headers.java
 *
 * Created on 18 de marzo de 2007, 22:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package servlet;

import javax.servlet.http.HttpServlet;

/**
 *
 * @author javier
 */
public class Headers extends HttpServlet {
    
    /**
     *Funcion que devuelve el encabezado para todas las paginas de la web.
     */
    public String printTop(String title)
    {
        String aux="";
        aux+="<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'";
        aux+="'http://www.w3.org/TR/html4/loose.dtd'>";
        aux+="<html>";
        aux+="<head>";
        aux+="<title>"+title+"</title>";
        aux+="<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' />";
        aux+="<link rel='stylesheet' href='css/comun.css' type='text/css' />";
        aux+="</head>";
        aux+="<body>";
        aux+="<p>&nbsp;</p>";
        aux+="<div class='principal'>";
        aux+="<p align='center' class='tam72 Estilo1'>WebDocIndex</p>";
        aux+="<hr>";
        aux+="<p align='right'><a href='login.html'><strong>Acceder</strong></a> | <a href='signup.html'><strong>Registrate</strong></a></p>";
        aux+="<p>&nbsp;</p>";
        aux+="<p>&nbsp;</p>";
        
        return aux;
    }
    
    /**
     *Funcion que devuelve el pie de pagina para todas las paginas de la web
     */
    public String printBottom()
    {
        String aux="";
        aux+="<p>&nbsp;</p>";
        aux+="</div>";
        aux+="</body>";
        aux+="</html>";
        
        return aux;
    }
    
    public String printError(String texto, int width)
    {
        String aux="";
        aux="<p><table width='"+width+"' class='error'>"+
            "<tr><td><p align='left'><strong>ERROR: "+texto+"</strong></p></td></tr>"+
            "</table></p>";
        return aux;
    }
    public String printOk(String texto, int width)
    {
        String aux="";
        aux="<p><table width='"+width+"' class='ok'>"+
            "<tr><td><p align='left' class='blanco'><strong>OK! "+texto+"</strong></p></td></tr>"+
            "</table></p>";
        return aux;
    }
    
}
