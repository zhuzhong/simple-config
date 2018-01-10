/**
 * 
 */
package com.z.config.client.service.support;

import java.util.concurrent.ConcurrentHashMap;

import com.z.config.client.service.LocalConfigService;

/**
 * @author sunff
 *
 */
public class LocalConfigServiceImpl implements LocalConfigService {

	private static ConcurrentHashMap<String, String> configs = new ConcurrentHashMap<String, String>();

	public String getValueByKey(String key) {
		// TODO Auto-generated method stub
		return configs.get(key);
	}

	public void setKV(String key, String value) {
		configs.put(key, value);

	}
	
	
	

}
