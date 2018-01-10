/**
 * 
 */
package com.z.config.client.service;

/**
 * @author sunff
 *
 */
public interface LocalConfigService {

	
	
	String getValueByKey(String key);
	
	void setKV(String key,String value);
	
}
