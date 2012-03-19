package com.sohu.wap.search;

import java.io.File;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.sohu.wap.search.core.index.IndexConfig;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	
    }
    
    
    public void index()throws Exception{
		
		Directory dir=FSDirectory.open(new File(IndexConfig.getProperty(IndexConfig.NEWDIR)));
		IndexWriter writer=new IndexWriter(dir,new StandardAnalyzer(Version.LUCENE_30),true,IndexWriter.MaxFieldLength.UNLIMITED);
		List<Document> docs=null;
		while(true){
			docs=getDocuments();
			if(docs==null || docs.size()==0){
				break;
			}
			for(int i=0;i<docs.size();i++){
				Document doc=docs.get(i);
				writer.addDocument(doc);
			}
		}
		
		
		writer.optimize();
		writer.close();
		
		
	}
    
    private List<Document> getDocuments(){
    	return null;
    }
}
