package com.qiwx.dao;

import java.util.List;

import com.qiwx.Book;
import com.qiwx.ResponseJson;
import com.qiwx.User;


/**
 * @Author qiwx
 * @time 2017年10月30日 上午9:55:35
 * @Des
 **/
public interface UserDao {

	// 登陆
	public User login(String name, String passWord);
	// 添加纪录
	public List<Book> addBook(Book book);
	// 删除记录
	public List<Book> deleteBook(int bookId);
	//查询操作
	public  List<Book>selectBook();

}
