/**
 * 
 */
package com.sohu.wap.search.core.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.sohu.wap.search.core.index.IndexConfig;

/**
 * @ClassName: Searcher
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author renyongsu renyongsu@sohu-inc.com
 * @date 2011-7-12 下午04:55:54
 *
 */
public class Searcher {
	
	private static Searcher instance=null;
	
	private Directory dir=null;
	
	private IndexSearcher searcher=null;
	
	private Logger logger=Logger.getLogger(Searcher.class);
	
	
	
	private Searcher(){
		
		String fdir=IndexConfig.getProperty(IndexConfig.DIR);
		boolean useMem=Boolean.valueOf(IndexConfig.getProperty(IndexConfig.SEARCH_USEMEM));
		try{
			dir=FSDirectory.open(new File(fdir));
			if(useMem){
				dir=new RAMDirectory(dir);
			}
			searcher=new IndexSearcher(dir);
		}catch(Exception e){
			logger.error("error:",e);
		}
		
	}
	
	public static  Searcher getInstance(){
		if(instance==null){
			synchronized (Searcher.class) {
				if(instance==null){
					instance=new Searcher();
				}	
			}
		}
		return instance;
	}
	
	public List<Document> search(String f,String key,int start,int size)throws Exception{
		
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
	
	public void close(){
		try{
			searcher.close();
		}catch(Exception e){
			logger.error("error:",e);
		}
	}

}
