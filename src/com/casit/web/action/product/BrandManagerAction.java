package com.casit.web.action.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.casit.bean.product.Brand;
import com.casit.bean.product.UserInfo;
import com.casit.service.product.BrandService;
import com.casit.service.product.UserService;
import com.casit.util.UrlUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class BrandManagerAction extends ActionSupport {

	private static final long serialVersionUID = 9222222194686878662L;
	@Resource
	private UserService us;
	private String name;
	private Integer userid;
	private String logopath;
	private Integer tm;
	private Integer tp;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
		
	public Integer getTm() {
		return tm;
	}

	public void setTm(Integer tm) {
		this.tm = tm;
	}

	public Integer getTp() {
		return tp;
	}

	public void setTp(Integer tp) {
		this.tp = tp;
	}

	public String getLogopath() {
		return logopath;
	}

	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.io.File getUpload() {
		return upload;
	}

	public void setUpload(java.io.File upload) {
		this.upload = upload;
	}

	public String addUi() {
		
		return "add";
	}
	
	public String add() throws Exception {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(name);
		/*if(upload!=null&&upload.length()>0) {
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd/HH");
			String timestamp = date.format(new Date());
			String logopathDirName = "/image/brand/" + timestamp;//�����Ա���·��
			String realLogopathDir = ServletActionContext.getServletContext().getRealPath(logopathDirName);
			String imageName = UUID.randomUUID().toString().substring(UUID.randomUUID().toString().lastIndexOf("-")+1);
			//String ext = upload.getAbsolutePath().substring(upload.getAbsolutePath().lastIndexOf("."));
			String ext = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			String image = imageName+ext;
			File logoDir = new File(realLogopathDir);
			if(!logoDir.exists()) logoDir.mkdirs();
			FileOutputStream out = new FileOutputStream(new File(realLogopathDir, image));
			FileInputStream in = new FileInputStream(upload);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=in.read(buffer))>0) {
				out.write(buffer,0,len);
			}
			out.close();
			String logopath = realLogopathDir +"/" + image;
			userInfo.setLogopath(logopath);
		}*/
		us.save(userInfo);
		ActionContext.getContext().put("message", "操作成功");
		ActionContext.getContext().put("url", UrlUtil.readUrl("userlist"));
		return Action.SUCCESS;
	}
	
	public String editUi() {
		UserInfo user = us.find(UserInfo.class, userid);
		name = user.getUsername();
		tm = user.getTm();
		tp = user.getTp();
		return "edit";
	}
	
	public String edit() throws IOException {
		UserInfo userInfo = us.find(UserInfo.class, userid);
		userInfo.setUsername(name);
		userInfo.setTm(tm);
		userInfo.setTp(tp);
		/*if(upload!=null&&upload.length()>0) {
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd/HH");
			String timestamp = date.format(new Date());
			String logopathDirName = "/image/brand/" + timestamp;//�����Ա���·��
			String realLogopathDir = ServletActionContext.getServletContext().getRealPath(logopathDirName);
			String imageName = UUID.randomUUID().toString().substring(UUID.randomUUID().toString().lastIndexOf("-")+1);
			//String ext = upload.getAbsolutePath().substring(upload.getAbsolutePath().lastIndexOf("."));
			String ext = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			String image = imageName+ext;
			File logoDir = new File(realLogopathDir);
			if(!logoDir.exists()) logoDir.mkdirs();
			FileOutputStream out = new FileOutputStream(new File(realLogopathDir, image));
			FileInputStream in = new FileInputStream(upload);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=in.read(buffer))>0) {
				out.write(buffer,0,len);
			}
			out.close();
			String logopath = realLogopathDir +"/" + image;
			brand.setLogopath(logopath);
		}*/
		us.update(userInfo);
		ActionContext.getContext().put("message", "操作成功");
		ActionContext.getContext().put("url", UrlUtil.readUrl("userlist"));
		return Action.SUCCESS;
	}
	
	public String queryUi() {
		return "query";
	}

}
