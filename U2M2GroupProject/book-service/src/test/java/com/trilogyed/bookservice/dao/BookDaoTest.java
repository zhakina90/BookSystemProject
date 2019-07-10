package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {
    @Autowired
    BookDao bookDao;

    @Before
    public void setUp() throws Exception {
        List<Book> bookList = bookDao.getAllBooks();
        bookList.stream()
                .forEach(book -> bookDao.deleteBook(book.getBookId()));
    }

    @Test
    public void createGetDeleteBook() {
        Book book = new Book("Hello", "Mr.Hello");
        bookDao.createBook(book);
        Book book1 = bookDao.getBookById(book.getBookId());
        assertEquals(book1, book);

        bookDao.deleteBook(book.getBookId());
        Book book2 = bookDao.getBookById(book.getBookId());
        assertNull(book2);
    }


    @Test
    public void getAllBooks() {
        Book book = new Book("Hello", "Mr.Hello");
        Book book2 = new Book("Bye", "Mrs.Bye");
        bookDao.createBook(book);
        bookDao.createBook(book2);
        List<Book> bookList = bookDao.getAllBooks();
        assertEquals(bookList.size(), 2);


    }

    @Test
    public void updateBook() {
        Book book1 = new Book("Bye", "Mrs.Bye");
        bookDao.createBook(book1);
        book1.setTitle("Random Book");
        book1.setAuthor("Mystery");
        bookDao.updateBook(book1);
        Book book2 = bookDao.getBookById(book1.getBookId());
        assertEquals(book2, book1);

    }


}