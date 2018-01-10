/**
 * 
 */
package com.z.config.server.service.dao;

import java.util.List;

import com.z.config.server.service.Config;

/**
 * @author sunff
 *
 */
public interface ConfigDao {

	
	Long insertConfig(Config config);
	List<Config> listAllConfigs();
	int updateConfigById(Config config);
}
