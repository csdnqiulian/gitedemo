package com.common.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.common.persistence.paging.DataListIfc;
import com.common.persistence.paging.Page;
import com.common.persistence.paging.PageRequest;
import com.common.persistence.paging.Pagination;
import com.common.startup.LoadSpringContext;
import com.common.util.StringUtil;
/**
 *<p>Title:JqgridController.java</p>
 *<p>Description:</p>
 *2013年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/jqgrid")
public class JqgridController {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("list")
	public @ResponseBody Page<?> pageList(@RequestParam("length") int length,// 每页显示记录条数
			@RequestParam(defaultValue = "1", value = "page") int page, // 页码 显示第几页
			@RequestParam("ispaging") String ispaging, // 是否分页
			@RequestParam("orderName") String orderName,// 排序字段
			@RequestParam("orderType") String orderType,// 排序 desc asc
			@RequestParam("search") String search, // 是否查询
			@RequestParam("classNameId") String classNameId, //分页list实现类
			HttpServletRequest request
			) {
		Page pageVO = new Pagination();
		pageVO.setPage(page);
		pageVO.setLength(length);
		pageVO.setOrderName(orderName);
		pageVO.setOrderType(orderType);
		if("true".equals(search)){//设置查询参数
			String jsonParam = StringUtil.deNull(request.getParameter("jsonParam"));
			pageVO.setJsonParam(jsonParam);
		}
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageVO(pageVO);
		//从spring 容器中获取列表查询对象
		DataListIfc<?> dataListImpl = (DataListIfc<?>) LoadSpringContext.getApplicationContext().getBean(classNameId);
		if("true".equals(ispaging)){//分页查询
			pageVO = dataListImpl.getPagingDataList(pageRequest);
		}else{//不分页查询
			pageVO = dataListImpl.getAllDataList(pageRequest);
		}
		return pageVO;
	}

}
