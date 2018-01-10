/**
 * 
 */
package com.z.config.server.service;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunff
 *
 */
public class Config  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4655086772629865466L;
	private Long id;
	private String configKey;
	private String configValue;
	
	private Date createdTime;
	private Date updatedTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	
}
