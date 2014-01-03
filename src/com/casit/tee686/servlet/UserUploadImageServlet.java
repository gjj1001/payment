package com.casit.tee686.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.casit.util.Base64;

/**
 * Servlet implementation class UserUploadImageServlet
 */
@Component
public class UserUploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUploadImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * List<String> uploadImages = new ArrayList<String>(); Map<String,
		 * String> map = new HashMap<String, String>(); boolean isUpload =
		 * ServletFileUpload.isMultipartContent(request); if(isUpload) {
		 * List<File> fileList = new ArrayList<File>(); DiskFileItemFactory
		 * itemFactory = new DiskFileItemFactory(1024*64, //写满该大小缓存后存入硬盘 new
		 * File("c:/tempUpload/")); ServletFileUpload fileUpload = new
		 * ServletFileUpload(itemFactory);
		 * fileUpload.setSizeMax(5*1024*1024);//设置上传文件大小 try {
		 * List<DiskFileItem> diskFileItems = fileUpload.parseRequest(request);
		 * for(DiskFileItem item : diskFileItems) { if(!item.isFormField())
		 * {//isFormField方法用于判断FileItem 是否代表一个普通表单域(即非file表单域) String path =
		 * item.getName(); String filename =
		 * path.substring(path.lastIndexOf("\\")); File uploadFile = new
		 * File("c:/uploadfile/"+filename); fileList.add(uploadFile); } else {
		 * map.put(item.getFieldName(), item.getString()); }
		 * 
		 * } for(File file : fileList) {
		 * uploadImages.add(file.getAbsolutePath()); } for(Iterator<String> itr
		 * = uploadImages.iterator(); itr.hasNext();) {
		 * System.out.println(itr.next()); } Set<Map.Entry<String, String>> set
		 * = map.entrySet(); for(Iterator<Map.Entry<String, String>> iterator =
		 * set.iterator(); iterator.hasNext();) { Map.Entry<String, String>
		 * entry = (Map.Entry<String, String>) iterator.next(); PubKey pubKey =
		 * PubKey.valueOf(entry.getKey()); switch (pubKey) { case HEADIMAGE:
		 * if(!uploadImages.isEmpty()) {
		 * pubContent.setImageFile(uploadImages.get(0)); } break; case SENDTIME:
		 * pubContent.setSendtime(entry.getValue()); break; case USERNAME:
		 * pubContent.setUsername(entry.getValue()); break; default:
		 * pubContent.setContent(entry.getValue()); break; } } } catch
		 * (FileUploadException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } else {
		 * pubContent.setContent(request.getParameter("content"));
		 * pubContent.setHeadimage(request.getParameter("headimage"));
		 * pubContent.setSendtime(request.getParameter("sendtime"));
		 * pubContent.setUsername(request.getParameter("username"));
		 * response.setContentType("text/plain");
		 * response.setCharacterEncoding("utf-8"); PrintWriter writer =
		 * response.getWriter(); writer.write("上传图片失败"); writer.flush();
		 * writer.close(); }
		 */
		String file=request.getParameter("file");
        if(file!=null){
			byte[] b = Base64.decode(file);
			// String filepath = getServletContext().getRealPath("/files");
			File upload = new File(request.getSession().getServletContext().getRealPath(File.separatorChar+"upload_image"));			
			try {
				if (!upload.exists()) {
					upload.mkdirs();
				}
				File imagefile = new File(upload.getPath()+File.separatorChar
						+ System.currentTimeMillis()+ ".jpg");
				FileOutputStream fos = new FileOutputStream(imagefile);
				System.out.println(upload.getPath());
				System.out.println(request.getRequestURL().toString().substring(0, 
						request.getRequestURL().toString().lastIndexOf("/")+1)+"upload_image/"+
						imagefile.getPath().substring(imagefile.getPath().lastIndexOf(File.separator)+1));
				fos.write(b);
				fos.flush();
				fos.close();				
				response.setContentType("text/plain");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.write("发布成功");
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				responseInfo(response);
			} catch(IOException e) {
				e.printStackTrace();
				responseInfo(response);
			} catch(SecurityException e) {
				e.printStackTrace();
				responseInfo(response);
			}
        }
        
	}

	/**图片上传失败
	 * @param response
	 * @throws IOException
	 */
	private void responseInfo(HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.write("图片上传失败");
		out.flush();
		out.close();
	}


/*public enum PubKey {
		CONTENT("content"), HEADIMAGE("headimage"), SENDTIME("sendtime"), USERNAME(
				"username");

		public String name;

		private PubKey(String name) {
			this.name = name;
		}
	}*/	

}
