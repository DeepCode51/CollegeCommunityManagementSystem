package com.so.system.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.so.system.bean.Menu;
import com.so.system.bean.Role;
import com.so.system.bean.User;
import com.so.system.dao.MenuRoleDao;
import com.so.system.dao.UserDao;
import com.so.system.service.RoleService;
import com.so.system.service.UserService;
import com.so.system.service.impl.RoleServiceImpl;
import com.so.system.service.impl.UserServiceImpl;
import com.so.utils.CurrentUserUtils;
import com.so.utils.MD5;
import com.so.utils.Page;
import com.so.utils.PropertiesUtil;

/**
 * 系统用户控制器servlet
 * @author DEMOCXY-S0
 * @version V1.0
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao userDao=new UserDao();
	UserService userService = new UserServiceImpl();
	RoleService roleService = new RoleServiceImpl();
	MenuRoleDao menuRoleDao = new MenuRoleDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		}else if ("login".equals(method)) {
			login(request, response);
		}else if ("logout".equals(method)) {
			logout(request, response);
		}else if ("repassword".equals(method)) {
			repassword(request, response);
		}else if ("index".equals(method)) {
			index(request, response);
		}
		
	}
	
	/**
	 * 跳转到首页
	 * @param request
	 * @param response
	 */
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getServletContext().getContextPath();
		User user = CurrentUserUtils.getCurrentUser(request);
		if (user==null) {
			response.getWriter().write("<script>alert('用户失效！'),parent.location.href='"+contextPath+"/mui/login.jsp'</script>");
		}
		List<Map<String, Object>> menus = new ArrayList<Map<String, Object>>();
		List<Menu> parentMenus = menuRoleDao.getUserMenus(user.getRole(), "0");
		for (Menu menu : parentMenus) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentMenu", menu);
			List<Menu> childrenMenus = menuRoleDao.getUserMenus(user.getRole(), menu.getId());
			map.put("sonMenus", childrenMenus);
			menus.add(map);
		}
		String projectName = PropertiesUtil.getValue("projectName");
		request.getSession().setAttribute("projectName", projectName);
		request.getSession().setAttribute("indexMenus", menus);
		request.getRequestDispatcher("/mui/index.jsp").forward(request, response);
	}

	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void repassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getServletContext().getContextPath();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		response.setCharacterEncoding("utf-8");
		
		User user = userService.getById(id);
		if (MD5.Encrypt(password).equals(user.getPassword())) {
			if (password1.equals(password2)) {
				user.setPassword(MD5.Encrypt(password2));
				userService.update(user);
				response.getWriter().write("<script>alert('密码修改成功！需要重新登录'),parent.location.href='"+contextPath+"/mui/login.jsp'</script>");
			}else{
				request.setAttribute("msg", "确认密码不一致");
				request.setAttribute("password", password);
				request.setAttribute("password1", password1);
				request.setAttribute("password2", password2);
				request.getRequestDispatcher("/views/system/repassword.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("msg", "旧密码验证不通过");
			request.setAttribute("password", password);
			request.setAttribute("password1", password1);
			request.setAttribute("password2", password2);
			request.getRequestDispatcher("/views/system/repassword.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String contextPath = request.getServletContext().getContextPath();
		request.getSession().invalidate();
		response.setCharacterEncoding("GBK");
		response.getWriter().write("<script>alert('谢谢使用！'),location.href='"+contextPath+"/mui/login.jsp'</script>");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String contextPath = request.getServletContext().getContextPath();
		System.out.println(contextPath);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User login = userDao.login(username, MD5.Encrypt(password));
		if (login!=null) {
			request.getSession().setAttribute("login", login);
			response.sendRedirect(contextPath+"/user?method=index");
		}else{
			request.setAttribute("msg", "登录失败,用户名或密码错误");
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/mui/login.jsp").forward(request, response);
		}
	}

	//添加
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contextPath = request.getServletContext().getContextPath();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String pic = request.getParameter("pic");
		String role = request.getParameter("role");
		String isBolck = request.getParameter("isBolck");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPic(pic);
		user.setRole(role);
		user.setIsBolck(isBolck);
		userService.add(user);
		response.sendRedirect(contextPath+"/user?method=list");
	}
	
	//添加保存
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getServletContext().getContextPath();
		
		User user = new User();
		 //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
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
                    		user.setId(item.getString("UTF-8"));
						}
					}
					if ("username".endsWith(name)) {
						user.setUsername(item.getString("UTF-8"));
					}
					if ("password".endsWith(name)) {
						user.setPassword(item.getString("UTF-8"));
					}
					if ("pic".endsWith(name)) {
						user.setPic(item.getString("UTF-8"));
					}
					if ("role".endsWith(name)) {
						user.setRole(item.getString("UTF-8"));
					}
					if ("isBolck".endsWith(name)) {
						user.setIsBolck(item.getString("UTF-8"));
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
                    
                    user.setPic(request.getContextPath()+"/upload" + "/" + filename);
                }
            }
            if (user.getId()!=null && !"".equals(user.getId())) {
            	user.setPassword(userService.getById(user.getId()).getPassword());
    			userService.update(user);
    		}else{
    			userService.add(user);
    		}
    		response.sendRedirect(contextPath+"/user?method=list");
        }catch (Exception e) {
            msg= "文件上传失败！";
            e.printStackTrace();
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/views/system/userForm.jsp").forward(request, response);
        }
	}
	
	//删除
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getServletContext().getContextPath();
		String id = request.getParameter("id");
		userService.delete(id);
		response.sendRedirect(contextPath+"/user?method=list");
	}
	
	//修改
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String pic = request.getParameter("pic");
		String role = request.getParameter("role");
		String isBolck = request.getParameter("isBolck");
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setPic(pic);
		user.setRole(role);
		user.setIsBolck(isBolck);
		userService.update(user);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/views/system/userForm.jsp").forward(request, response);
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		User user = new User();
		//分页有关
		Page<User> page = new Page<User>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String username = request.getParameter("username");
		if (username != null && username != "") {
			user.setUsername(username);
			request.setAttribute("username", username);
		}
		
		page = userService.page(user, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/system/userList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = new User();
		if (id!=null && id!="") {
			user = userService.getById(id);
		}
		request.setAttribute("user", user);
		List<Role> findAll = roleService.findAll(new Role());
		request.setAttribute("roles", findAll);
		request.getRequestDispatcher("/views/system/userForm.jsp").forward(request, response);
	}
	
}