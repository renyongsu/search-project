package com.sohu.wap.search.core.index;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.sohu.wap.search.core.xml.DomColumn;
import com.sohu.wap.search.core.xml.DomTable;

public class DefaultDao {
	
	
	public List<Document> getDocuments(DomTable domTable ,int start,int size)throws Exception{
		
		List<Document> docs=new ArrayList<Document>();
		ResultSet rs=execSql(domTable.getSql());
		while(rs.next()){
			Document doc=new Document();
			for(DomColumn column:domTable.getColumns()){
				String value=rs.getString(column.getName());
				Field f=new Field(column.getIndexField(), value, column.getIndexStore(), column.getIndexIndex());
				doc.add(f);
			}
			docs.add(doc);
		}
		return docs;
	}
	
	private ResultSet execSql(String sql){
		return null;
	}
	

}
