package com.so.utils;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.so.system.bean.User;

/**
 * 这是过滤器,用于设置请求与响应的字符集编码
* @ClassName: SerFilter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author ShiLing-DENG
* @date 2016年11月5日 下午6:14:54
 */
@WebFilter("/*")
public class SerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding("utf-8");
		HttpServletResponse res = (HttpServletResponse) response;
		//res.setContentType("charset=utf-8");
		res.setCharacterEncoding("utf-8");
		String parameter = request.getParameter("method");
		//如果不是登录和安全退出方法 进行过滤操作
		if (parameter!=null && parameter !="" && !"regit".equals(parameter) && !"login".equals(parameter)&& !"logout".equals(parameter)) {
			User login = CurrentUserUtils.getCurrentUser(req);
			if (login==null) {
				String contextPath = req.getServletContext().getContextPath();
				res.getWriter().write("<script>alert('yonghushenfenguoqi！');window.parent.location.href='"+contextPath+"/mui/login.jsp'</script>");
			}else{
				chain.doFilter(req, res);
			}
		}else{
			chain.doFilter(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
