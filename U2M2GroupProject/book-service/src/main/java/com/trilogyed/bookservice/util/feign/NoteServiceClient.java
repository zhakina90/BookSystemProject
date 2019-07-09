package com.trilogyed.bookservice.util.feign;

<<<<<<< HEAD
=======
import com.trilogyed.bookservice.model.Note;
>>>>>>> 5737fc2a0d093140ff28dd8fcabf6c3e03462a49
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

<<<<<<< HEAD
@FeignClient(name = "note-service")
public interface NoteServiceClient {
    @RequestMapping(value = "/notes/getbooksbyid/{book_id}", method = RequestMethod.GET)
    public String getNote(@PathVariable("book_id") int id);
=======
import java.util.List;

@FeignClient(name = "note-service")
public interface NoteServiceClient {
    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    String getNote(@PathVariable("book_id") int id);

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
     List<Note> getAllNotes();


    @RequestMapping(value = "/notes/{note_id}", method = RequestMethod.GET)
    Note getNoteById(@PathVariable("note_id") int id);


>>>>>>> 5737fc2a0d093140ff28dd8fcabf6c3e03462a49
}


