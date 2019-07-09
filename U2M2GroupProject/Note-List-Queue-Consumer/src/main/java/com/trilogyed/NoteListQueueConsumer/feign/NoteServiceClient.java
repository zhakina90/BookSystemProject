package com.trilogyed.NoteListQueueConsumer.feign;

import com.trilogyed.NoteListQueueConsumer.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteServiceClient {

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    Note addNote(Note note);

    @RequestMapping(value = "/notes/{note_id}", method = RequestMethod.PUT)
     void updateNote(@PathVariable int note_id, @RequestBody Note note);

}
