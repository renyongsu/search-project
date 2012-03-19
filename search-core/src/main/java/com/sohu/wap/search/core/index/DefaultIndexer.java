package com.sohu.wap.search.core.index;

import java.io.File;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.sohu.wap.search.core.xml.DomIndex;
import com.sohu.wap.search.core.xml.DomTable;

public class DefaultIndexer {
	
	//private DomParser domParser=null;
	
	private DefaultDocumentFactory docFactory=new DefaultDocumentFactory();
	
	public void index()throws Exception{
		
		List<DomIndex> domIndexs=docFactory.getDomIndexs();
		for(DomIndex domIndex:domIndexs){
			indexDoc(domIndex);
		}
		
	}
	
	private void indexDoc(DomIndex domIndex)throws Exception{
		Directory dir=FSDirectory.open(new File(domIndex.getSaveDir()));
		IndexWriter writer=new IndexWriter(dir,new StandardAnalyzer(Version.LUCENE_30),true,IndexWriter.MaxFieldLength.UNLIMITED);
		
		for(DomTable domTable:docFactory.getTables(domIndex)){
			indexTable(writer,domTable);
		}
		
		writer.optimize();
		writer.close();
	}
	
	private void indexTable(IndexWriter writer,DomTable domTable)throws Exception{
		List<Document> docs=null;
		int start=0;
		int size=10;
		while(true){
			docs=docFactory.getDocuments(domTable,start,size);
			if(docs==null || docs.size()==0){
				break;
			}
			for(int i=0;i<docs.size();i++){
				Document doc=docs.get(i);
				writer.addDocument(doc);
			}
			start=start+size;
		}
	}

}
