package com.so.system.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadController
 */
@WebServlet(name = "download", urlPatterns = { "/download" })
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 字符编码

	private final String ENCODING = "GB2312";

	// 内容类型

	private final String CONTENT_TYPE = "text/html;charset=gb2312";

	// 要下载的文件存放的路径

	private String downloadfiledir = "d:\\temp\\";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String realPath = request.getSession().getServletContext().getRealPath("");
		// 设置request对象的字符编码

		request.setCharacterEncoding(ENCODING);

		// 从request中取出要下载文件的名字

		String filename = request.getParameter("filename");

		if (filename == null || filename.trim().equals("")) {

			// 设置response对象的ContentType

			response.setContentType(CONTENT_TYPE);

			// 输出错误信息

			PrintWriter out = response.getWriter();

			out.println("<font color=red>输入的文件名无效！</font>");

			out.close();

		} else {

			// 下载文件的完整路径名

			String fullfilename = realPath + filename;

			System.out.println("下载文件：" + fullfilename);

			// 根据文件的类型设置response对象的ContentType

			String contentType = getServletContext().getMimeType(fullfilename);

			if (contentType == null)

				contentType = "application/octet-stream";

			response.setContentType(contentType);

			// 设置response的头信息

			response.setHeader("Content-disposition", "attachment;filename=\"" + filename + "\"");

			InputStream is = null;

			OutputStream os = null;

			try {

				is = new BufferedInputStream(new FileInputStream(fullfilename));

				// 定义输出字节流

				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				// 定义response的输出流

				os = new BufferedOutputStream(response.getOutputStream());

				// 定义buffer

				byte[] buffer = new byte[4 * 1024]; // 4k Buffer

				int read = 0;

				// 从文件中读入数据并写到输出字节流中

				while ((read = is.read(buffer)) != -1) {

					baos.write(buffer, 0, read);

				}

				// 将输出字节流写到response的输出流中

				os.write(baos.toByteArray());

			} catch (IOException e) {

				e.printStackTrace();

			} finally {
				// 关闭输出字节流和response输出流
				os.close();
				is.close();

			}
		}
	}

}
