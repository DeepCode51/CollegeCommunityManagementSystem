package com.so.team.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.so.system.bean.User;
import com.so.team.bean.MoneyManger;
import com.so.team.bean.SiteNews;
import com.so.team.bean.Teams;
import com.so.team.dao.MoneyMangerDao;
import com.so.team.service.MoneyMangerService;
import com.so.team.service.SiteNewsService;
import com.so.team.service.impl.MoneyMangerServiceImpl;
import com.so.team.service.impl.SiteNewsServiceImpl;
import com.so.utils.CurrentUserUtils;
import com.so.utils.Page;
import com.so.utils.PropertiesUtil;


@WebServlet("/moneyManger")
public class MoneyMangerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	MoneyMangerDao moneyMangerDao=new MoneyMangerDao();
	MoneyMangerService moneyMangerService = new MoneyMangerServiceImpl();
	
	SiteNewsService siteNewsService = new SiteNewsServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contextPath = request.getServletContext().getContextPath();
		String method = request.getParameter("method");
		if ("save".equals(method)) {
			save(request,response);
		}else if ("delete".equals(method)) {
			delete(request, response);
		}else if ("list".equals(method)) {
			list(request, response);
		}else if ("form".equals(method)) {
			form(request, response);
		}
		
	}
	
	//添加保存
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MoneyManger moneyManger = new MoneyManger();
		 //这是上传到tomcat下，文件容易丢失
		 //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/upload");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        //上传到服务器硬盘上，保证重启tomcat不会丢失文件
        //获取上传文件的路径
//        String savePath = PropertiesUtil.getValue("fileBaseUrl")+ PropertiesUtil.getValue("fileUrl");
//        File file = new File(savePath+"/");
//        if (!file.exists() && !file.isDirectory()) {
//            System.out.println(savePath+"目录不存在，需要创建");
//            //创建目录
//            file.mkdirs();
//        }
        //消息提示
        String msg = "";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                String str = "";
            	String name = item.getFieldName();
                if(item.isFormField()){
                	str = item.getString("UTF-8");
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                   	String filename = item.getName();//文件名
                	String fileType = filename.substring(filename.lastIndexOf(".")+1);//文件后缀
                	double fileSize = item.getSize()/1000;//文件大小
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    /*filename = filename.substring(filename.lastIndexOf("\\")+1);*/
                    String newFileName = UUID.randomUUID().toString()+"."+fileType;
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
 					msg = "文件上传成功！";
 					str = contextPath+"\\" + newFileName;
                }
                
                //解决普通输入项的数据的中文乱码问题
				if ("id".endsWith(name)) {
                   	if (str!=null && str!="") {
                   		moneyManger.setId(str);
					}
				}
				
				if ("actId".endsWith(name)) {
					moneyManger.setActId(str);
					
				}
				
				if ("type".endsWith(name)) {
					moneyManger.setType(str);
					
				}
				
				if ("money".endsWith(name)) {
					moneyManger.setMoney(Double.parseDouble(str));
					
				}
				
				if ("remark".endsWith(name)) {
					moneyManger.setRemark(str);
					
				}
                
            }
            if (moneyManger.getId()!=null && !"".equals(moneyManger.getId())) {
    			moneyMangerService.update(moneyManger);
    		}else{
    			moneyMangerService.add(moneyManger);
    		}
    		response.sendRedirect(contextPath+"/moneyManger?method=list");
        }catch (Exception e) {
            msg= "服务异常！";
            e.printStackTrace();
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/views/team/moneyMangerForm.jsp").forward(request, response);
        }
	}
	
	//删除
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		moneyMangerService.delete(id);
		response.sendRedirect(contextPath+"/moneyManger?method=list");
	}
	
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MoneyManger moneyManger = new MoneyManger();
		
		SiteNews siteNews = new SiteNews();
		siteNews.setIsAudit("1");
		siteNews.setNewsType("2");//活动
		
		User currentUser = CurrentUserUtils.getCurrentUser(request);
		String roleFlag = currentUser.getRole2().getRoleFlag();
		if ("leader".equals(roleFlag)) {
			
			siteNews.setCreateUser(currentUser.getUsername());
			
		}
		
		List<SiteNews> findAll = siteNewsService.findAll(siteNews);
		request.setAttribute("findAll", findAll);
		if ("leader".equals(roleFlag)) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(" and a.act_id in (");
			for (SiteNews siteNews2 : findAll) {
				stringBuffer.append("'"+siteNews2.getId()+"',");
			}
			stringBuffer.append(" '1')");
			moneyManger.setSqlStr(stringBuffer.toString());
		}
		//分页有关
		Page<MoneyManger> page = new Page<MoneyManger>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String actId = request.getParameter("actId");
		if (actId != null && actId != "") {
			moneyManger.setActId(actId);
			request.setAttribute("actId", actId);
		}
		String type = request.getParameter("type");
		if (type != null && type != "") {
			moneyManger.setType(type);
			request.setAttribute("type", type);
		}
		
		//判断提示信息
		Object msg = request.getSession().getAttribute("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
			request.getSession().removeAttribute("msg");
		}
		
		page = moneyMangerService.page(moneyManger, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/team/moneyMangerList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MoneyManger moneyManger = new MoneyManger();
		if (id!=null && id!="") {
			moneyManger = moneyMangerService.getById(id);
		}
		request.setAttribute("moneyManger", moneyManger);
		
		
		SiteNews siteNews = new SiteNews();
		siteNews.setIsAudit("1");
		siteNews.setNewsType("2");//活动
		
		User currentUser = CurrentUserUtils.getCurrentUser(request);
		String roleFlag = currentUser.getRole2().getRoleFlag();
		if ("leader".equals(roleFlag)) {
			
			siteNews.setCreateUser(currentUser.getUsername());
		}
		
		List<SiteNews> findAll = siteNewsService.findAll(siteNews);
		request.setAttribute("findAll", findAll);
		request.getRequestDispatcher("/views/team/moneyMangerForm.jsp").forward(request, response);
	}
	
}