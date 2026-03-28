package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/")
public class BookController {

	@Autowired
	BookService bookService;
	
	Logger logger=LoggerFactory.getLogger(BookController.class);
	
	@PostMapping("/books")
	
	public ResponseEntity<String> createBook(@RequestBody Book book)
	{
		logger.info(""+book);     //variable print karayla debug lihila nhi tr aapan trace lihu shakto  funtion calling hot tevha debug krto
		
		boolean result=bookService.isAddBook(book);
		if(result)
		{
			return new ResponseEntity<String>("Book save successfully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Book not save successfully",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/books")
	public List<Book> getBooks()
	{
		return bookService.getAllBook();
	}
	
	@GetMapping("/books/{bid}")    //http://localhost:8090/api/books/1
	public ResponseEntity<Optional<Book>> getBookById(@PathVariable("bid") int bid)
	{
		Optional<Book> book=bookService.getBook(bid);
		return new ResponseEntity<Optional<Book>>(book,HttpStatus.FOUND);
	}
	
	@PutMapping("/books/{id}")
	public String updateBook(@PathVariable int id, @RequestBody Book book) {

	    boolean result = bookService.updateBook(book, id);

	    if(result) {
	        return "Book updated successfully";
	    } else {
	        return "Book not found";
	    }
	}
	
	@DeleteMapping("/books/{id}")
	public String deleteBook(@PathVariable int id) {

	    boolean result = bookService.deleteBookById(id);

	    if(result) {
	        return "Book deleted successfully";
	    } else {
	        return "Book not found";
	    }
	}
}
