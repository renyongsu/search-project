/**
 * 
 */
package com.sohu.wap.search.core.index;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @ClassName: IndexConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author renyongsu renyongsu@sohu-inc.com
 * @date 2011-7-12 上午11:14:28
 *
 */
public class IndexConfig {
	
	private static Logger logger=Logger.getLogger(IndexConfig.class);
	private static Properties p=new Properties();
	static{
		try{
			p.load(IndexConfig.class.getClassLoader().getResourceAsStream("indexconf.properties"));
		}catch(Exception e){
			logger.error("error:",e);
		}
	}
	
	
	public static  final String DIR="indexDir";
	public static  final String NEWDIR="indexNewDir";
	public static  final String DOCUMENT_FACTORY="documentFactory";
	public static  final String SEARCH_USEMEM="search.useMem";
	
	public static String getProperty(String key){
		return p.getProperty(key);
	}

}
