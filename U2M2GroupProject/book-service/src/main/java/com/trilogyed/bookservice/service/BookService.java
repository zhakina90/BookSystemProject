package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
import com.trilogyed.bookservice.viewModel.BookViewModel;
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
private final NoteServiceClient client;
BookService(NoteServiceClient client){
    this.client=client;
}
    @Autowired
    public BookService(BookDao bookDao, NoteServiceClient client){
        this.bookDao = bookDao;
        this.client = client;
    }

    private BookViewModel buildBookViewModel(Book book){
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());

        return bookViewModel;
    }

    @Transactional
    public BookViewModel addBook(BookViewModel bookViewModel){
        Book book = new Book();
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        book = bookDao.createBook(book);
        bookViewModel.setBookId(book.getBookId());
        //the information  that we added
//        bookViewModel.setNote(client.getNote());
        return bookViewModel;
    }

    @Transactional
    public BookViewModel updateBook(BookViewModel bookViewModel){
        Book book = new Book();
        book.setBookId(bookViewModel.getBookId());
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
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
        if(book == null){
            return null;
        }else{
            BookViewModel myBook = buildBookViewModel(book);
            System.out.println(client.getNote(id));
            myBook.setNote(client.getNote(id));
            return myBook;
        }
    }


}
