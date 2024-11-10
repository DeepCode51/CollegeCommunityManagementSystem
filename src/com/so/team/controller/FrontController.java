package com.so.team.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
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
import com.so.team.bean.ActiveStu;
import com.so.team.bean.Message;
import com.so.team.bean.SiteNews;
import com.so.team.bean.Student;
import com.so.team.bean.TeamApplay;
import com.so.team.bean.Teams;
import com.so.team.dao.TeamsDao;
import com.so.team.service.ActiveStuService;
import com.so.team.service.MessageService;
import com.so.team.service.SiteNewsService;
import com.so.team.service.StudentService;
import com.so.team.service.TeamApplayService;
import com.so.team.service.TeamsService;
import com.so.team.service.impl.ActiveStuServiceImpl;
import com.so.team.service.impl.MessageServiceImpl;
import com.so.team.service.impl.SiteNewsServiceImpl;
import com.so.team.service.impl.StudentServiceImpl;
import com.so.team.service.impl.TeamApplayServiceImpl;
import com.so.team.service.impl.TeamsServiceImpl;
import com.so.utils.CurrentUserUtils;
import com.so.utils.Page;
import com.so.utils.PropertiesUtil;

@WebServlet("/f")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	TeamsDao teamsDao=new TeamsDao();
	TeamsService teamsService = new TeamsServiceImpl();
	SiteNewsService siteNewsService = new SiteNewsServiceImpl();
	TeamApplayService teamApplayService = new TeamApplayServiceImpl();
	StudentService studentService = new StudentServiceImpl();
	ActiveStuService activeStuService = new ActiveStuServiceImpl();
	MessageService messageService = new MessageServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contextPath = request.getServletContext().getContextPath();
		String method = request.getParameter("action");
		request.setAttribute("method", method);
		if ("detail".equals(method)) {
			detail(request, response);
		}else if ("teamDetail".equals(method)) {
			teamDetail(request, response);
		}else if ("applayLeader".equals(method)) {
			applayLeader(request, response);
		}else if ("regit".equals(method)) {
			regit(request, response);
		}else if ("login".equals(method)) {
			login(request, response);
		}else if ("applayTeam".equals(method)) {
			applayTeam(request, response);	
		}else if ("logout".equals(method)) {
			logout(request, response);	
		}else if ("applayAct".equals(method)) {
			applayAct(request, response);	
		}else if ("applayBuildTeam".equals(method)) {
			applayBuildTeam(request, response);	
		}else if ("userInfo".equals(method)) {
			userInfo(request, response);	
		}else if ("getMyActApplay".equals(method)) {
			getMyActApplay(request, response);	
		}else if ("getMyTeamApplay".equals(method)) {
			getMyTeamApplay(request, response);	
		}else if ("siteNewsList".equals(method)) {
			siteNewsList(request, response);	
		}else if ("getMyTeam".equals(method)) {
			getMyTeam(request, response);	
		}else if ("delete".equals(method)) {
			delete(request, response);	
		}else if ("form".equals(method)) {
			form(request, response);	
		}else if ("saveMsg".equals(method)) {
			saveMsg(request, response);	
		}else {
			index(request, response);
		}
		
	}
	
	private void saveMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		String createStu = request.getParameter("createStu");
		String auditType = request.getParameter("auditType");
		Message message = new Message();
		message.setContent(content);
		message.setCreateTime(new Date());
		message.setCreateStu(createStu);
		message.setAuditType(auditType);
		messageService.add(message);
		request.setAttribute("message", message);
		response.setCharacterEncoding("GBK");
		response.getWriter().write("<script>alert('留言成功！请耐心等待回复！');window.location.href='"+contextPath+"/f'</script>");
		
		
	}

		//删除
		private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			teamsService.delete(id);
			response.sendRedirect(contextPath+"/f?action=getMyTeam");
		}
	
	//form跳转页面
		private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			Teams teams = new Teams();
			if (id!=null && id!="") {
				teams = teamsService.getById(id);
			}
			request.setAttribute("teams", teams);
			request.getRequestDispatcher("/front/applayTeam.jsp").forward(request, response);
		}
	
	private void getMyTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teams teams = new Teams();
		//分页有关
		Page<Teams> page = new Page<Teams>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String teamName = request.getParameter("teamName");
		if (teamName != null && teamName != "") {
			teams.setTeamName(teamName);
			request.setAttribute("teamName", teamName);
		}
		String buildStu = request.getParameter("id");
		if (buildStu != null && buildStu != "") {
			teams.setBuildStu(buildStu);
			request.setAttribute("buildStu", buildStu);
		}
		String auditType = request.getParameter("auditType");
		if (auditType != null && auditType != "") {
			teams.setAuditType(auditType);
			request.setAttribute("auditType", auditType);
		}
		
		//判断提示信息
		Object msg = request.getSession().getAttribute("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
			request.getSession().removeAttribute("msg");
		}
		
		page.setPageSize(20);
		
		page = teamsService.page(teams, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/front/myTeamsList.jsp").forward(request, response);
		
	}

	private void siteNewsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsType = request.getParameter("newsType");
		request.setAttribute("newsType", newsType);
		String title = request.getParameter("title");
		request.setAttribute("title", title);
		//查出左侧列表数据
		SiteNews siteNews = new SiteNews();
		siteNews.setIsAudit("1");
		siteNews.setNewsType(newsType);
		if (title!=null && !"".equals(title)) {
			siteNews.setNewsType(title);
		}
		Page<SiteNews> page = new Page<SiteNews>();
		page.setPageSize(15);
		Page<SiteNews> leftNews = siteNewsService.page(siteNews, page);
		request.setAttribute("page", leftNews);
		request.getRequestDispatcher("/front/siteNewsList.jsp").forward(request, response);
	}
	
	private void getMyTeamApplay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		TeamApplay teamApplay = new TeamApplay();
		teamApplay.setApplayStu(id);
		Page<TeamApplay> page = new Page<TeamApplay>();
		Page<TeamApplay> teams = teamApplayService.page(teamApplay, page);
		request.setAttribute("teams", teams);
		request.getRequestDispatcher("/front/myTeams.jsp").forward(request, response);
	}
	
	//查看我的活动申请信息
	private void getMyActApplay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ActiveStu activeStu = new ActiveStu();
		activeStu.setStuId(id);
		Page<ActiveStu> page = new Page<ActiveStu>();
		Page<ActiveStu> findAll = activeStuService.page(activeStu,page);
		request.setAttribute("acts", findAll);
		 request.getRequestDispatcher("/front/myActives.jsp").forward(request, response);
	}

	//跳转到个人中心
	private void userInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.getRequestDispatcher("/front/userInfo.jsp").forward(request, response);
	}

	private void applayBuildTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   {
		response.setCharacterEncoding("GBK");
		Teams teams = new Teams();
		//上传到服务器硬盘上，保证重启tomcat不会丢失文件
        //获取上传文件的路径
		String savePath = this.getServletContext().getRealPath("/upload");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        
        
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
                    		teams.setId(item.getString("UTF-8"));
						}
					}
					
					if ("teamName".endsWith(name)) {
						teams.setTeamName(item.getString("UTF-8"));
						
					}
					
					if ("pic".endsWith(name)) {
						teams.setPic(item.getString("UTF-8"));
						
					}
					
					if ("teamIntro".endsWith(name)) {
						teams.setTeamIntro(item.getString("UTF-8"));
						
					}
					
					if ("buildTime".endsWith(name)) {
						if(item.getString("UTF-8")!=null &&item.getString("UTF-8")!="") {
						
						teams.setBuildTime(Timestamp.valueOf(item.getString("UTF-8")));
						}
						
					}
					
					if ("buildStu".endsWith(name)) {
						teams.setBuildStu(item.getString("UTF-8"));
						
					}
					
					if ("auditType".endsWith(name)) {
						teams.setAuditType(item.getString("UTF-8"));
						
					}
					
					if ("members".endsWith(name)) {
						teams.setMembers(Integer.parseInt(item.getString("UTF-8")));
						
					}
					
					if ("remark".endsWith(name)) {
						teams.setRemark(item.getString("UTF-8"));
						
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
                    
                    String suffix = filename.substring(filename.lastIndexOf("."));
                    filename=String.valueOf(System.currentTimeMillis()+suffix);
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
                    
                    teams.setPic(request.getContextPath()+"/upload" + "/" + filename);
 					//teams.setPic(savePath.substring(savePath.lastIndexOf("\\")) + "\\" + filename);
                }
            }
            if (teams.getId()!=null && !"".equals(teams.getId())) {
    			teamsService.update(teams);
    			response.getWriter().write("<script>alert('修改成功！请耐心等待审核结果！');window.location.href='"+contextPath+"/f?action=form&id="+teams.getId()+"'</script>");
    		}else{
    			teams.setBuildTime(new Date());
    			teamsService.add(teams);
    			response.getWriter().write("<script>alert('申请成功！请耐心等待审核结果！');window.location.href='"+contextPath+"/f'</script>");
    		}
            }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","申请失败，系统异常！");
            request.getRequestDispatcher("/front/applayTeam.jsp").forward(request, response);
        }
	}
	
	//修改
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String teamName = request.getParameter("teamName");
		String pic = request.getParameter("pic");
		String teamIntro = request.getParameter("teamIntro");
		String buildTime = request.getParameter("buildTime");
		String buildStu = request.getParameter("buildStu");
		String auditType = request.getParameter("auditType");
		String members = request.getParameter("members");
		String remark = request.getParameter("remark");
		Teams teams = new Teams();
		teams.setId(id);
		teams.setTeamName(teamName);
		teams.setPic(pic);
		teams.setTeamIntro(teamIntro);
		teams.setBuildStu(buildStu);
		teams.setAuditType(auditType);
		teams.setMembers(Integer.parseInt(members));
		teams.setRemark(remark);
		teamsService.update(teams);
		request.setAttribute("teams", teams);
		request.getRequestDispatcher("/views/team/teamsForm.jsp").forward(request, response);
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		Teams teams = new Teams();
		//分页有关
		Page<Teams> page = new Page<Teams>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String teamName = request.getParameter("teamName");
		if (teamName != null && teamName != "") {
			teams.setTeamName(teamName);
			request.setAttribute("teamName", teamName);
		}
		String buildStu = request.getParameter("buildStu");
		if (buildStu != null && buildStu != "") {
			teams.setBuildStu(buildStu);
			request.setAttribute("buildStu", buildStu);
		}
		String auditType = request.getParameter("auditType");
		if (auditType != null && auditType != "") {
			teams.setAuditType(auditType);
			request.setAttribute("auditType", auditType);
		}
		
		//判断提示信息
		Object msg = request.getSession().getAttribute("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
			request.getSession().removeAttribute("msg");
		}
		
		page = teamsService.page(teams, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/team/teamsList.jsp").forward(request, response);
		
	}

	private void applayAct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String activeId = request.getParameter("activeId");
		String stuId = request.getParameter("stuId");
		String stuName = request.getParameter("stuName");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		String isArrive = request.getParameter("isArrive");
		ActiveStu activeStu = new ActiveStu();
		activeStu.setActiveId(activeId);
		activeStu.setStuId(stuId);
		response.setCharacterEncoding("GBK");
		List<ActiveStu> findAll = activeStuService.findAll(activeStu);
		if (findAll!=null && findAll.size()>0) {
			response.getWriter().write("<script>alert('已经申请过，不能在申请。');window.location.href='"+contextPath+"/f?action=detail&id="+activeId+"';</script>");
		}else{
			activeStu.setStuName(stuName);
			activeStu.setPhone(phone);
			activeStu.setType(type);
			activeStu.setIsArrive(isArrive);
			activeStuService.add(activeStu);
	        response.getWriter().write("<script>alert('申请成功，请按时参加。');window.location.href='"+contextPath+"/f?action=detail&id="+activeId+"';</script>");
		}
		
	}

	//获取新闻活动详情
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		SiteNews news = siteNewsService.getById(id);
		request.setAttribute("news", news);
		//查出左侧列表数据
		String newsType = news.getNewsType();
		SiteNews siteNews = new SiteNews();
		siteNews.setIsAudit("1");
		siteNews.setNewsType(newsType);
		Page<SiteNews> page = new Page<SiteNews>();
		page.setPageSize(15);
		Page<SiteNews> leftNews = siteNewsService.page(siteNews, page);
		request.setAttribute("leftNews", leftNews);
		request.getRequestDispatcher("/front/detail.jsp").forward(request, response);
	}
	
	//查看社团详情
	protected void teamDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Teams team = teamsService.getById(id);
		request.setAttribute("team", team);
		//查出所有社团
		Teams teams = new Teams();
		teams.setAuditType("1");
		List<Teams> teamList = teamsService.findAll(teams);
		request.setAttribute("teamList", teamList);
		request.getRequestDispatcher("/front/teamDetail.jsp").forward(request, response);
		
	}
	
	//申请社团
	private void applayLeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object attribute = request.getSession().getAttribute("flogin");
		if (attribute!=null) {
			Student student = new Student();
			student = (Student) attribute;
			TeamApplay teamApplay = new TeamApplay();
			teamApplay.setApplayStu(student.getId());
			String id = request.getParameter("id");
			teamApplay.setApplayTeam(id);
			teamApplay.setApplayType("0");
			List<TeamApplay> findAll = teamApplayService.findAll(teamApplay);
			if (findAll!=null && findAll.size()>0) {
				response.getWriter().print(2);
			}else{
				teamApplay.setApplayContent("申请做社长！");
				teamApplay.setApplayTime(new Date());
				teamApplay.setApplayType("0");//未审核
				teamApplayService.add(teamApplay);
				response.getWriter().print(3);
			}
		}else{
			response.getWriter().print(1);
		}
	}
	
	
	private void applayTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object attribute = request.getSession().getAttribute("flogin");
		if (attribute!=null) {
			Student student = new Student();
			student = (Student) attribute;
			TeamApplay teamApplay = new TeamApplay();
			teamApplay.setApplayStu(student.getId());
			String id = request.getParameter("id");
			teamApplay.setApplayTeam(id);
			teamApplay.setApplayType("0");
			List<TeamApplay> findAll = teamApplayService.findAll(teamApplay);
			teamApplay.setApplayType("1");
			List<TeamApplay> findAll1 = teamApplayService.findAll(teamApplay);
			if (findAll!=null && findAll.size()>0) {
				response.getWriter().print(2);
			}else if (findAll1!=null && findAll1.size()>0) {
				response.getWriter().print(3);
			}else{
				teamApplay.setApplayContent("申请加入社团！");
				teamApplay.setApplayTime(new Date());
				teamApplay.setApplayType("0");//未审核
				teamApplayService.add(teamApplay);
				response.getWriter().print(3);
			}
		}else{
			response.getWriter().print(1);
		}
	}
	
	
	//列表查询
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查出所有社团
		Teams teams = new Teams();
		teams.setAuditType("1");
		List<Teams> teamList = teamsService.findAll(teams);
		request.setAttribute("teams", teamList);
		//查出最新的6条活动内容 2是活动
		SiteNews siteNews = new SiteNews();
		siteNews.setIsAudit("1");
		siteNews.setNewsType("2");
		Page<SiteNews> page = new Page<SiteNews>();
		page.setPageSize(6);
		Page<SiteNews> actives = siteNewsService.page(siteNews, page);
		request.setAttribute("actives", actives);
		//查出最新的6条站内新闻 1是新闻
		siteNews.setNewsType("1");
		Page<SiteNews> pageNews = new Page<SiteNews>();
		page.setPageSize(6);
		Page<SiteNews> news = siteNewsService.page(siteNews, pageNews);
		request.setAttribute("news", news);
		
		//查出最新的6条社团风采
		siteNews.setNewsType("3");
		Page<SiteNews> pageFengcai = new Page<SiteNews>();
		page.setPageSize(6);
		pageFengcai = siteNewsService.page(siteNews, pageFengcai);
		request.setAttribute("pageFengcai", pageFengcai);
		//查出所有的推荐活动
		siteNews.setNewsType("2");
		siteNews.setIsRun("1");
		List<SiteNews> findAll = siteNewsService.findAll(siteNews);
		request.setAttribute("runNews", findAll);
		request.getRequestDispatcher("/front/index.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");
		Student student = new Student();
		student.setLoginName(loginName);
		student.setLoginPassword(loginPassword);
		List<Student> findAll = studentService.findAll(student);
		if (findAll!=null && findAll.size()==1) {
			student = findAll.get(0);
			request.getSession().setAttribute("flogin", student);
			response.sendRedirect(contextPath+"/f");
		}else{
			request.setAttribute("loginPassword", loginPassword);
			request.setAttribute("msg", "登录失败");
			request.getRequestDispatcher("/front/login.jsp").forward(request, response);
		}
	}
	
	//安全退出
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(contextPath+"/f");
	}
	
	//注册
	private void regit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = new Student();
       //上传到服务器硬盘上，保证重启tomcat不会丢失文件
       //获取上传文件的路径
		String savePath = this.getServletContext().getRealPath("/upload");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
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
                   		student.setId(item.getString("UTF-8"));
						}
					}
					
					if ("loginName".endsWith(name)) {
						student.setLoginName(item.getString("UTF-8"));
						
					}
					
					if ("loginPassword".endsWith(name)) {
						student.setLoginPassword(item.getString("UTF-8"));
						
					}
					
					if ("regitTime".endsWith(name)) {
						student.setRegitTime(new Date());
						
					}
					
					if ("auditType".endsWith(name)) {
						student.setAuditType(item.getString("UTF-8"));
						
					}
					
					if ("stuName".endsWith(name)) {
						student.setStuName(item.getString("UTF-8"));
						
					}
					
					if ("sex".endsWith(name)) {
						student.setSex(item.getString("UTF-8"));
						
					}
					
					if ("schoolRoom".endsWith(name)) {
						student.setSchoolRoom(item.getString("UTF-8"));
						
					}
					
					if ("classRoom".endsWith(name)) {
						student.setClassRoom(item.getString("UTF-8"));
						
					}
					
					if ("phone".endsWith(name)) {
						student.setPhone(item.getString("UTF-8"));
						
					}
					
					if ("pic".endsWith(name)) {
						student.setPic(item.getString("UTF-8"));
						
					}
					
					if ("remark".endsWith(name)) {
						student.setRemark(item.getString("UTF-8"));
						
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
                   String suffix = filename.substring(filename.lastIndexOf("."));
                   filename=String.valueOf(System.currentTimeMillis()+suffix);
                   
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
                   
                   student.setPic(request.getContextPath()+"/upload" + "/" + filename);
               }
           }
           if (student.getId()!=null && !"".equals(student.getId())) {
   			studentService.update(student);
   			request.setAttribute("msg","修改成功");
   			request.getSession().setAttribute("flogin", student);
            request.getRequestDispatcher("/front/userInfo.jsp").forward(request, response);
   		}else{
   			studentService.add(student);
   			request.setAttribute("msg","注册成功");
            request.getRequestDispatcher("/front/regit.jsp").forward(request, response);
   		}
           
       }catch (Exception e) {
           msg= "注册失败！";
           e.printStackTrace();
           request.setAttribute("msg",msg);
           request.getRequestDispatcher("/front/regit.jsp").forward(request, response);
       }
	}
	
}