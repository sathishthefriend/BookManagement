package com.test.restapi;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.book.restapi.data.Book;

public class TestBookRESTServices {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/BookManagement/rest/BookServices/books";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";

	public static void main(String[] args) {
		TestBookRESTServices restServices = new TestBookRESTServices();
		try {
		restServices.init();		
		//test get all books Web Service Method
		restServices.testGetAllBooks();
		//test get a book Web Service Method
		//restServices.testGetBook(4);
		//test add a book Web Service Method
		restServices.testAddBook();
		
		//restServices.testGetBook(5);
		
		// Update a book
		restServices.testUpdateBook();
		
		restServices.testGetBook(5);
		
		restServices.testGetAllBooks();
		
		restServices.deleteBook(2);
		
		restServices.testGetAllBooks();
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param bookId
	 */
	private void deleteBook(int bookId) {
		String callResult = client.target(REST_SERVICE_URL)
				.path("{bookId}")
				.resolveTemplate("bookId", bookId)
				.request(MediaType.APPLICATION_JSON)
				.delete(String.class);
		System.out.println("Delete Status: "+callResult);
	}

	/**
	 * UpdateBook
	 */
	private void testUpdateBook() {
		Form form = new Form();
		form.param("bookId", "5");
		form.param("bookName", "The Devil and Miss Prym : Redemption");
		form.param("author", "Paulo Coelho ");
		form.param("price", "15.25");
		String callResult = null;		
		callResult = client.target(REST_SERVICE_URL).request()
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);		
		System.out.println("Update Result: "+callResult);
	}

	/**
	 * AddBook
	 */
	private void testAddBook() {
		Form form = new Form();
		form.param("bookId", "5");
		form.param("bookName", "The Devil and Miss Prym");
		form.param("author", "Paulo Coelho ");
		form.param("price", "9.25");
				
		String callResult = client.target(REST_SERVICE_URL).request()
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);		
		System.out.println("Create/Add Result: "+callResult);
	}

	/**
	 * @param bookId
	 */
	private void testGetBook(int bookId){
		Book book = client.target(REST_SERVICE_URL)
				.path("{bookId}")
				.resolveTemplate("bookId", bookId)
				.request(MediaType.APPLICATION_JSON)
				.get(Book.class);
		System.out.println("book Details of "+bookId);
		if(null!=book && null!=book.getId() && book.getId() == bookId) {
			System.out.println(PASS);
			System.out.println(book.getName());
			System.out.println(book.getAuthor());
			System.out.println(book.getPrice());
		}
		else
			System.out.println(FAIL);
	}

	/**
	 * GetAllBooks
	 */
	private void testGetAllBooks() {
		GenericType<List<Book>> bookList = new GenericType<List<Book>>() {};
		List<Book> books = client.target(REST_SERVICE_URL).request().get(bookList);
		System.out.println(books);
	}

	private void init() {
		client = ClientBuilder.newClient();
	}

}
