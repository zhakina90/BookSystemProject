package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
import com.trilogyed.bookservice.viewModel.BookViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @Mock
    BookDao bookDao;

    BookService bookService;
    @Mock
    NoteServiceClient client;
    @Mock
    RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() throws Exception {
        setUpBookDaoMock();
        bookService = new BookService(bookDao, client, rabbitTemplate);
    }

    public void setUpBookDaoMock() {
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note(1, "New Note"));
        noteList.add(new Note(1, "Another note"));

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
        doReturn(noteList).when(client).getNotesByBookID(1);
    }

    @Test
    public void addGetBook() {
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note(1, "New Note"));
        noteList.add(new Note(1, "Another note"));

        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setTitle("Anything");
        bookViewModel.setAuthor("Someone");
        bookViewModel.setNoteList(noteList);

        bookViewModel = bookService.addBook(bookViewModel);

        BookViewModel bookViewModel1 = bookService.findBookById(bookViewModel.getBookId());

        assertEquals(bookViewModel, bookViewModel1);

    }


    @Test
    public void findAllBooks() {
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note(1, "New Note"));
        noteList.add(new Note(1, "Another note"));

        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setTitle("Anything");
        bookViewModel.setAuthor("Someone");
        bookViewModel.setNoteList(noteList);

        bookViewModel = bookService.addBook(bookViewModel);
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        List<BookViewModel> bookViewModelFromService = bookService.findAllBooks();
        bookViewModelList.add(bookViewModel);
        assertEquals(bookViewModelList.size(), bookViewModelFromService.size());
    }

}