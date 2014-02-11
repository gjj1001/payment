package com.casit.tee686.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.casit.bean.entity.PubContent;
import com.casit.service.PublishService;
import com.casit.util.Base64;

/**
 * Servlet implementation class UserPublishServlet
 */
@Component
public class UserPublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PubContent pubContent;
	@Resource
	PublishService ps;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPublishServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");		
		String file=request.getParameter("file");
		if(file==null) {
			StringBuffer data = new StringBuffer();
			byte[] buffer = new byte[1024];
			while(request.getInputStream().read(buffer) != -1) {
				data.append(new String(buffer, "utf-8"));
			}
			System.out.println(data.toString());
			/*if(data.indexOf("file")!=-1) {
				file = data.substring(data.indexOf("file"));
				data = data.delete(data.indexOf("file"), data.length());
				System.out.println(file);
				System.out.println(data.toString());
			}*/
			pubContent = new ObjectMapper().readValue(data.toString(), PubContent.class);		
			ps.save(pubContent);
			PrintWriter out = response.getWriter();
			out.write("发布成功");
			out.flush();
			out.close();
		} else {
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
				fos.write(b);
				fos.flush();
				fos.close();
				String uploadImage = request.getRequestURL().toString().substring(0, 
						request.getRequestURL().toString().lastIndexOf("/")+1)+"upload_image/"+
						imagefile.getPath().substring(imagefile.getPath().lastIndexOf(File.separator)+1);
				System.out.println(uploadImage);
				List<PubContent> list = ps.getScrollData(PubContent.class).getResultList();
				Collections.reverse(list);
				pubContent = list.get(0);
				pubContent.setImageFile(uploadImage);				
				ps.update(pubContent);
				PrintWriter wirter = response.getWriter();
				wirter.write(uploadImage);
				wirter.flush();
				wirter.close();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
	
}
