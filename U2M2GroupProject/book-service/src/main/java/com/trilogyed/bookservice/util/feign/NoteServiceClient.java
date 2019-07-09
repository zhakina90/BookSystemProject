package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteServiceClient {
//    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
//    String getNote(@PathVariable("book_id") int id);

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
     List<Note> getAllNotes();

    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    public List<Note> getNotesByBookID(@PathVariable int book_id);


}


