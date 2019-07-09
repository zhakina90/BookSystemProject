package com.trilogyed.bookservice.viewModel;

import com.trilogyed.bookservice.model.Note;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class BookViewModel {
    @NotNull
    private int bookId;
    @NotNull
    @Size(max = 50)
    private String title;
    @NotNull
    @Size(max = 50)
    private String author;
    private List<Note> noteList;

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return bookId == that.bookId &&
                title.equals(that.title) &&
                author.equals(that.author) &&
                noteList.equals(that.noteList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, noteList);
    }
}
