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
import com.so.team.bean.MoneyManger;
import com.so.team.bean.SiteNews;
import com.so.team.bean.Teams;
import com.so.team.dao.SiteNewsDao;
import com.so.team.service.MoneyMangerService;
import com.so.team.service.SiteNewsService;
import com.so.team.service.TeamsService;
import com.so.team.service.impl.MoneyMangerServiceImpl;
import com.so.team.service.impl.SiteNewsServiceImpl;
import com.so.team.service.impl.TeamsServiceImpl;
import com.so.utils.CurrentUserUtils;
import com.so.utils.Page;
import com.so.utils.PropertiesUtil;
import com.so.utils.StringUtils;

@WebServlet("/siteNews")
public class SiteNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	SiteNewsDao siteNewsDao=new SiteNewsDao();
	SiteNewsService siteNewsService = new SiteNewsServiceImpl();
	TeamsService teamsService = new TeamsServiceImpl();
	MoneyMangerService moneyMangerService = new MoneyMangerServiceImpl();
	
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
		}else if ("main".equals(method)) {
			main(request, response);
		}
		
	}
	
	/**
	 * 跳转到主页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查出最新的6条活动内容 2是活动
		SiteNews siteNews = new SiteNews();
		siteNews.setIsAudit("1");
		siteNews.setNewsType("2");
		Integer acNum = siteNewsService.findAll(siteNews).size();
		request.setAttribute("acNum", acNum);//返回活动数
		Page<SiteNews> page = new Page<SiteNews>();
		page.setPageSize(10);
		Page<SiteNews> actives = siteNewsService.page(siteNews, page);
		request.setAttribute("actives", actives.getList());
		//查出最新的6条站内新闻 1是新闻
		siteNews.setNewsType("1");
		Page<SiteNews> pageNews = new Page<SiteNews>();
		page.setPageSize(10);
		Page<SiteNews> news = siteNewsService.page(siteNews, pageNews);
		request.setAttribute("news", news.getList());
		Teams teams = new Teams();
		User currentUser = CurrentUserUtils.getCurrentUser(request);
		String roleFlag = currentUser.getRole2().getRoleFlag();
		if ("leader".equals(roleFlag)) {
			teams.setBuildStu(CurrentUserUtils.getCurrentUser(request).getId());
		}
		int size = teamsService.findAll(teams).size();
		request.setAttribute("Tsize", size);
		
		//统计财务情况
		SiteNews query = new SiteNews();
		query.setIsAudit("1");
		query.setNewsType("2");//活动
		
		if ("leader".equals(roleFlag)) {
			
			query.setCreateUser(currentUser.getUsername());
			
		}
		List<SiteNews> findAll = siteNewsService.findAll(query);//查出负责的所有活动
		MoneyManger moneyManger = new MoneyManger();
		if ("leader".equals(roleFlag)) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(" and a.act_id in (");
			for (SiteNews siteNews2 : findAll) {
				stringBuffer.append("'"+siteNews2.getId()+"',");
			}
			stringBuffer.append(" '1')");
			moneyManger.setSqlStr(stringBuffer.toString());
		}
		List<MoneyManger> moneys = moneyMangerService.findAll(moneyManger);
		double zhichu = 0.0;
		double shouru = 0.0;
		for (MoneyManger moneyManger2 : moneys) {
			if ("1".equals(moneyManger2.getType())) {
				zhichu += moneyManger2.getMoney();
			}else {
				shouru += moneyManger2.getMoney();
			}
		}
		request.setAttribute("zhichu", zhichu);
		request.setAttribute("shouru", shouru);
		
		request.getRequestDispatcher("/mui/welcome.jsp").forward(request, response);
	}

	//添加
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String newsType = request.getParameter("newsType");
		String title = request.getParameter("title");
		String pic = request.getParameter("pic");
		String shortContent = request.getParameter("shortContent");
		String content = request.getParameter("content");
		String createUser = request.getParameter("createUser");
		String createTime = request.getParameter("createTime");
		String isRun = request.getParameter("isRun");
		String belonTeam = request.getParameter("belonTeam");
		String isAudit = request.getParameter("isAudit");
		SiteNews siteNews = new SiteNews();
		siteNews.setNewsType(newsType);
		siteNews.setTitle(title);
		siteNews.setPic(pic);
		siteNews.setShortContent(shortContent);
		siteNews.setContent(content);
		siteNews.setCreateUser(createUser);
		siteNews.setIsRun(isRun);
		siteNews.setBelonTeam(belonTeam);
		siteNews.setIsAudit(isAudit);
		siteNewsService.add(siteNews);
		response.sendRedirect(contextPath+"/siteNews?method=list");
	}
	
	//添加保存
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SiteNews siteNews = new SiteNews();
		 //这是上传到tomcat下，文件容易丢失
		/* //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全*/
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
                    		siteNews.setId(item.getString("UTF-8"));
						}
					}
					
					if ("newsType".endsWith(name)) {
						siteNews.setNewsType(item.getString("UTF-8"));
						
					}
					
					if ("title".endsWith(name)) {
						siteNews.setTitle(item.getString("UTF-8"));
						
					}
					
					if ("pic".endsWith(name)) {
						siteNews.setPic(item.getString("UTF-8"));
						
					}
					
					if ("shortContent".endsWith(name)) {
						siteNews.setShortContent(item.getString("UTF-8"));
						
					}
					
					if ("content".endsWith(name)) {
						siteNews.setContent(item.getString("UTF-8"));
						
					}
					
					if ("createUser".endsWith(name)) {
						siteNews.setCreateUser(item.getString("UTF-8"));
						
					}
					
					if ("createTime".endsWith(name)) {
						if (StringUtils.isNotEmpty(item.getString("UTF-8"))) {
							siteNews.setCreateTime(Timestamp.valueOf(item.getString("UTF-8")));
						}else {
							siteNews.setCreateTime(new Date());
						}
						
					}
					
					if ("isRun".endsWith(name)) {
						siteNews.setIsRun(item.getString("UTF-8"));
						
					}
					
					if ("belonTeam".endsWith(name)) {
						siteNews.setBelonTeam(item.getString("UTF-8"));
						
					}
					
					if ("isAudit".endsWith(name)) {
						siteNews.setIsAudit(item.getString("UTF-8"));
						
					}
					
					if ("acStartTime".endsWith(name)) {
						if (StringUtils.isNotEmpty(item.getString("UTF-8"))) {
							siteNews.setAcStartTime(Timestamp.valueOf(item.getString("UTF-8")));
						}else {
							siteNews.setAcStartTime(new Date());
						}
						
					}
					
					if ("acEndTime".endsWith(name)) {
						if (StringUtils.isNotEmpty(item.getString("UTF-8"))) {
							siteNews.setAcEndTime(Timestamp.valueOf(item.getString("UTF-8")));
						}else {
							siteNews.setAcEndTime(new Date());
						}
						
					}
					
					if ("isCanApply".endsWith(name)) {
						siteNews.setIsCanApply(item.getString("UTF-8"));
						
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
                    
                    siteNews.setPic(request.getContextPath()+"/upload" + "/" + filename);
                }
            }
            if (siteNews.getId()!=null && !"".equals(siteNews.getId())) {
    			siteNewsService.update(siteNews);
    		}else{
    			String username = CurrentUserUtils.getCurrentUser(request).getUsername();
    			siteNews.setCreateUser(username);
    			siteNewsService.add(siteNews);
    		}
    		response.sendRedirect(contextPath+"/siteNews?method=list&newsType="+siteNews.getNewsType());
        }catch (Exception e) {
            msg= "文件上传失败！";
            e.printStackTrace();
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/views/team/siteNewsForm.jsp").forward(request, response);
        }
	}
	
	//删除
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String newsType = siteNewsService.getById(id).getNewsType();
		siteNewsService.delete(id);
		response.sendRedirect(contextPath+"/siteNews?method=list&newsType="+newsType);
	}
	
	//修改
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String newsType = request.getParameter("newsType");
		String title = request.getParameter("title");
		String pic = request.getParameter("pic");
		String shortContent = request.getParameter("shortContent");
		String content = request.getParameter("content");
		String createUser = request.getParameter("createUser");
		String createTime = request.getParameter("createTime");
		String isRun = request.getParameter("isRun");
		String belonTeam = request.getParameter("belonTeam");
		String isAudit = request.getParameter("isAudit");
		SiteNews siteNews = new SiteNews();
		siteNews.setId(id);
		siteNews.setNewsType(newsType);
		siteNews.setTitle(title);
		siteNews.setPic(pic);
		siteNews.setShortContent(shortContent);
		siteNews.setContent(content);
		siteNews.setCreateUser(createUser);
		siteNews.setIsRun(isRun);
		siteNews.setBelonTeam(belonTeam);
		siteNews.setIsAudit(isAudit);
		siteNewsService.update(siteNews);
		request.setAttribute("siteNews", siteNews);
		request.getRequestDispatcher("/views/team/siteNewsForm.jsp").forward(request, response);
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		SiteNews siteNews = new SiteNews();
		//分页有关
		Page<SiteNews> page = new Page<SiteNews>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String newsType = request.getParameter("newsType");
		if (newsType != null && newsType != "") {
			siteNews.setNewsType(newsType);
			request.setAttribute("newsType", newsType);
		}
		String title = request.getParameter("title");
		if (title != null && title != "") {
			siteNews.setTitle(title);
			request.setAttribute("title", title);
		}
		String createUser = request.getParameter("createUser");
		if (createUser != null && createUser != "") {
			siteNews.setCreateUser(createUser);
			request.setAttribute("createUser", createUser);
		}
		String isRun = request.getParameter("isRun");
		if (isRun != null && isRun != "") {
			siteNews.setIsRun(isRun);
			request.setAttribute("isRun", isRun);
		}
		String belonTeam = request.getParameter("belonTeam");
		if (belonTeam != null && belonTeam != "") {
			siteNews.setBelonTeam(belonTeam);
			request.setAttribute("belonTeam", belonTeam);
		}
		String isAudit = request.getParameter("isAudit");
		if (isAudit != null && isAudit != "") {
			siteNews.setIsAudit(isAudit);
			request.setAttribute("isAudit", isAudit);
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
			stringBuffer.append(" and a.belon_team in (");
			for (Teams teams2 : findAll) {
				stringBuffer.append("'"+teams2.getId()+"',");
			}
			stringBuffer.append(" '1')");
			siteNews.setSqlStr(stringBuffer.toString());
		}else{
			request.setAttribute("teamList", teamsService.findAll(new Teams()));
		}
		
		page = siteNewsService.page(siteNews, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/team/siteNewsList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		SiteNews siteNews = new SiteNews();
		String newsType = request.getParameter("newsType");
		if (newsType!=null && newsType !="") {
			siteNews.setNewsType(newsType);
		}
		if (id!=null && id!="") {
			siteNews = siteNewsService.getById(id);
		}
		request.setAttribute("siteNews", siteNews);
		//根据登录用户查出所有的社团
		User currentUser = CurrentUserUtils.getCurrentUser(request);
		String roleFlag = currentUser.getRole2().getRoleFlag();
		if ("leader".equals(roleFlag)) {
			//如果是社长，查出自己负责的所以社团
			Teams teams = new Teams();
			teams.setBuildStu(currentUser.getId());
			request.setAttribute("teamList", teamsService.findAll(teams));
		}else{
			request.setAttribute("teamList", teamsService.findAll(new Teams()));
		}
		request.getRequestDispatcher("/views/team/siteNewsForm.jsp").forward(request, response);
	}
	
}