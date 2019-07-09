package com.trilogyed.bookservice.util.messages;

public class NoteListEntry {
    private int bookId;
    private String note;

    public NoteListEntry(){};

    public NoteListEntry( String note, int bookId) {

        this.note = note;
        this.bookId = bookId;
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
    public String toString() {
        return "NoteListEntry{" +
                "bookId=" + bookId +
                ", note='" + note + '\'' +
                '}';
    }
}
