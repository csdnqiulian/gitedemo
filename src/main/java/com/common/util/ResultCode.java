package com.common.util;

/**
 *<p>Title:ResultCode.java</p>
 *<p>Description:错误代码结果集</p>
 *@version 1.0
 */
public class ResultCode {
	private int index;
	public ResultCode (int code){
		this.index = code;
	}
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ResultCode other = (ResultCode) obj;
		if (index != other.index)
			return false;
		return true;
	}

	/**
	 * 成功
	 */
	public static final ResultCode OK = new ResultCode(0);
	/**
	 * 失败
	 */
	public static final ResultCode FAIL = new ResultCode(-1);
	
	/**
	 * 数据已存在
	 */
	public static final ResultCode EXIST = new ResultCode(-2); 
	/**
	 * 数据不存在
	 */
	public static final ResultCode UNEXIST = new ResultCode(-3);
}
