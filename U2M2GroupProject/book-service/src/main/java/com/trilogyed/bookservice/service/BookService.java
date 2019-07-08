package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
import com.trilogyed.bookservice.util.messages.NoteListEntry;
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

//    @Autowired
//    RabbitTemplate rabbitTemplate;

    @Autowired
private final NoteServiceClient client;

    @Autowired
    public BookService( BookDao bookDao, NoteServiceClient client){

        this.bookDao = bookDao;
        this.client = client;

    }







    private BookViewModel buildBookViewModel(Book book){
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setNote(client.getNote(book.getBookId()));

        return bookViewModel;
    }

    @Transactional
    public BookViewModel addBook(BookViewModel bookViewModel){
        Book book = new Book();
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        book = bookDao.createBook(book);
        bookViewModel.setBookId(book.getBookId());

//        NoteListEntry msg = new NoteListEntry(bookViewModel.getTitle()  , bookViewModel.getAuthor(), bookViewModel.getNote());
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
//        System.out.println("Message Sent");

        //the information  that we added
        bookViewModel.setNote(client.getNote(book.getBookId()));
        return bookViewModel;
    }



    @Transactional
    public BookViewModel updateBook(BookViewModel bookViewModel){
        Book book = new Book();
        book.setBookId(bookViewModel.getBookId());
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        bookViewModel.setNote(client.getNote(book.getBookId()));
        bookDao.updateBook(book);
        //note

        return bookViewModel;
    }

    @Transactional
    public void removeBook(int id){
        bookDao.deleteBook(id);
    }

    public List<BookViewModel> findAllBooks(){
        List<Book> bookList = bookDao.getAllBooks();
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        for(Book book : bookList){
            bookViewModelList.add(buildBookViewModel(book));
        }
        return bookViewModelList;
    }

    public BookViewModel findBookById(int id){

        Book book = bookDao.getBookById(id);
        BookViewModel bookViewModel= new BookViewModel();
        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setNote(client.getNote(id));
        return bookViewModel;
//        if(book == null){
//            return null;
//        }else{
//            BookViewModel myBook = buildBookViewModel(book);
//            myBook.setNote(client.getNote(id));
//            return myBook;
//        }
    }


}
