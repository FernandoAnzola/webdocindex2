/*
 * MyDocSearch.java
 *
 * Created on 26 de febrero de 2007, 16:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lucene;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Vector;
import myclasses.MyDocument;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.FilterIndexReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;

/**
 *
 * @author Administrador
 */
public class MyDocSearch {
    
    /** Creates a new instance of MyDocSearch */
    public MyDocSearch() {
        index = "index";
        field = "contents";
        try 
        {
            reader = IndexReader.open(index);
            searcher = new IndexSearcher(reader);
            analyzer = new StandardAnalyzer();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Vector <MyDocument> search(String query)
    {
        Vector <MyDocument> documents = new Vector();
        BufferedReader in = null;
        Hits hits =  null;
          //  in = new BufferedReader(new FileReader(query));
           //in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
           
        QueryParser parser = new QueryParser(field, analyzer);
        Query querys = null;
        try 
        {
            querys = parser.parse(query);
        } 
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        try 
        {
            hits = searcher.search(querys);
            for(int i=0;i<hits.length();i++)
            {
                MyDocument doc= new MyDocument();
                doc.setDoc(hits.doc(i));
                doc.setScore(hits.score(i));
                System.out.println(hits.doc(i).get("path")+"-->"+hits.score(i));
                documents.add(doc);
            }
            return documents;
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private IndexReader reader = null;
    private Searcher searcher = null;
    private Analyzer analyzer = null;
    private String field = null;
    private String index = null;
}
