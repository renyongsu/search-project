/**
 * 
 */
package com.sohu.wap.search.core.index;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


/**
 * @ClassName: Indexer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author renyongsu renyongsu@sohu-inc.com
 * @date 2011-7-12 上午09:48:52
 *
 */
public class Indexer {
	
	private Logger logger=Logger.getLogger(Indexer.class);
	
	private DocumentFactory docFactory=null;
	
	
	public Indexer(){
		String docFStr=IndexConfig.getProperty("documentFactory");
		try{
			docFactory=(DocumentFactory)Class.forName(docFStr).newInstance();
			docFactory.init();
		}catch(Exception e){
			logger.error("error:",e);
		}
	}
	
	public void index()throws Exception{
		
		Directory dir=FSDirectory.open(new File(IndexConfig.getProperty(IndexConfig.NEWDIR)));
		IndexWriter writer=new IndexWriter(dir,new StandardAnalyzer(Version.LUCENE_30),true,IndexWriter.MaxFieldLength.UNLIMITED);
		List<Document> docs=null;
		while(true){
			docs=docFactory.getDocuments();
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
		docFactory.close();
		
		deployIndex();
		
	}
	
	private boolean deployIndex(){
		long start=System.currentTimeMillis();
		try{
			boolean success=true;
			File currentFile=new File(IndexConfig.getProperty(IndexConfig.DIR));
			if(!currentFile.exists()){
				success=currentFile.mkdirs();
			}
			if(success){
				File bakFile=new File(IndexConfig.getProperty(IndexConfig.DIR)+"-"+getDate());
				success =success && currentFile.renameTo(bakFile);
			}
			if(success){
				File newFile=new File(IndexConfig.getProperty(IndexConfig.NEWDIR));
				newFile.renameTo(currentFile);
			}
		
			return success;
		}catch(Exception e){
			logger.error("error:",e);
		}finally{
			long end=System.currentTimeMillis();
			logger.error("deploy time is  ::  "+(end-start));
		}
		
		logger.error("failed to deploy!!!!!!!!!!!!!!!");
		return false;
		
	}
	
	private String getDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	public static void main(String[] args){
		try{
			Indexer indexer=new Indexer();
			indexer.index();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
