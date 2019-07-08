package com.trilogyed.Note.Service.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Note {
    private int note_id;
    private int bookId;
    private String note;

    public Note(int note_id, int bookId, String note) {
        this.note_id = note_id;
        this.bookId = bookId;
        this.note = note;
    }

    public Note(int bookId, String note) {
        this.bookId = bookId;
        this.note = note;
    }

    public Note() {
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    @NotNull
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return note_id == note1.note_id &&
                bookId == note1.bookId &&
                Objects.equals(note, note1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note_id, bookId, note);
    }

    @Override
    public String toString() {
        return "Note{" +
                "note_id=" + note_id +
                ", bookId=" + bookId +
                ", note='" + note + '\'' +
                '}';
    }
}
