/*
 * login.java
 *
 * Created on 25 de febrero de 2007, 22:30
 */

package servlet;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import myclasses.User;
/**
 *
 * @author javier
 * @version
 */
public class login extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response ,int estado)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet login</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Introduce tus datos de usuario para acceder</h1>");
        out.println("<form name=\"formLogin\" method=\"post\" action=\"login.html\" >");
        out.println("<input name=\"type\" type=\"hidden\" id=\"type\" value=\"login\" />");
        out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"4\" cellpadding=\"0\">");
        
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"><strong>Email:</strong></td>");
        out.println("<td width=\"85%\"><input name=\"email1\" type=\"text\" class=\"formulario\" size=\"50\" maxlength=\"100\"/></td>");
        out.println("</tr>");
        
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"><strong>Password:</strong></td>");
        out.println("<td width=\"85%\"><input name=\"password1\" type=\"password\" class=\"formulario\" size=\"50\" maxlength=\"100\"/></td>");
        out.println("</tr>");
        
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"></td>");
        out.println("<td width=\"85%\"><input name=\"Submit\" id=\"submit\" type=\"submit\" class=\"boton\" value=\"Entrar\"  /></td>");
        out.println("</tr>");
        out.println("</table>");
        if(estado==1)
        {
            out.println("<p>Acceso valido!!!</p>");
        }
        if(estado==2)
        {
            out.println("<p>Acceso NO valido!!!</p>");
        }
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response, 0); //nada
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email1=null;
        String password1=null;
        if(request.getParameter("email1")!=null)
            email1=request.getParameter("email1");
        if(request.getParameter("password1")!=null)
            password1=request.getParameter("password1");
        
        User u = new User(email1,password1);
        if(u.isValid())
        {
            processRequest(request, response,1); //exito
        }
        else
        {
            processRequest(request, response,2); //fallo
        }
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
