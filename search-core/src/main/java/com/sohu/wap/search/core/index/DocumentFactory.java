/**
 * 
 */
package com.sohu.wap.search.core.index;

import java.util.List;

import org.apache.lucene.document.Document;

/**
 * @ClassName: DocumentFactory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author renyongsu renyongsu@sohu-inc.com
 * @date 2011-7-12 上午09:47:27
 *
 */
public interface DocumentFactory {
	
	public void init();
	
	public List<Document> getDocuments();
	
	public void close();
}
