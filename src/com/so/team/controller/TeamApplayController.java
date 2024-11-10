package com.so.team.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.so.system.bean.User;
import com.so.team.bean.TeamApplay;
import com.so.team.bean.Teams;
import com.so.team.dao.TeamApplayDao;
import com.so.team.service.TeamApplayService;
import com.so.team.service.TeamsService;
import com.so.team.service.impl.TeamApplayServiceImpl;
import com.so.team.service.impl.TeamsServiceImpl;
import com.so.utils.CurrentUserUtils;
import com.so.utils.Page;
import com.so.utils.PropertiesUtil;

@WebServlet("/teamApplay")
public class TeamApplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	TeamApplayDao teamApplayDao=new TeamApplayDao();
	TeamApplayService teamApplayService = new TeamApplayServiceImpl();
	TeamsService teamsService = new TeamsServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contextPath = request.getServletContext().getContextPath();
		String method = request.getParameter("method");
		if ("add".equals(method)) {
			add(request,response);
		}else if ("delete".equals(method)) {
			delete(request, response);
		}else if ("list".equals(method)) {
			list(request, response);
		}else if ("update".equals(method)) {
			update(request, response);
		}else if ("form".equals(method)) {
			form(request, response);
		}else if ("save".equals(method)) {
			save(request, response);
		}
		
	}
	
	//添加
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String applayTeam = request.getParameter("applayTeam");
		String applayStu = request.getParameter("applayStu");
		String applayContent = request.getParameter("applayContent");
		String applayTime = request.getParameter("applayTime");
		String applayType = request.getParameter("applayType");
		TeamApplay teamApplay = new TeamApplay();
		teamApplay.setApplayTeam(applayTeam);
		teamApplay.setApplayStu(applayStu);
		teamApplay.setApplayContent(applayContent);
		teamApplay.setApplayType(applayType);
		teamApplayService.add(teamApplay);
		response.sendRedirect(contextPath+"/teamApplay?method=list");
	}
	
	//添加保存
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeamApplay teamApplay = new TeamApplay();
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
//        String savePath = PropertiesUtil.getValue("fileUpload");
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
                if(item.isFormField()){
                    String name = item.getFieldName();
                     //解决普通输入项的数据的中文乱码问题
					if ("id".endsWith(name)) {
                    	if (item.getString("UTF-8")!=null && item.getString("UTF-8")!="") {
                    		teamApplay.setId(item.getString("UTF-8"));
						}
					}
					
					if ("applayTeam".endsWith(name)) {
						teamApplay.setApplayTeam(item.getString("UTF-8"));
						
					}
					
					if ("applayStu".endsWith(name)) {
						teamApplay.setApplayStu(item.getString("UTF-8"));
						
					}
					
					if ("applayContent".endsWith(name)) {
						teamApplay.setApplayContent(item.getString("UTF-8"));
						
					}
					
					if ("applayTime".endsWith(name)) {
						teamApplay.setApplayTime(Timestamp.valueOf(item.getString("UTF-8")));
						
					}
					
					if ("applayType".endsWith(name)) {
						teamApplay.setApplayType(item.getString("UTF-8"));
						
					}
                    
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
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
                    
                }
            }
            if (teamApplay.getId()!=null && !"".equals(teamApplay.getId())) {
    			teamApplayService.update(teamApplay);
    		}else{
    			teamApplayService.add(teamApplay);
    		}
    		response.sendRedirect(contextPath+"/teamApplay?method=list");
        }catch (Exception e) {
            msg= "文件上传失败！";
            e.printStackTrace();
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/views/team/teamApplayForm.jsp").forward(request, response);
        }
	}
	
	//删除
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		teamApplayService.delete(id);
		response.sendRedirect(contextPath+"/teamApplay?method=list");
	}
	
	//修改
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String applayTeam = request.getParameter("applayTeam");
		String applayStu = request.getParameter("applayStu");
		String applayContent = request.getParameter("applayContent");
		String applayTime = request.getParameter("applayTime");
		String applayType = request.getParameter("applayType");
		TeamApplay teamApplay = new TeamApplay();
		teamApplay.setId(id);
		teamApplay.setApplayTeam(applayTeam);
		teamApplay.setApplayStu(applayStu);
		teamApplay.setApplayContent(applayContent);
		teamApplay.setApplayType(applayType);
		teamApplayService.update(teamApplay);
		request.setAttribute("teamApplay", teamApplay);
		request.getRequestDispatcher("/views/team/teamApplayForm.jsp").forward(request, response);
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		TeamApplay teamApplay = new TeamApplay();
		//分页有关
		Page<TeamApplay> page = new Page<TeamApplay>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String applayTeam = request.getParameter("applayTeam");
		if (applayTeam != null && applayTeam != "") {
			teamApplay.setApplayTeam(applayTeam);
			request.setAttribute("applayTeam", applayTeam);
		}
		String applayStu = request.getParameter("applayStu");
		if (applayStu != null && applayStu != "") {
			teamApplay.setApplayStu(applayStu);
			request.setAttribute("applayStu", applayStu);
		}
		String applayType = request.getParameter("applayType");
		if (applayType != null && applayType != "") {
			teamApplay.setApplayType(applayType);
			request.setAttribute("applayType", applayType);
		}
		
		//判断提示信息
		Object msg = request.getSession().getAttribute("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
			request.getSession().removeAttribute("msg");
		}
		
		//根据登录用户查出所有的社团
		User currentUser = CurrentUserUtils.getCurrentUser(request);
		String roleFlag = currentUser.getRole2().getRoleFlag();
		if ("leader".equals(roleFlag)) {
			//如果是社长，查出自己负责的所以社团
			Teams teams = new Teams();
			teams.setBuildStu(currentUser.getId());
			List<Teams> findAll = teamsService.findAll(teams);
			request.setAttribute("teamList", findAll);
			
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(" and a.applay_team in (");
			for (Teams teams2 : findAll) {
				stringBuffer.append("'"+teams2.getId()+"',");
			}
			stringBuffer.append(" '1')");
			teamApplay.setSqlStr(stringBuffer.toString());
		}else{
			request.setAttribute("teamList", teamsService.findAll(new Teams()));
		}
		
		page = teamApplayService.page(teamApplay, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/team/teamApplayList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		TeamApplay teamApplay = new TeamApplay();
		if (id!=null && id!="") {
			teamApplay = teamApplayService.getById(id);
		}
		request.setAttribute("teamApplay", teamApplay);
		request.getRequestDispatcher("/views/team/teamApplayForm.jsp").forward(request, response);
	}
	
}