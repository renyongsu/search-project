package com.sohu.wap.search.core.index;

import java.util.List;

import org.apache.lucene.document.Document;

import com.sohu.wap.search.core.xml.DomIndex;
import com.sohu.wap.search.core.xml.DomParser;
import com.sohu.wap.search.core.xml.DomTable;

public class DefaultDocumentFactory{
	
	DomParser domParser=new DomParser();
	DefaultDao dao=new DefaultDao();

	
	public List<Document> getDocuments(DomTable domTable,int start,int size)throws Exception {
		// TODO Auto-generated method stub
		
		return dao.getDocuments(domTable, start, size);
	
	}
	
	public  List<DomTable> getTables(DomIndex domIndex){
		return domParser.getTablesByIndex(domIndex.getName());
	}
	
	
	public List<DomIndex> getDomIndexs(){
		return domParser.getIndexs();
	}

}
