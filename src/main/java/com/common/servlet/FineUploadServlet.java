package com.common.servlet;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class FineUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(FineUploadServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		try {  
	        MultipartUploadParser UploadParser = new MultipartUploadParser(request, new File(System.getProperty("java.io.tmpdir")));  
	        FileItem uploadfile = UploadParser.getFirstFile();  
	        String fileName = uploadfile.getName(); 
	        String filePaht = "D:\\"+ fileName;
	        uploadfile.write(new File(filePaht));  
	        result.put("success", true);
	        LOG.debug("文件上传成功！路径 : " + filePaht);
	    } catch (Exception e) {  
	    	result.put("success", false);
			LOG.error(e.getMessage(), e);
	    }  
		String resJson = JSONObject.toJSONString(result);
		System.out.println(resJson);
		try {
			response.getWriter().print(resJson);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
