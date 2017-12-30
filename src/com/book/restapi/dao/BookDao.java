package com.book.restapi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.InternalServerErrorException;

import com.book.restapi.data.Book;

public class BookDao {
	private static List<Book> books = new ArrayList<>();
	
	public BookDao() {			
			if(books.isEmpty())
				loadData();		
	}

	public List<Book> getAllBooks(){
		if(books.isEmpty())
			loadData();
		return books;
	}
	
	public Book getBook(int bookId) throws InternalServerErrorException, Exception {
		Book book = new Book();
		if (!books.isEmpty()) {
			book = books.stream().filter(e -> e.getId() == bookId).findFirst().get();
		} else {
			loadData();
			book = books.stream().filter(e -> e.getId() == bookId).findFirst().get();
		}
		if (null != book)
			return book;
		else
			return new Book();
	}
	
	public int addBook(Book book2) throws Exception{		
		boolean bookExists = false;
		if (null != book2 && null != book2.getId()) {
			if (!books.isEmpty()) {
				for (Book e : books) {
					if (e.getId() == book2.getId()) {
						bookExists = true;						
					}
				}
			}
		} else {
			bookExists = true;
		}
		if (!bookExists) {
			books.add(book2);
			return 1;
		}
		return 0;
	}
	

	public void loadData() {
		//books = new ArrayList<>();
		
		Book book1 = new Book();
		book1.setId(1);
		book1.setName("The Alchemist");
		book1.setAuthor("Paulo Coelho ");
		book1.setPrice(13.48);		
		books.add(book1);
		
		Book book2 = new Book();
		book2.setId(2);
		book2.setName("The Pilgrimage");
		book2.setAuthor("Paulo Coelho ");
		book2.setPrice(10.98);		
		books.add(book2);
		
		Book book3 = new Book();
		book3.setId(3);
		book3.setName("Warrior of the Light: A Manual");
		book3.setAuthor("Paulo Coelho ");
		book3.setPrice(8.44);		
		books.add(book3);
		
		Book book4 = new Book();
		book4.setId(4);
		book4.setName("Veronika Decides to Die: A Novel of Redemption");
		book4.setAuthor("Paulo Coelho ");
		book4.setPrice(9.84);		
		books.add(book4);
	}

	public int updateBook(Book book2) {
		if(null!=book2) {
			for(Book book:books) {
				if(book.getId() == book2.getId()) {
					int bookIndex = books.indexOf(book);
					books.set(bookIndex, book2);
					return 1;
				}/*else {
					books.add(book2);
				}*/
			}			
		}
		return 0;
	}

	public int deleteBook(int bookId) {
			for(Book book:books) {
				if(book.getId() == bookId) {
					int bookIndex = books.indexOf(book);
					books.remove(bookIndex);
					return 1;
				}			
		}
		return 0;
	}

}
