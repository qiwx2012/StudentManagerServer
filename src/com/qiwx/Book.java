package com.qiwx;

import java.io.Serializable;

/**
 * @Author qiwx
 * @time 2017年10月30日 下午5:16:59
 * @Des
 **/
public class Book implements Serializable {
	int bookId;
	String bookName;
	double bookPrice;
	String bookThumb;
	

	public String getBookThumb() {
		return bookThumb;
	}

	public void setBookThumb(String bookThumb) {
		this.bookThumb = bookThumb;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

}
