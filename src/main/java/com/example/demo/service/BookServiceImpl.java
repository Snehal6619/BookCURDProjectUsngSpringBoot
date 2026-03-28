package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepo bookRepo;
	
	@Override
	public boolean isAddBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepo.isAddBook(book);
	}

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		return bookRepo.getAllBook();
	}

	@Override
	public List<Book> getBook(String title) {
		// TODO Auto-generated method stub
		return bookRepo.getBook(title);
	}

	@Override
	public Optional<Book> getBook(int bookId) {
		// TODO Auto-generated method stub
		return bookRepo.getBook(bookId);
	}

	@Override
	public boolean updateBook(Book book, int bookId) {
		// TODO Auto-generated method stub
		return bookRepo.updateBook(book, bookId);
	}

	@Override
	public Optional<Book> partialUpdateBook(String title, int bookId) {
		// TODO Auto-generated method stub
		return bookRepo.partialUpdateBook(title, bookId) ;
	}

	@Override
	public boolean deleteBookById(int id) {
		// TODO Auto-generated method stub
		return bookRepo.deleteBookById(id);
	}

}
