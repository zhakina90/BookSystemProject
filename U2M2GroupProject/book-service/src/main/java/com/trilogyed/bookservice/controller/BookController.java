package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.exception.NotFoundException;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.service.BookService;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
<<<<<<< HEAD
=======
import com.trilogyed.bookservice.util.messages.NoteListEntry;
>>>>>>> 5737fc2a0d093140ff28dd8fcabf6c3e03462a49
import com.trilogyed.bookservice.viewModel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope //Needed in this controller?
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    private final NoteServiceClient client;

<<<<<<< HEAD
    BookController(NoteServiceClient client){
        this.client = client;
    }
=======
    @Autowired
    RabbitTemplate rabbitTemplate;


    BookController(RabbitTemplate rabbitTemplate, NoteServiceClient client){
        this.rabbitTemplate = rabbitTemplate;
        this.client = client;

    }
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.books.controller";

>>>>>>> 5737fc2a0d093140ff28dd8fcabf6c3e03462a49

//    @RequestMapping(value = "/note", method = RequestMethod.GET)
//    public String note(){
//        return client.getNote(6);
//    }


    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookViewModel createBook(@RequestBody @Valid BookViewModel bookViewModel){

        bookService.addBook(bookViewModel);
        NoteListEntry msg = new NoteListEntry();
        msg.setBookId(bookViewModel.getBookId());
        msg.setNote(bookViewModel.getNote());
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);

        return bookViewModel;

    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public BookViewModel getBook(@PathVariable ("id")int bookId){
        BookViewModel bookViewModel = bookService.findBookById(bookId);
        if(bookViewModel == null){
            throw new IllegalArgumentException("Book could not be found for book_id: " + bookId);
        }
        return bookViewModel;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookViewModel> getAllBooks(){
        return bookService.findAllBooks();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public BookViewModel updateBook(@PathVariable("id") int bookId, @RequestBody BookViewModel bookViewModel){
        if(bookViewModel.getBookId() == 0)
            bookViewModel.setBookId(bookId);
        if(bookId != bookViewModel.getBookId()){
            throw new IllegalArgumentException("Book ID: " + bookId + " must match the ID in the Book Object.");
        }
        return bookService.updateBook(bookViewModel);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") int bookId){
        if(bookService.findBookById(bookId) == null){
            throw new IllegalArgumentException("Book ID: " + bookId + " must match the ID in the Book Object. ");
        }else {
            bookService.removeBook(bookId);
        }
    }
}
