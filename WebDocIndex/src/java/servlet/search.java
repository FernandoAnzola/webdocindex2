/*
 * search.java
 *
 * Created on 26 de febrero de 2007, 16:28
 */

package servlet;

import init.Config;
import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import lucene.MyDocSearch;
import myclasses.MyDocument;
import org.apache.lucene.document.Document;

/**
 *
 * @author Administrador
 * @version
 */
public class search extends BaseServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Vector <MyDocument> documents, boolean busqueda)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(printTop("WebDocIndex"));
        
        out.println("<p>&nbsp;</p>");
        out.println("<h1>Introduce tu busqueda!</h1>");
        out.println("<p>&nbsp;</p>");
        out.println("<form name='formLogin' method='post' action='search.html' >");
        out.println("<input name='type' type='hidden' id='type' value='search' />");
        out.println("<table width='600'   border='0' cellspacing='4' cellpadding='0' align='center'  >");
        
        out.println("<tr valign='top'>");
        out.println("<td width='15%'><strong>Buscar:</strong></td>");
        out.println("<td width='85%'><input name='query' type='text' size='50' maxlength='100'/></td>");
        out.println("</tr>");
        
        
        out.println("<tr valign='top'>");
        out.println("<td width='15%'></td>");
        out.println("<td width='85%'><input name='Submit' id='submit' type='submit' value='Buscar'  /></td>");
        out.println("</tr>");
        out.println("</table>");
        if((documents!=null)&&(busqueda==true))
        {
            out.println("<h1>Resultados</h1>");
            if(documents.size()>0)
                out.println(printDocuments(documents));
            else
                out.println("<p>ERROR! No existe la palabra en nuestros indices!!!</p>");
                
        }
        out.println("</form>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println(printBottom());
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response,null,false);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String query = null;
        if(request.getParameter("query")!=null)
            query=request.getParameter("query");
        MyDocSearch mysearch = new MyDocSearch();
        Vector <MyDocument> documents = mysearch.search(query);
        processRequest(request, response, documents,true);
    }
    
    private String printDocuments(Vector <MyDocument> documents)
    {
        String aux="";
        for(MyDocument doc : documents)
        {
            Document d=doc.getDoc();
            
            aux+="<p><a href=\"file:///"+d.get("path")+" \"><strong class='tam14'>"+d.get("path")+"</strong></a></p>";
            aux+="<p>Acierto: <strong>"+(doc.getScore()*100)+" %</strong></p>";
            Enumeration fields=d.fields();
            if(d.get("path")!=null)
                aux+="<p>Path:"+d.get("path")+"</p>";
            if(d.get("url")!=null)
                aux+="<p>Url:"+d.get("url")+"</p>";
            if(d.get("modified")!=null)
                aux+="<p>Modified:"+d.get("modified")+"</p>";
            if(d.get("Author")!=null)
                aux+="<p>Author:"+d.get("Author")+"</p>";
            if(d.get("CreationDate")!=null)
                aux+="<p>CreationDate:"+d.get("CreationDate")+"</p>";
            if(d.get("Creator")!=null)
                aux+="<p>Creator:"+d.get("Creator")+"</p>";
            if(d.get("Keywords")!=null)
                aux+="<p>Keywords:"+d.get("Keywords")+"</p>";
            if(d.get("ModificationDate")!=null)
                aux+="<p>ModificationDate:"+d.get("ModificationDate")+"</p>";
            if(d.get("GPL Ghostscript 8.54")!=null)
                aux+="<p>GPL Ghostscript 8.54:"+d.get("GPL Ghostscript 8.54")+"</p>";
            if(d.get("Subject")!=null)
                aux+="<p>Subject:"+d.get("Subject")+"</p>";
            if(d.get("Title")!=null)
                aux+="<p>Title:"+d.get("Title")+"</p>";
            if(d.get("summary")!=null)
                aux+="<p>summary:"+d.get("summary")+"</p>";
            aux+="<p>&nbsp;</p>";
        }
        return aux;
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
