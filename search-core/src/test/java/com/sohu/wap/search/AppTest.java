package com.sohu.wap.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

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
    	try{
    		//index();
    		
    		List<Document> result=search("id","11111",0,100);
    		System.out.print("size :::: "+result.size());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    
    public List<Document> search(String f,String key,int start,int size)throws Exception{
    	IndexSearcher searcher=new IndexSearcher(FSDirectory.open(new File("e:/test/index")));
		List<Document> result=new ArrayList<Document>();
		QueryParser parser=new QueryParser(Version.LUCENE_30, f, new StandardAnalyzer(Version.LUCENE_30));
		Query query=parser.parse(key);
		TopDocs tds=searcher.search(query, start+size);
		int thits=tds.totalHits;
		if(thits<=start){
			return result;
		}
		
		ScoreDoc[] hits = tds.scoreDocs;
		
		for (int i = start; i < hits.length && i<start+size; i++) {  
			Document hitDoc = searcher.doc(hits[i].doc); //根据命中的文档的内部编号获取该文档   
			result.add(hitDoc);
		}
		return result;
	}
    
    
    public void index()throws Exception{
		
		Directory dir=FSDirectory.open(new File("e:/test/index"));
		IndexWriter writer=new IndexWriter(dir,new StandardAnalyzer(Version.LUCENE_30),true,IndexWriter.MaxFieldLength.UNLIMITED);
		List<Document> docs=null;
		
			docs=getDocuments();
			
			for(int i=0;i<docs.size();i++){
				Document doc=docs.get(i);
				writer.addDocument(doc);
			}
		
		
		
		writer.optimize();
		writer.close();
		System.out.println("yes index is end");
		
		
	}
    
    
    
    private List<Document> getDocuments(){
    	List<Document> list=new ArrayList<Document>();
    	Document doc=new Document();
    	doc.add(new Field("name", "稍等嘎达发生的", Field.Store.YES, Field.Index.ANALYZED));
    	doc.add(new Field("id", "11111", Field.Store.YES, Field.Index.ANALYZED));
    	doc.add(new Field("time","2012", Field.Store.YES, Field.Index.NOT_ANALYZED));
    	list.add(doc);
    	doc=new Document();
    	doc.add(new Field("name", "阿斯顿的", Field.Store.YES, Field.Index.ANALYZED));
    	doc.add(new Field("id", "11111", Field.Store.YES, Field.Index.ANALYZED));
    	doc.add(new Field("time","2010", Field.Store.YES, Field.Index.NOT_ANALYZED));
    	list.add(doc);
    	
    	
    	doc=new Document();
    	doc.add(new Field("name", "龙看咯将空间", Field.Store.YES, Field.Index.ANALYZED));
    	doc.add(new Field("time","2010", Field.Store.YES, Field.Index.NOT_ANALYZED));
    	list.add(doc);
    	return list;
    	
    }
}
