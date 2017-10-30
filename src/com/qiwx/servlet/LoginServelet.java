package com.qiwx.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qiwx.ResponseJson;
import com.qiwx.User;
import com.qiwx.dao.UserDao;
import com.qiwx.impl.UserDaoImpl;

/**
 * Servlet implementation class LoginServelet
 */
@WebServlet("/login")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = null;
	public LoginServelet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		ResponseJson rjJson = new ResponseJson<User>();
		String name = request.getParameter("userName");
		String pwd = request.getParameter("passWord");
		UserDao uDao = new UserDaoImpl();
		user = uDao.login(name, pwd);
		if (user == null) {
			rjJson.setData(null);
			rjJson.setStatus(-100);
			rjJson.setMessage("用户名或密码错误");

		} else {
			rjJson.setData(user);
			rjJson.setStatus(200);
			rjJson.setMessage("登录成功");
		}
		String userJson=new GsonBuilder().serializeNulls().create().toJson(rjJson);
		OutputStream out = response.getOutputStream();
		out.write(userJson.getBytes("UTF-8"));
		out.flush();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
