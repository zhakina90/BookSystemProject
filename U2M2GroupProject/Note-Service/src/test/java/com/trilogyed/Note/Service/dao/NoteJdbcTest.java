package com.trilogyed.Note.Service.dao;

import com.trilogyed.Note.Service.model.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteJdbcTest {

    @Autowired
    protected NoteDao dao;

    @Before
    public void setUp() throws Exception {

        List<Note> nList = dao.getAllNotes();

        nList.stream()
                .forEach(note -> dao.deleteNote(note.getNote_id()));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteNote(){
        Note note = new Note();
        note.setNote_id(1);
        note.setBook_id(22);
        note.setNote("This book is alright...could be better.");

        note = dao.createNote(note);

        Note note2 = dao.getNote(note.getNote_id());

        assertEquals (note2, note);

        dao.deleteNote(note.getNote_id());

        note2 = dao.getNote(note.getNote_id());

        assertNull(note2);

    }

    @Test
    public void updateNote(){
        Note note = new Note();
        note.setNote_id(1);
        note.setBook_id(22);
        note.setNote("This book is alright...could be better.");

        note = dao.createNote(note);

        note.setBook_id(33);
        note.setNote("Ok, its actually kinda good");

        dao.updateNote(note);
        Note note2 = dao.getNote(note.getNote_id());
        assertEquals(note, note2);
    }

    @Test
    public void getNoteByBook(){
        Note note = new Note();
        note.setNote_id(1);
        note.setBook_id(22);
        note.setNote("This book is alright...could be better.");

        note = dao.createNote(note);

        Note note2 = new Note();
        note2.setNote_id(2);
        note2.setBook_id(22);
        note2.setNote("Awesome");

        Note note3 = new Note();
        note3.setNote_id(3);
        note3.setBook_id(21);
        note3.setNote("Coolbeans");

        note2= dao.createNote(note2);
        note3 = dao.createNote(note3);

        List<Note> notes = dao.getNotesByBook(note.getBook_id());
        assertEquals(2, notes.size());
    }
}
