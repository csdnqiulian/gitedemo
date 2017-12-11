package com.modules.weixin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.web.BaseController;
import com.modules.weixin.util.CheckUtil;
import com.modules.weixin.util.MessageUtil;
@Controller
@RequestMapping(value="/weixin/LoginWechat")
public class LoginWechatController extends BaseController{
	private String token = "csdnqiulian";
	/**
     * 微信平台接入
     */
    @RequestMapping(value="loginByWechat.do",method=RequestMethod.GET)
    public void loginByWechat(HttpServletRequest request,HttpServletResponse 
                                response){
    	try{
	    	// 接收微信服务器以Get请求发送的4个参数
	        String signature = request.getParameter("signature");
	        String timestamp = request.getParameter("timestamp");
	        String nonce = request.getParameter("nonce");
	        String echostr = request.getParameter("echostr");
	         
	        PrintWriter out = response.getWriter();
	        if (CheckUtil.checkSignature(token,signature, timestamp, nonce)) {
	            out.print(echostr);        // 校验通过，原样返回echostr参数内容
	        }
	        out.close();
			out = null;
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value="loginByWechat.do", method = RequestMethod.POST)
	public void wechatPost(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		System.out.println("这是wechatPost的post方法");
		try{
			Map<String, String> map = MessageUtil.parseXml(request);
			System.out.println("=========="+map.get("Content"));
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
	}
}
