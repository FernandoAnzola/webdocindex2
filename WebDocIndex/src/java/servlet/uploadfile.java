/*
 * uploadfile.java
 *
 * Created on 25 de febrero de 2007, 22:47
 */

package servlet;

import init.Config;
import java.io.*;
import java.net.*;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;
import com.oreilly.servlet.MultipartRequest;
import lucene.MyDocIndex;
import lucene.MyDocSearch;

/**
 *
 * @author javier
 * @version
 */
public class uploadfile extends BaseServlet {
    
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
        out.println("<title>Servlet login</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Subir un fichero</h1>");
        
        out.println("<form action=\"uploadfile.html\" method=\"post\" enctype=\"multipart/form-data\" name=\"form2\" id=\"form2\">");
        out.println("<input name=\"type\" type=\"hidden\" id=\"type\" value=\"login\" />");
        out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"4\" cellpadding=\"0\">");
        
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"><strong>Fichero:</strong></td>");
        out.println("<td width=\"85%\"><input name=\"file\" type=\"file\" size=\"70\"/></td>");
        out.println("</tr>");
        
        out.println("<tr valign=\"top\">");
        out.println("<td width=\"15%\"></td>");
        out.println("<td width=\"85%\"><input name=\"Submit\" id=\"submit\" type=\"submit\" class=\"boton\" value=\"Subir fichero\"  /></td>");
        out.println("</tr>");
        out.println("</table>");
        if(estado==1)
        {
            out.println("<p>Su archivo se ha guardado con exito!</p>");
        }
        if(estado==2)
        {
            out.println("<p>No se ha podido guardar el archivo!!.No es un fichero valido para indexar!!</p>");
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
        if(saveFile(request))
            processRequest(request, response,1); //exito
        else
            processRequest(request, response,2); //fallo
    }
    
    private boolean saveFile(HttpServletRequest request)
    {
        HttpSession sesion = request.getSession();
        try 
        {
            String name="",filename="",original="",type="";
            int tam = 5*1024*1024; //definimos el tamaño que podran tener el total de los archivos
            //para windows el directorio asi c:/
            //para linux /temp..
            MultipartRequest multi =  new MultipartRequest(request, Config.getFilePath(), tam,new com.oreilly.servlet.multipart.DefaultFileRenamePolicy());
            Enumeration params = multi.getParameterNames();
            while (params.hasMoreElements())  //busca los elementos del formulario todos los que no son arhcivos
            {
                String name1 = (String)params.nextElement();
                String value1 = multi.getParameter(name);
            }
            Enumeration files = multi.getFileNames();
            File f = null;
            if (files.hasMoreElements()) 
            {
                name = (String)files.nextElement();
                type = multi.getContentType(name);
                f = multi.getFile(name);
                java.util.Random rd = new java.util.Random();
                filename=rd.nextInt(9999999)+"."+type;
                
                //PRUEBA PARA SUBIR EL FICHERO AL INDICE
                //Inicializamos el indice        
                MyDocIndex myindex = new MyDocIndex();     
                return myindex.addFile(f);
            }
        }
        catch (Exception e) 
        {
          e.printStackTrace();
        }
        return false;
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short descrisssption";
    }
    // </editor-fold>
}
