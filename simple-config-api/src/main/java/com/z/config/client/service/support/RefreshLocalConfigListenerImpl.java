/**
 * 
 */
package com.z.config.client.service.support;

import java.util.Map;

import com.z.config.client.service.ConfigSrcService;
import com.z.config.client.service.LocalConfigService;
import com.z.config.client.service.RefreshLocalConfigListener;

/**
 * @author sunff
 *
 */
public class RefreshLocalConfigListenerImpl implements RefreshLocalConfigListener {

	@Override
	public boolean refreshConfig(String event) {
		/**
		 * event 格式 type:key:value
		 */
		String[] ars = event.split(":");
		String type = ars[0];
		String key = ars[1];
		String value = ars[2];
		localConfigService.setKV(key, value);
		return true;
	}

	public void initConfigs() {
		/**
		 * 用于服务启动时用于初始化config
		 */
		Map<String, String> configSrcs = configSrcService.getAllConfigs();
		for (Map.Entry<String, String> kv : configSrcs.entrySet()) {
			localConfigService.setKV(kv.getKey(), kv.getValue());
		}
	}

	private LocalConfigService localConfigService;

	public void setLocalConfigService(LocalConfigService localConfigService) {
		this.localConfigService = localConfigService;
	}

	private ConfigSrcService configSrcService;

	public void setConfigSrcService(ConfigSrcService configSrcService) {
		this.configSrcService = configSrcService;
	}

}
