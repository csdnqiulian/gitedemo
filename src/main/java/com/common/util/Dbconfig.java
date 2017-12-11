package com.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * Dbconfig 读取配置文件Dbconfig.properties
 * @author a
 *
 */
public class Dbconfig {
	private final static Logger LOG = Logger.getLogger(Dbconfig.class);
	private static Properties p;
	
	public static Properties getConfig(){
		if(null == p){
			p = new Properties();
	    	try {
	    		File pFile = new File(Dbconfig.class.getResource("/").getPath()+"pconfig.properties");
	    		FileInputStream pInStream=new FileInputStream(pFile );
				p.load(pInStream);
			} catch (IOException e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return p;
			
	}
}
