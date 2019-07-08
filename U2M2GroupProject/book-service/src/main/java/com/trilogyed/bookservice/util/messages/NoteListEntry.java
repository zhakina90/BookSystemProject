package com.trilogyed.bookservice.util.messages;

public class NoteListEntry {

    private String title;
    private String author;
    private String note;

    public NoteListEntry(){};

    public NoteListEntry(String title, String author, String note) {
        this.title = title;
        this.author = author;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "The entry added to the note list is as follows: " +
                "title = '" + title + ' ' +
                ", author = '" + author + ' ' +
                ", note = '" + note + ' ' +
                " .";
    }
}
