package com.common.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *<p>Title:ApplicationListener.java</p>
 *<p>Description:应用程序监听</p>
 *2017年7月1日
 */
public class ApplicationListener implements ServletContextListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LoadSpringContext.init(sce.getServletContext());
		LOG.info("loading spring context successful");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {//直接kill 掉容器将不会触发
		LOG.info("停止应用程序");
	}
}
