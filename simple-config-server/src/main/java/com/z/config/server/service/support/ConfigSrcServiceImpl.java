/**
 * 
 */
package com.z.config.server.service.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.z.config.client.service.ConfigSrcService;
import com.z.config.client.service.RefreshLocalConfigListener;
import com.z.config.server.service.Config;
import com.z.config.server.service.ConfigSrcBasicService;
import com.z.config.server.service.dao.ConfigDao;

/**
 * @author sunff
 *
 */

public class ConfigSrcServiceImpl implements ConfigSrcService, ConfigSrcBasicService {

	public Map<String, String> getAllConfigs() {
		List<Config> configList = listAllConfigs();
		if (configList == null || configList.size() == 0) {
			return null;
		}

		Map<String, String> m = new HashMap<String, String>(configList.size());

		for (Config f : configList) {
			m.put(f.getConfigKey(), f.getConfigValue());
		}
		return m;
	}

	public Long insertConfig(Config config) {
		Long id = configDao.insertConfig(config);
		refreshLocalConfig(RefreshLocalConfigListener.TYPE_C, config);
		return id;
	}

	public List<Config> listAllConfigs() {

		return configDao.listAllConfigs();
	}

	public int updateConfigById(Config config) {
		int r = configDao.updateConfigById(config);
		refreshLocalConfig(RefreshLocalConfigListener.TYPE_U, config);
		return r;
	}

	// 调用这个远程服务，刷新客户端的本地配置对应的值
	private RefreshLocalConfigListener refreshLocalConfigListener;

	public void setRefreshLocalConfigListener(RefreshLocalConfigListener refreshLocalConfigListener) {
		this.refreshLocalConfigListener = refreshLocalConfigListener;
	}

	private ConfigDao configDao;

	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}

	private void refreshLocalConfig(String type, Config config) {
		StringBuilder sb = new StringBuilder(type);
		sb.append(":");
		sb.append(config.getConfigKey());
		sb.append(":");
		sb.append(config.getConfigValue());
		refreshLocalConfigListener.refreshConfig(sb.toString());
	}

}
