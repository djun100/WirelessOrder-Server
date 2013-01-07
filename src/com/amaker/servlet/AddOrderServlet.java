package com.amaker.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.amaker.bean.Order;
import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;

public class AddOrderServlet extends HttpServlet {
	private OrderDao dao = new OrderDaoImpl();
	private String IMAGE_PATH = "/file/Images";

	public AddOrderServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();

		DiskFileItemFactory factory=new DiskFileItemFactory(); 
		
		ServletFileUpload file = new ServletFileUpload(factory);
		Order order = new Order();
		try {
			List list = file.parseRequest(request); 
			Iterator<ServletFileUpload> it = list.iterator();
			while(it.hasNext()){ 
				
				FileItem  fileItem=(FileItem)it.next(); 
				if(fileItem.isFormField()){
					if("orderName".equals(fileItem.getFieldName())){
						order.setName(fileItem.getString("UTF-8"));
					} else if("orderDesc".equals(fileItem.getFieldName())){
						order.setDescription(fileItem.getString("UTF-8"));
					} else if("orderType".equals(fileItem.getFieldName())){
						order.setType(fileItem.getString("UTF-8"));
					} else if("orderPrice".equals(fileItem.getFieldName())){
						order.setPrice(fileItem.getString("UTF-8"));
					}
				} else { 
					if(fileItem.getName()!=null&&!fileItem.getName().equals("")){
						
						String filename = fileItem.getName();
						String ext = filename.substring(filename.lastIndexOf(".") + 1);
						
						if(!"jpgpngbmp".contains(ext)){
							out.println("图片格式必须为：jpg、png、bmp");
							return ;
						}
						if(fileItem.getSize() > 1024 * 1024){ // 1 M 
							out.println("图片不能大于1M");
							return ;
						}
						
						String newname = System.currentTimeMillis() + "." + ext;
						
						String str = this.getClass().getResource("/").getPath();
						str = str.replace("WEB-INF/upload/", "").substring(1);
						
						String imagesPath = str + IMAGE_PATH;
						
						File dir = new File(imagesPath);
						if(!dir.exists() && !dir.isDirectory())
							dir.mkdirs();
						
						File realFile=new File(imagesPath, newname);
						fileItem.write(realFile);
						order.setImgage_path(IMAGE_PATH + newname);
					}
				}
			}

			if(dao.addOrder(order) > 0)
				out.println("添加成功");
			else
				out.println("添加失败");
		} catch (FileUploadException e) {
			out.println("添加失败");
			e.printStackTrace();
		} catch (Exception e) {
			out.println("添加失败");
			e.printStackTrace();
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void init() throws ServletException {
	}

}
