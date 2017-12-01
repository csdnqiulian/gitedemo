package com.common.properties;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import com.common.util.DesUtils;

public class DBEncryptFactoryBean implements FactoryBean<Object> {
	private static final Logger LOG = LoggerFactory.getLogger(DBEncryptFactoryBean.class);

	private Properties properties;

	public Object getObject() throws Exception {
		return getProperties();
	}

	public Class<Properties> getObjectType() {
		return java.util.Properties.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties inProperties) {
		try {
			this.properties = inProperties;
			DesUtils des = new DesUtils();
			String originalUsername = properties.getProperty("username");
			String originalPassword = properties.getProperty("password");
			if (originalUsername != null) {
				String newUsername = des.decrypt(originalUsername);
				properties.put("username", newUsername);
			}
			if (originalPassword != null) {
				String newPassword = des.decrypt(originalPassword);
				properties.put("password", newPassword);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
