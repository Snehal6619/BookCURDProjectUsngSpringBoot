package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Book;


public interface BookService {

	public boolean isAddBook(Book book);
	public List<Book>getAllBook();
	public List<Book> getBook(String title);
	public Optional<Book> getBook(int bookId);
	public boolean updateBook(Book book,int bookId);
	public Optional<Book> partialUpdateBook(String title,int bookId);
	public boolean deleteBookById(int id);
}
