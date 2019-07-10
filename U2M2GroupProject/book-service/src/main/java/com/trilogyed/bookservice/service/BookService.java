package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.exception.NotFoundException;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
import com.trilogyed.bookservice.viewModel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
@RefreshScope
public class BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private final NoteServiceClient client;

    @Autowired
    public BookService(BookDao bookDao, NoteServiceClient client, RabbitTemplate rabbitTemplate) {

        this.bookDao = bookDao;
        this.client = client;
        this.rabbitTemplate = rabbitTemplate;

    }

    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.books.controller";


    private BookViewModel buildBookViewModel(Book book) {
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setNoteList(client.getAllNotes());

        return bookViewModel;
    }

    @Transactional
    public BookViewModel addBook(BookViewModel bookViewModel) {
        Book book = new Book();
        BookViewModel bookToBeReturned;
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        book = bookDao.createBook(book);
        bookViewModel.setBookId(book.getBookId());
        for (Note giveNote : bookViewModel.getNoteList()) {
            giveNote.setBookId(bookViewModel.getBookId());
            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, giveNote);
            System.out.println("Message Sent");
        }
        bookToBeReturned = this.findBookById(bookViewModel.getBookId());
        return bookToBeReturned;
    }

    @Transactional
    public BookViewModel updateBook(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setBookId(bookViewModel.getBookId());
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        for (Note giveNote : bookViewModel.getNoteList()) {
            giveNote.setBookId(bookViewModel.getBookId());
            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, giveNote);
            System.out.println("Message Sent");
        }
        bookViewModel.setNoteList(client.getAllNotes());

        bookDao.updateBook(book);
        return bookViewModel;
    }

    @Transactional
    public void removeBook(int id) {
        bookDao.deleteBook(id);
    }

    public List<BookViewModel> findAllBooks() {
        List<Book> bookList = bookDao.getAllBooks();
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        for (Book book : bookList) {
            bookViewModelList.add(buildBookViewModel(book));
        }
        return bookViewModelList;
    }

    public BookViewModel findBookById(int id) {

        BookViewModel bookViewModel = new BookViewModel();
        Book book = bookDao.getBookById(id);
        if (book == null) {
            throw new IllegalArgumentException("Book couldn't be fount for given Id " + id);
        } else {
            bookViewModel.setBookId(book.getBookId());
            bookViewModel.setTitle(book.getTitle());
            bookViewModel.setAuthor(book.getAuthor());
            bookViewModel.setNoteList(client.getNotesByBookID(id));
        }
        return bookViewModel;
    }


}
