package com.z.config.server.service;

import java.util.List;

public interface ConfigSrcBasicService {

	
	
	Long insertConfig(Config config);
	List<Config> listAllConfigs();
	int updateConfigById(Config config);
	
}
