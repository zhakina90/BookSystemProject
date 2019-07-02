package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;

import java.util.List;

public interface BookDao {
    public Book createBook(Book book);
    public Book getBookById(int id);
    List<Book> getAllBooks();
    Book updateBook(Book book);
    void deleteBook(int id);
}
