package com.book.restapi.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.book.restapi.dao.BookDao;
import com.book.restapi.data.Book;

@Path("/BookServices")
public class BookService {
	
	BookDao bookDao = new BookDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getAllBooks(){
		return bookDao.getAllBooks();
	}
	
	@GET
	@Path("/books/{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("id") int bookId) throws InternalServerErrorException, Exception{
		return bookDao.getBook(bookId);
	}
	
	@POST
	@Path("/books")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createBook(@FormParam("bookId") Integer bookId,
			@FormParam("bookName") String bookName,
			@FormParam("author") String author,
			@FormParam("price") Double price) throws Exception { //,@Context HttpServletResponse httpServletResponse
		Book book2 = new Book(bookId,bookName,author,price);
		//System.out.println(book2);
		int result  = bookDao.addBook(book2);
		if(result == 1)
			return SUCCESS_RESULT;
		else
			return FAILURE_RESULT;
	}
	
	@PUT
	@Path("/books")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateBook(@FormParam("bookId") Integer bookId,
			@FormParam("bookName") String bookName,
			@FormParam("author") String author,
			@FormParam("price") Double price,
			@Context HttpServletResponse httpServletResponse) {
		Book book2 = new Book(bookId,bookName,author,price);
		//System.out.println(book2);
		int result  = bookDao.updateBook(book2);
		if(result == 1)
			return SUCCESS_RESULT;
		else
			return FAILURE_RESULT;
	}
	
	@DELETE
	@Path("/books/{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	public String deleteBook(@PathParam("id") int bookId) throws InternalServerErrorException, Exception{
		int result = bookDao.deleteBook(bookId);
		if(result == 1)
			return SUCCESS_RESULT;
		else
			return FAILURE_RESULT;
	}

}
