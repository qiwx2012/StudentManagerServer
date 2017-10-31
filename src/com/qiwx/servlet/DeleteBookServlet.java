package com.qiwx.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.qiwx.Book;
import com.qiwx.ResponseJson;
import com.qiwx.dao.UserDao;
import com.qiwx.impl.UserDaoImpl;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteBookServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		ResponseJson rjJson = new ResponseJson<List<Book>>();
		String bookId = request.getParameter("bookId");

		if (bookId != null) {
			UserDao uDao = new UserDaoImpl();
			List<Book> dataList = uDao.deleteBook(Integer.valueOf(bookId));
			if (dataList != null && dataList.size() > 0) {
				rjJson.setMessage("成功");
				rjJson.setStatus(200);
				rjJson.setData(dataList);
			} else {
				rjJson.setMessage("失败");
				rjJson.setStatus(-100);
				rjJson.setData(null);
			}
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
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
