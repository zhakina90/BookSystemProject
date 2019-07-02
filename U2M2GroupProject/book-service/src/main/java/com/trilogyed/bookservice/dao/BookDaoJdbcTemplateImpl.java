package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_BOOK_SQL =
            "insert into book(title, author) values(?,?)";
    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";
    private static final String SELECT_ALL_BOOK_SQL =
            "select * from book";
    private static final String UPDATE_BOOK_SQL =
            "update book set title=?, author=? where book_id =?";
    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id=?";
    @Autowired
    public BookDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
private Book mapRowToBook(ResultSet resultSet, int rowNum)throws SQLException {
     Book book= new Book();
     book.setBookId(resultSet.getInt("book_id"));
     book.setTitle(resultSet.getString("title"));
     book.setAuthor(resultSet.getString("author"));
     return book;
}

    @Override
    @Transactional
    public Book createBook(Book book) {
        jdbcTemplate.update(INSERT_BOOK_SQL,
                book.getTitle(),
                book.getAuthor());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        book.setBookId(id);
        return book;
    }

    @Override
    public Book getBookById(int id) {
        try {
           return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOK_SQL, this::mapRowToBook);
    }

    @Override
    public Book updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getTitle(),
                book.getAuthor(),
                book.getBookId());
        return book;
    }

    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);

    }
}
