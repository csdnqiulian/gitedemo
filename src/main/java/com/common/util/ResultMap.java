package com.common.util;

/**
 *<p>Title:ResultMap.java</p>
 *<p>Description:返回数据结果集</p>
 *<p>Copyright:Copyright (c) 2013</p>
 *<p>Company:湖南科创</p>
 *@author 陈建华
 *@version 1.0
 *Apr 28, 2013
 * @param <C> 错误代码集
 * @param <R> 数据集
 */
public class ResultMap<C,R> {
	
	private C code;
	private R result;	 
	/**
	 * @return the code
	 */
	public C getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(C code) {
		this.code = code;
	}
	/**
	 * @return the result
	 */
	public R getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(R result) {
		this.result = result;
	}
	/**
	 * 相等检测
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj){
		if(obj!=null && getClass().isInstance(obj)){
			ResultMap<C,R> t=(ResultMap<C,R>)obj;
			return t.code==code && t.result==result;
		}
		return false;
	}
	@Override
	public String toString() {
		return "ResultMap [code=" + code + ", result=" + result + "]";
	}
}
