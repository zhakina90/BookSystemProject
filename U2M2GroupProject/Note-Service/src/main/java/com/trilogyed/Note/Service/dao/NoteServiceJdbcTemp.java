package com.trilogyed.Note.Service.dao;

import com.trilogyed.Note.Service.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteServiceJdbcTemp implements NoteDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static final String INSERT_NOTE_SQL =
            "insert into note (book_id, note) values (?,?)";

    public static final String GET_NOTE_SQL =
            "select * from note where note_id=?";

    public static final String GET_ALL_NOTES_SQL=
            "select * from note";

    public static final String UPDATE_NOTE_SQL=
            "update note set book_id = ?, note = ?";

    public static final String DELETE_NOTE=
            "delete from note where note_id =?";

    public static final String GET_NOTES_BY_BOOK_SQL =
            "select * from note where book_id =?";

    public NoteServiceJdbcTemp (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    @Transactional
    public Note createNote(Note note){

        jdbcTemplate.update(INSERT_NOTE_SQL,
               note.getBook_id(),
                note.getNote());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        note.setNote_id(id);
        return note;
    }

    @Override
    public  Note getNote(int id){

        try {
            return jdbcTemplate.queryForObject(GET_NOTE_SQL, this::mapRowToNote, id);
        }
       catch (EmptyResultDataAccessException e){
            return null;
       }

    }

    @Override
    public  List<Note> getNotesByBook(int book_id){

       return jdbcTemplate.query(GET_NOTES_BY_BOOK_SQL, this::mapRowToNote, book_id);
    }

    @Override
    public List<Note> getAllNotes(){

            return jdbcTemplate.query(GET_ALL_NOTES_SQL, this::mapRowToNote);

    }

    @Override
    public void updateNote(Note note){

        jdbcTemplate.update(UPDATE_NOTE_SQL,
                note.getBook_id(),
                note.getNote());
//                note.getNote_id());

    }

    @Override
    public void deleteNote(int note_id){
         jdbcTemplate.update(DELETE_NOTE, note_id);
    }

    private Note mapRowToNote(ResultSet rs, int rowNum) throws SQLException {
        Note note = new Note();
        note.setNote_id(rs.getInt("note_id"));
        note.setBook_id(rs.getInt("book_id"));
        note.setNote(rs.getString("note"));

        return note;
    }

}
