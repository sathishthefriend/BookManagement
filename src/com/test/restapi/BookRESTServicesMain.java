package com.test.restapi;

import com.book.restapi.dao.BookDao;
import com.book.restapi.data.Book;

public class BookRESTServicesMain {

	public static void main(String[] args) {
		BookDao bookDao = new BookDao();
		try {
			System.out.println(bookDao.getBook(2));
		

		/*Book book = new Book();
		book.setId(5);
		book.setName("The Devil and Miss Prym");
		book.setAuthor("Paulo Coelho ");
		book.setPrice(9.25);

		int result = bookDao.addBook(book);
		System.out.println(result);*/

		System.out.println(bookDao.getAllBooks());
		
		System.out.println(bookDao.getBook(5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
