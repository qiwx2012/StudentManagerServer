package com.qiwx.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.qiwx.Book;
import com.qiwx.ResponseJson;
import com.qiwx.User;
import com.qiwx.dao.UserDao;
import com.qiwx.impl.UserDaoImpl;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBookServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		ResponseJson rjJson = new ResponseJson<List<Book>>();
		InputStream in = request.getInputStream();
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[512];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		Book book = new GsonBuilder().serializeNulls().create()
				.fromJson(out.toString(), Book.class);
		if (book != null) {
			UserDao uDao = new UserDaoImpl();
			List<Book> dataList = uDao.addBook(book);
			rjJson.setMessage("成功");
			rjJson.setStatus(200);
			rjJson.setData(dataList);
		} else {
			rjJson.setMessage("失败");
			rjJson.setStatus(-100);
			rjJson.setData(null);
		}
		String json = new GsonBuilder().serializeNulls().create()
				.toJson(rjJson);
		OutputStream os = response.getOutputStream();
		os.write(json.getBytes("UTF-8"));
		os.flush();
		System.out.println("----" + out.toString());
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
