package com.modules.test.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.common.persistence.Page;
import com.common.web.BaseController;
import com.modules.test.bean.Test;
import com.modules.test.service.TestService;

@Controller
@RequestMapping(value = "${adminPath}/test/test")
public class TestController extends BaseController{
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = {"list", ""})
	public String list(Test test, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<Test> page = new Page<Test>(request, response);
		test.setPage(page);
		List<Test> typeList = testService.findList(test);
		page.setList(typeList);
		model.addAttribute("page", page);
		return "modules/test/testList";
	}
	
	@RequestMapping(value = {"save", ""})
	public String save(Test test, HttpServletRequest request,HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		testService.saveTest(test);
		Page<Test> page = new Page<Test>(request, response);
		List<Test> typeList = testService.findList(new Test());
		test.setPage(page);
		page.setCount(typeList.size());
		page.setList(typeList);
		model.addAttribute("page", page);
		return "modules/test/testList";
	}
	@RequestMapping(value = {"add", ""})
	public String add(String id,Model model) {
		Test test = testService.findTestById(id);
		if(test==null){
			test = new Test();
		}
		model.addAttribute("test",  test);
		return "modules/test/testForm";
	}
	
	@RequestMapping(value = {"delete", ""})
	public String delete(String id,Model model) {
		testService.deleteTestById(id);
		return "redirect:" + adminPath + "/test/test/list?repage";
	}
	
	@Override
	protected void initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
		
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String[] fileds = {"endd_time"};  
        for(String filed : fileds){  
        	dataBinder.registerCustomEditor(Date.class, filed, new CustomDateEditor(simpleDateFormat, true));  
        }  
	}
}
