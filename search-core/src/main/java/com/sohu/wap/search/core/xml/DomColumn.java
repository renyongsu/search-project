package com.sohu.wap.search.core.xml;

import org.apache.lucene.document.Field;

public class DomColumn {
	
	private String name;
	private String indexField;
	private String index;
	private String store;
	private String value;
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndexField() {
		return indexField;
	}
	public void setIndexField(String indexField) {
		this.indexField = indexField;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	
	public Field.Index getIndexIndex(){
		return null;
	}
	
	public Field.Store getIndexStore(){
		return null;
	}
		

}
