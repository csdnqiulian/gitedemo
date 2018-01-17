package com.common.persistence.paging;
import com.common.persistence.annotation.Paging;

/**
 *<p>Title:PageRequest.java</p>
 *<p>Description:分页查询参数对象</p>
 */
@Paging(field = "pageVO")
public class PageRequest<T> {
	
	private Page<T> pageVO;

	public Page<T> getPageVO() {
		return pageVO;
	}

	public void setPageVO(Page<T> pageVO) {
		this.pageVO = pageVO;
	}
}
