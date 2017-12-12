package com.modules.test.entity;

import org.hibernate.validator.constraints.Length;

import com.common.persistence.DataEntity;

/**
 * 测试Entity
 * @author admin
 * @version 2017-12-12
 */
public class Testfreemarker extends DataEntity<Testfreemarker> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名字
	private String remark;		// 备注
	
	public Testfreemarker() {
		super();
	}

	public Testfreemarker(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名字长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}