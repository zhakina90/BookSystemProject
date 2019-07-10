package com.trilogyed.Note.Service.dao;

import com.trilogyed.Note.Service.model.Note;


import java.util.List;


public interface NoteDao {

    Note createNote(Note note);

    Note getNote(int id);

    List<Note> getNotesByBook(int bookId);

    List<Note> getAllNotes();

    void updateNote(Note note);

    void deleteNote(int note_id);
}
