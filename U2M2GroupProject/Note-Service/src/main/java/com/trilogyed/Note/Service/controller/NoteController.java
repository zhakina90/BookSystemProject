package com.trilogyed.Note.Service.controller;

import com.trilogyed.Note.Service.dao.NoteDao;
import com.trilogyed.Note.Service.model.Note;
import com.trilogyed.Note.Service.uitl.messages.NoteListEntry;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteDao dao;

    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.notes.controller";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public NoteController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note addNote(@Valid @RequestBody Note note) {

        return dao.createNote(note);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable int id) {
        Note note1 = dao.getNote(id);
        return note1;
    }

    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNotesByBookID(@PathVariable int book_id) {
        List<Note> notes = dao.getNotesByBook(book_id);

        return notes;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes() {
        List<Note> notes = dao.getAllNotes();
        return notes;
    }

    @RequestMapping(value = "notes/{note_id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@PathVariable("note_id") int note_id, @Valid @RequestBody Note note) {
        note.setNote_id(note_id);
        dao.updateNote(note);
    }

    @RequestMapping(value = "notes/{note_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable int note_id) {
        dao.deleteNote(note_id);
    }
}
