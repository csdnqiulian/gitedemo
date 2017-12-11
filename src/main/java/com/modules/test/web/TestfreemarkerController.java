package com.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.common.config.Global;
import com.common.persistence.Page;
import com.common.web.BaseController;
import com.common.util.StringUtils;
import com.modules.test.entity.Testfreemarker;
import com.modules.test.service.TestfreemarkerService;

/**
 * 测试Controller
 * @author admin
 * @version 2017-12-10
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testfreemarker")
public class TestfreemarkerController extends BaseController {

	@Autowired
	private TestfreemarkerService testfreemarkerService;
	
	@ModelAttribute
	public Testfreemarker get(@RequestParam(required=false) String id) {
		Testfreemarker entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testfreemarkerService.get(id);
		}
		if (entity == null){
			entity = new Testfreemarker();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testfreemarker:view")
	@RequestMapping(value = {"list", ""})
	public String list(Testfreemarker testfreemarker, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Testfreemarker> page = testfreemarkerService.findPage(new Page<Testfreemarker>(request, response), testfreemarker); 
		model.addAttribute("page", page);
		return "modules/test/testfreemarkerList";
	}

	@RequiresPermissions("test:testfreemarker:view")
	@RequestMapping(value = "form")
	public String form(Testfreemarker testfreemarker, Model model) {
		model.addAttribute("testfreemarker", testfreemarker);
		return "modules/test/testfreemarkerForm";
	}

	@RequiresPermissions("test:testfreemarker:edit")
	@RequestMapping(value = "save")
	public String save(Testfreemarker testfreemarker, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testfreemarker)){
			return form(testfreemarker, model);
		}
		testfreemarkerService.save(testfreemarker);
		addMessage(redirectAttributes, "保存测试成才成功");
		return "redirect:"+Global.getAdminPath()+"/test/testfreemarker/?repage";
	}
	
	@RequiresPermissions("test:testfreemarker:edit")
	@RequestMapping(value = "delete")
	public String delete(Testfreemarker testfreemarker, RedirectAttributes redirectAttributes) {
		testfreemarkerService.delete(testfreemarker);
		addMessage(redirectAttributes, "删除测试成才成功");
		return "redirect:"+Global.getAdminPath()+"/test/testfreemarker/?repage";
	}

}