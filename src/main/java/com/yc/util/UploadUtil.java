package com.yc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

public class UploadUtil {
	//首先，定义一些属性
	private String path = "upload";
	//定义允许上传的格式
	private String allowed = "jpg,png,gif,bmp";
	//设置一下上传文件的总大小
	private int totalmax = 10*1024*1024;
	//设置单个文件大小
	private int singlesize = 2*1024*1024;
	
	public UploadUtil(String path, String allowed, int totalmax, int singlesize) {
		super();
		this.path = path;
		this.allowed = allowed;
		this.totalmax = totalmax;
		this.singlesize = singlesize;
	}
	
	public UploadUtil() {
		super();
	}
	
	//上传文件
	public List<String> upLoadFiles(PageContext pageContext){
		List<String> list = new ArrayList<String>();
		
		try {
			SmartUpload su = new SmartUpload();
			//初始化
			su.initialize(pageContext);
			//允许上传的文件格式
			su.setAllowedFilesList(allowed);
			su.setCharset("utf-8");
			su.setTotalMaxFileSize(totalmax);
			su.setMaxFileSize(singlesize);
			
			//开始上传
			su.upload();
			
			
			//得到上传的文件
			if ( su.getFiles() != null && su.getFiles().getCount() > 0 ) {
				//有文件，我们才做处理
				Files fs = su.getFiles();
				//循环
				for(int i = 0;i < fs.getCount();i ++) {
					String fname = "";
					File f = fs.getFile(i);
					
					fname = path + "/" + new Date().getTime() + new Random().nextInt() + "." + f.getFileExt();
					//上传
					f.saveAs(fname, SmartUpload.SAVE_VIRTUAL);
					
					list.add(fname);
				}
			}
		} catch (Exception e) {
			return null;
		}
		return list;
	}
}
