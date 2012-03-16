/**
 * 
 */
package com.sohu.wap.search.core.index;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldSelectorResult;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IndexOutput;
import org.apache.lucene.util.Version;

/**
 * @ClassName: IndexMain
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author renyongsu renyongsu@sohu-inc.com
 * @date 2011-7-11 下午03:56:40
 *
 */
public class IndexMain {
	
	public void index()throws Exception{
		
		Directory dir=FSDirectory.open(new File("d:/test"));
		IndexWriter writer=new IndexWriter(dir,new StandardAnalyzer(Version.LUCENE_30),true,IndexWriter.MaxFieldLength.UNLIMITED);
		Document doc=null;
		int i=0;
		while(i<10){
			doc=new Document();
			doc.add(new Field("name", "sdfas", Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
			writer.addDocument(doc);
			i++;
		}
		writer.optimize();
		writer.close();
	}
	
	
	public Document getDocument(){
		
		
		return null;
	}
	
	

}
