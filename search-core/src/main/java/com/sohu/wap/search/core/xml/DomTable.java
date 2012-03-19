package com.sohu.wap.search.core.xml;

import java.util.List;


public class DomTable {
	
	private String name;
	private String ref_index;
	private List<DomColumn> columns;
	
	private String sql;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRef_index() {
		return ref_index;
	}
	public void setRef_index(String ref_index) {
		this.ref_index = ref_index;
	}
	public List<DomColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<DomColumn> columns) {
		this.columns = columns;
	}
	
	public DomColumn getColumn(String name){
		return null;
		
	}
	public String getSql(){
		if(sql==null){
			sql=generateSql();
		}
		return sql;
	}
	
	private  String generateSql(){
		StringBuilder sql=new StringBuilder();
		sql.append("select ");
		for (DomColumn column:columns){
			sql.append(column.getName()+",");
		}
		sql.append(" from "+name+" limit ?,?");
		return sql.toString();
		
	}
	

}
