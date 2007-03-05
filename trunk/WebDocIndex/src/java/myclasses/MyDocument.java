/*
 * MyDocument.java
 *
 * Created on 26 de febrero de 2007, 12:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myclasses;
import org.apache.lucene.document.Document;

/**
 *
 * @author Administrador
 */
public class MyDocument {
    
    /**
     * Creates a new instance of MyDocument
     */
    public MyDocument() {
    }
    
    private int id_documento = -1;
    private String descripcion = null;
    private int id_usuario = -1;
    private String url = null;
    private float score = 0;
    private Document lucene_doc = null;

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
   

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Document getDoc() {
        return lucene_doc;
    }

    public void setDoc(Document doc) {
        this.lucene_doc = doc;
    }
    
    public boolean saveToDb()
    {
         return MyDbConnector.executeQuery("insert into documentos (id_usuario,url,descripcion)values("+this.getId_usuario()+","+this.getUrl()+","+this.getDescripcion()+"))");
    }
    
}
