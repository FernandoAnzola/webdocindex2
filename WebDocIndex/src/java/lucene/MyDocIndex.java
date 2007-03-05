package lucene;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import org.apache.lucene.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
//import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.IndexWriter;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
//Librerias para indexar pdf
import org.pdfbox.searchengine.lucene.LucenePDFDocument;
import org.pdfbox.searchengine.lucene.IndexFiles;
//librerias para importar ppt

/*
 * MyDocIndex.java
 *
 * Created on 26 de febrero de 2007, 13:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class MyDocIndex {
    
    /** Creates a new instance of MyDocIndex */
    public MyDocIndex() {
        
        INDEX_DIR = new File("index");
        try 
        {
            //true si queremos q se cree de nuevo y false sino
            writer = new IndexWriter(INDEX_DIR, new StandardAnalyzer(), false);
        } //RICHAD ES UN PALOMO COJO
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
    }
    
    public boolean addFile(File f)
    {   
        String path = f.getPath();
        try 
        {    
            Document doc = null;
            if(path.endsWith(".pdf"))
            {
               doc = LucenePDFDocument.getDocument( f );
            }
            
            else if(path.endsWith(".doc"))
            {
                FileInputStream in;
                in = new FileInputStream(f);
                WordExtractor wd = new WordExtractor(in);
                String texto = wd.getText();
                doc = luneneDocument(path,texto,f);
                
            }
            else if (path.endsWith(".ppt"))
            {
                PowerPointExtractor ps = new PowerPointExtractor(f.getPath());
                String texto=ps.getText(true,true);
                doc = luneneDocument(path,texto,f);
            }
            else if(path.endsWith(".txt"))
            {
                doc=FileDocument.Document(f);
            }
            else
            {
                return false;
            }
            if(doc!=null)
            {
                writer.addDocument(doc);
                writer.optimize();
                writer.close();
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    private Document luneneDocument (String path, String texto, File f)
    {
            doc = new Document ();
            doc.add(new Field("path", path, Field.Store.YES, Field.Index.UN_TOKENIZED));
            doc.add(new Field("modified",DateTools.timeToString(f.lastModified(), DateTools.Resolution.MINUTE),Field.Store.YES, Field.Index.UN_TOKENIZED));
            doc.add(new Field("contents", texto, Field.Store.YES,Field.Index.TOKENIZED));
            return doc;
    }
    
   
    
    private IndexWriter writer = null;
    private File INDEX_DIR;
    private Document doc = null;
}

   
