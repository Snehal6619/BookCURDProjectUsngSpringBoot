package com.example.demo.repo;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository("bookRepo")
public class BookRepoImpl implements BookRepo {

	@Autowired
	JdbcTemplate jdbc;
	
	List<Book> bookList;
	@Override
	public boolean isAddBook(Book book) {
		
		int value=jdbc.update("insert into book values ('0',?,?,?,?)",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getAuthor());
				ps.setInt(3, book.getPrice());
				ps.setString(4, book.getCategory());
				
			}
			
		});
		
		return value>0?true:false;
	}
	@Override
	public List<Book> getAllBook() {
		
		bookList=jdbc.query("select * from book", new RowMapper<Book>() {

			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book b=new Book();
				 b.setId(rs.getInt(1));          // 👈 IMPORTANT
			        b.setTitle(rs.getString(2));
			        b.setAuthor(rs.getString(3));
			        b.setPrice(rs.getInt(4));
			        b.setCategory(rs.getString(5));
				return b;
			}
			
		});
		
		return bookList;
	}
	@Override
	public List<Book> getBook(String title) {
		
		bookList=jdbc.query("select * from book where title like %"+title+"%", new RowMapper<Book>() {

			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book b=new Book();
				 b.setId(rs.getInt(1));          // 👈 IMPORTANT
			        b.setTitle(rs.getString(2));
			        b.setAuthor(rs.getString(3));
			        b.setPrice(rs.getInt(4));
			        b.setCategory(rs.getString(5));
				return b;
			}
			
		});
		
		return bookList;
	}
	
	@Override
	public Optional<Book> getBook(int bookId) {
		
		List<Book>list=jdbc.query("select * from book where bookid=?", new Object[] {bookId},new RowMapper<Book>(){

			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Book b=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
				return b;
			}
			
		});
		return list.size() >0 ? Optional.ofNullable(list.get(0)) :Optional.empty();
		
	}
	
	@Override
	public boolean updateBook(Book book, int bookId) {

	    int value = jdbc.update(
	        "update book set title=?, author=?, price=?, category=? where bookid=?",
	        book.getTitle(),
	        book.getAuthor(),
	        book.getPrice(),
	        book.getCategory(),
	        bookId
	    );

	    return value > 0;
	}
	
	@Override
	public Optional<Book> partialUpdateBook(String title, int bookId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean deleteBookById(int id) {

	    int value = jdbc.update(
	        "delete from book where bookid=?",
	        id
	    );

	    return value > 0;
	}

	
}
