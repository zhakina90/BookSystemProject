package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
import com.trilogyed.bookservice.viewModel.BookViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class BookServiceTest {
    BookDao bookDao;
    BookService bookService;
    NoteServiceClient client;
    RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() throws Exception {
        setUpBookDaoMock();
        bookService = new BookService( bookDao, client, rabbitTemplate);
    }



    public void setUpBookDaoMock(){
        bookDao = mock(BookDaoJdbcTemplateImpl.class);
        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Anything");
        book.setAuthor("Someone");

        Book book1 = new Book();
        book1.setTitle("Anything");
        book1.setAuthor("Someone");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        doReturn(book).when(bookDao).createBook(book1);
        doReturn(book).when(bookDao).getBookById(book.getBookId());
        doReturn(bookList).when(bookDao).getAllBooks();
    }
//
//    @Test
//    public void addGetBook() {
//        BookViewModel bookViewModel = new BookViewModel();
//        bookViewModel.setTitle("Anything");
//        bookViewModel.setAuthor("Someone");
//        bookViewModel.setBookId(1);
//        bookViewModel.setNote("This is our first note");
//
//        bookViewModel = bookService.addBook(bookViewModel);
//
//        BookViewModel bookViewModel1 = bookService.findBookById(bookViewModel.getBookId());
//
//        assertEquals(bookViewModel, bookViewModel1);
//
//    }


    @Test
    public void findAllBooks() {
        List<BookViewModel> bookViewModelList = bookService.findAllBooks();
        assertEquals(bookViewModelList.size(), 1);
    }

}