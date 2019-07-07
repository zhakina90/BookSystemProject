package com.trilogyed.bookservice.viewModel;

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
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
                author.equals(that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author);
    }
}
