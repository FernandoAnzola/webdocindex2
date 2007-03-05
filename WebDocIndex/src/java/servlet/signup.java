/*
 * signup.java
 *
 * Created on 25 de febrero de 2007, 21:43
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
public class signup extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, int estado)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet signup</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Introduce tus datos de usuario</h1>");
        out.println("<form name=\"formLogin\" method=\"post\" action=\"signup.html\" >");
        out.println("<input name=\"type\" type=\"hidden\" id=\"type\" value=\"login\" />");
        out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"4\" cellpadding=\"0\">");
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"><strong>Email:</strong></td>");
        out.println("<td width=\"85%\"><input name=\"email1\" type=\"text\" class=\"formulario\" size=\"50\" maxlength=\"100\"/></td>");
        out.println("</tr>");
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"><strong>Email (Repetir):</strong></td>");
        out.println("<td width=\"85%\"><input name=\"email2\" type=\"text\" class=\"formulario\" size=\"50\" maxlength=\"100\"/></td>");
        out.println("</tr>");
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"><strong>Password:</strong></td>");
        out.println("<td width=\"85%\"><input name=\"password1\" type=\"password\" class=\"formulario\" size=\"50\" maxlength=\"100\"/></td>");
        out.println("</tr>");
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"><strong>Password (Repetir):</strong></td>");
        out.println("<td width=\"85%\"><input name=\"password2\" type=\"password\" class=\"formulario\" size=\"50\" maxlength=\"100\"/></td>");
        out.println("</tr>");
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"></td>");
        out.println("<td width=\"85%\"><input name=\"Submit\" id=\"submit\" type=\"submit\" class=\"boton\" value=\"Guardar\"  /></td>");
        out.println("</tr>");
        out.println("</table>");
        if(estado==1)
        {
            out.println("<p>Usuario Registrado con Exito!!</p>");
        }
        if(estado==2)
        {
            out.println("<p>No se ha podido guardar el Usuario!!</p>");
        }
        if(estado==3)
        {
            out.println("<p>Los datos introducidos no son validos. Compruebe que coinciden!!</p>");
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
        processRequest(request, response,0);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String email1=null;
        String email2=null;
        String password1=null;
        String password2=null;
        
        if(request.getParameter("email1")!=null)
            email1=request.getParameter("email1");
        if(request.getParameter("email2")!=null)
            email2=request.getParameter("email2");
        if(request.getParameter("password1")!=null)
            password1=request.getParameter("password1");
        if(request.getParameter("password2")!=null)
            password2=request.getParameter("password2");
        if((email1.length()>5)&&(password1.length()>=5)&&(password1.length()<=12))
        {
            if((email1.compareTo(email2)==0)&&(password1.compareTo(password2)==0))
            {
                User u = new User(email1,password1);
                if(u.saveToDb())
                {
                    processRequest(request, response,1); //exito
                }
                else
                {
                    processRequest(request, response,2); //fallo al salvar el usuario
                }
            }
            else
            {
                processRequest(request, response,3); //el usuario y el password no coinciden
            }
        }
        else
        {
            processRequest(request, response,3);
        }
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
