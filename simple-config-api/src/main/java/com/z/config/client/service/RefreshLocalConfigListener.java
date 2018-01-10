/**
 * 
 */
package com.z.config.client.service;

/**
 * @author sunff
 *
 */
public interface RefreshLocalConfigListener {

	
	public static final String TYPE_C="C";

	public static final String TYPE_R="R";

	public static final String TYPE_U="U";

	public static final String TYPE_D="D";
	
	/**
	 * event 格式 type:key:value 
	 * type   对应的配置属性的数据库操作事件类型CRUD
	 * 
	 * @param event
	 * @return
	 */
	boolean refreshConfig(String event);
	
}
