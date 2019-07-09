package com.trilogyed.Note.Service.controler;

import com.trilogyed.Note.Service.dao.NoteDao;
import com.trilogyed.Note.Service.model.Note;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteDao dao;

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note addNote(@RequestBody Note note) {
        Note note1 = dao.createNote(note);
        return note1;
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable int id) {
        Note note1 = dao.getNote(id);
        return note1;
    }

    @RequestMapping(value = "/notes/getbooksbyid/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNotesByBookID(@PathVariable int book_id) {
        List<Note> notes = dao.getNotesByBook(book_id);
//        List<Note> noteList = new ArrayList<>();
//        noteList.add(new Note(1, "testing"));
        return notes;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNotesByBook() {
        List<Note> notes = dao.getAllNotes();
        return notes;
    }

    @RequestMapping(value = "notes/{note_id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("note_id") int note_id, @RequestBody Note note) {
        dao.updateNote(note);
    }

    @RequestMapping(value = "notes/{note_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable int note_id) {
        dao.deleteNote(note_id);
    }
}
