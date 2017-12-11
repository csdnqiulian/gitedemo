package com.common.util;

import java.sql.Connection;

public class JDBC_Util {
	private static SimpleConnetionPool simpleConnectionPool = null;
    static {
    	if(null == simpleConnectionPool){
    		simpleConnectionPool = new SimpleConnetionPool();
    		SimpleConnetionPool.setUrl(Dbconfig.getConfig().getProperty("jdbc.url"));
    		SimpleConnetionPool.setUser(Dbconfig.getConfig().getProperty("jdbc.username"));
    		SimpleConnetionPool.setPassword(Dbconfig.getConfig().getProperty("jdbc.password"));
    	}
    }

    //连接数据库的方法  
    public static SimpleConnetionPool  getConnetionPool(){  
        return simpleConnectionPool;
    }  
    
    public static Connection getConnection(){
    	return simpleConnectionPool.getConnection();
    }
     //测试能否与oracle数据库连接成功  
     public static void main(String[] args) {  
    	 JDBC_Util basedao=new JDBC_Util();  
    	 SimpleConnetionPool pool = basedao.getConnetionPool();  
        if(pool.getConnection()==null){  
            System.out.println("与oracle数据库连接失败！");  
        }else{  
            System.out.println("与oracle数据库连接成功！");  
        }  
     }  
}
