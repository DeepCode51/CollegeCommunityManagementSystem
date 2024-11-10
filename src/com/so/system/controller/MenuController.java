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
import com.so.system.bean.MenuRole;
import com.so.system.dao.MenuDao;
import com.so.system.dao.MenuRoleDao;
import com.so.system.service.MenuService;
import com.so.system.service.impl.MenuServiceImpl;
import com.so.utils.Page;
import com.so.utils.StringUtils;

@WebServlet("/menu")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	MenuDao menuDao=new MenuDao();
	MenuService menuService = new MenuServiceImpl();
	MenuRoleDao menuRoleDao = new MenuRoleDao();
	
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
		}else if ("setMenuRole".equals(method)) {
			setMenuRole(request, response);
		}else if ("saveMenuRole".equals(method)) {
			saveMenuRole(request, response);
		}
		
	}
	
	//添加
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String href = request.getParameter("href");
		String target = request.getParameter("target");
		String isShow = request.getParameter("isShow");
		String sort = request.getParameter("sort");
		String parentId = request.getParameter("parentId");
		String parentIds = request.getParameter("parentIds");
		String remarks = request.getParameter("remarks");
		Menu menu = new Menu();
		menu.setName(name);
		menu.setHref(href);
		menu.setTarget(target);
		menu.setIsShow(isShow);
		menu.setSort(sort);
		menu.setParentId(parentId);
		menu.setParentIds(parentIds);
		menu.setRemarks(remarks);
		menuService.add(menu);
		response.sendRedirect(contextPath+"/menu?method=list");
	}
	
	//添加保存
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Menu menu = new Menu();
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
                    		menu.setId(item.getString("UTF-8"));
						}
					}
					if ("name".endsWith(name)) {
						menu.setName(item.getString("UTF-8"));
					}
					if ("href".endsWith(name)) {
						menu.setHref(item.getString("UTF-8"));
					}
					if ("target".endsWith(name)) {
						menu.setTarget(item.getString("UTF-8"));
					}
					if ("isShow".endsWith(name)) {
						menu.setIsShow(item.getString("UTF-8"));
					}
					if ("sort".endsWith(name)) {
						menu.setSort(item.getString("UTF-8"));
					}
					if ("parentId".endsWith(name)) {
						menu.setParentId(item.getString("UTF-8"));
					}
					if ("parentIds".endsWith(name)) {
						menu.setParentIds(item.getString("UTF-8"));
					}
					if ("remarks".endsWith(name)) {
						menu.setRemarks(item.getString("UTF-8"));
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
                    
//                    menu.setPic("\\upload" + "\\" + filename);
                }
            }
            if (menu.getId()!=null && !"".equals(menu.getId())) {
    			menuService.update(menu);
    		}else{
    			menuService.add(menu);
    		}
    		response.sendRedirect(contextPath+"/menu?method=list");
        }catch (Exception e) {
            msg= "文件上传失败！";
            e.printStackTrace();
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/views/system/menuForm.jsp").forward(request, response);
        }
	}
	
	//删除
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		menuService.delete(id);
		response.sendRedirect(contextPath+"/menu?method=list");
	}
	
	//修改
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String href = request.getParameter("href");
		String target = request.getParameter("target");
		String isShow = request.getParameter("isShow");
		String sort = request.getParameter("sort");
		String parentId = request.getParameter("parentId");
		String parentIds = request.getParameter("parentIds");
		String remarks = request.getParameter("remarks");
		Menu menu = new Menu();
		menu.setId(id);
		menu.setName(name);
		menu.setHref(href);
		menu.setTarget(target);
		menu.setIsShow(isShow);
		menu.setSort(sort);
		menu.setParentId(parentId);
		menu.setParentIds(parentIds);
		menu.setRemarks(remarks);
		menuService.update(menu);
		request.setAttribute("menu", menu);
		request.getRequestDispatcher("/views/system/menuForm.jsp").forward(request, response);
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		Menu menu = new Menu();
		/*//分页有关
		Page<Menu> page = new Page<Menu>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String name = request.getParameter("name");
		if (name != null && name != "") {
			menu.setName(name);
			request.setAttribute("name", name);
		}
		String isShow = request.getParameter("isShow");
		if (isShow != null && isShow != "") {
			menu.setIsShow(isShow);
			request.setAttribute("isShow", isShow);
		}
		
		page = menuService.page(menu, page);
		request.setAttribute("page", page);*/
		
		List<Menu> list = new ArrayList<Menu>();
		List<Menu> list1 = menuService.findAll(menu);
		sortList(list, list1, "0", true);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/system/menuList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String parentId = request.getParameter("parentId");
		Menu menu = new Menu();
		if (StringUtils.isNotEmpty(parentId)) {
			menu.setParentId(parentId);
		}
		if (id!=null && id!="") {
			menu = menuService.getById(id);
		}
		request.setAttribute("menu", menu);
		List<Menu> parentMenus = menuDao.getParentMenu("0");
		request.setAttribute("parentMenus", parentMenus);
		request.getRequestDispatcher("/views/system/menuForm.jsp").forward(request, response);
	}
	
	/**
	 * 跳转到设置权限页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void setMenuRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleId = request.getParameter("roleId");
		request.setAttribute("roleId", roleId);
		//获取角色的所以权限菜单ID
		List<String> roleMenus = menuRoleDao.getByRoleId(roleId);
		//定义集合接收菜单数据
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//获取所有的父级菜单
		List<Menu> parentMenus = menuDao.getParentMenu("0");
		for (Menu menu : parentMenus) {
			if (roleMenus.contains(menu.getId())) {
				menu.setIsCheck("1");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentMenu", menu);
			List<Menu> parentMenu = menuDao.getParentMenu(menu.getId());
			List<Menu> menus = new ArrayList<Menu>();
			for (Menu menu2 : parentMenu) {
				if (roleMenus.contains(menu2.getId())) {
					menu2.setIsCheck("1");
				}
				menus.add(menu2);
			}
			
			map.put("menus", menus);
			list.add(map);
		}
		request.setAttribute("list", list);
		String type = request.getParameter("type");
		if (type!=null && "success".equals(type)) {
			request.setAttribute("msg", "更新权限成功！");
		}
		request.getRequestDispatcher("/views/system/setMenuRole.jsp").forward(request, response);
	}
	
	/**
	 * 更新保存权限设置
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void saveMenuRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleId = request.getParameter("roleId");
		request.setAttribute("roleId", roleId);
		menuRoleDao.delete(roleId);
		MenuRole menuRole = new MenuRole();
		menuRole.setRoleId(roleId);
		String[] parameterValues = request.getParameterValues("menuIds");
		if (parameterValues!=null) {
			for (String string : parameterValues) {
				menuRole.setMenuId(string);
				menuRoleDao.add(menuRole);
			}
		}
		response.sendRedirect(contextPath+"/menu?method=setMenuRole&type=success&roleId="+roleId);
	}
	
	
	public static void sortList(List<Menu> list, List<Menu> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Menu e = sourcelist.get(i);
			if (e.getParentId()!=null && e.getParentId()!=null
					&& e.getParentId().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Menu child = sourcelist.get(j);
						if (child.getParentId()!=null && child.getParentId()!=null
								&& child.getParentId().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}
	
}