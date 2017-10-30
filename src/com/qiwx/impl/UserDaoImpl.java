package com.qiwx.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qiwx.Book;
import com.qiwx.DBUtils;
import com.qiwx.ResponseJson;
import com.qiwx.User;
import com.qiwx.dao.UserDao;

/**
 * @Author qiwx
 * @time 2017年10月30日 上午10:10:37
 * @Des
 **/
public class UserDaoImpl implements UserDao {
	@Override
	public User login(String name, String passWord) {
		String sql = "select * from user where name=? and password=?";
		DBUtils dbUtils = new DBUtils();
		Connection connection = dbUtils.getConnection();
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, name);
			pStatement.setString(2, passWord);
			ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				User user = new User();
				user.setId(id);
				// 此处不需要取数据库里的用户名和密码
				user.setUserName(name);
				user.setPassWord(passWord);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	@Override
	public List<Book> addBook(Book book) {
		String sql = "insert into book values(?,?,?)";
		DBUtils dbUtils = new DBUtils();
		Connection connection = dbUtils.getConnection();
		List<Book> dataList = new ArrayList<>();
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, book.getBookId());
			pStatement.setString(2, book.getBookName());
			pStatement.setDouble(3, book.getBookPrice());
		    pStatement.executeUpdate();
		  
			ResultSet rs = pStatement.executeQuery("select * from book");
			
			while (rs.next()) {
				Book rec = new Book();
				rec.setBookId(rs.getInt(1));
				rec.setBookName(rs.getString(2));
				rec.setBookPrice(rs.getDouble(3));
				dataList.add(rec);
			}

			return dataList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeDB(connection);
		}
		return dataList;

	}
	@Override
	public List<Book> deleteBook(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
