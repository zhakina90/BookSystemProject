package com.trilogyed.NoteQueueConsumer.util.messages.feign;

import com.trilogyed.NoteQueueConsumer.model.Note;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteServiceClient {
    @RequestMapping(value = "notes/{note_id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable("note_id") int note_id, @RequestBody Note note);

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public Note addNote(@RequestBody Note note);

//    @RequestMapping(value = "/notes", method = RequestMethod.GET)
//    public List<Note> getNotesByBookID(@PathVariable ("book_id")int id);

//    @RequestMapping(value = "/notes", method = RequestMethod.GET)
//    public List<Note> getAllNotes () {

}
