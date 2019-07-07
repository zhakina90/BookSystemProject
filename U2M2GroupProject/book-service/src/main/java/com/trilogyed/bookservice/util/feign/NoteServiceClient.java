package com.trilogyed.bookservice.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "note-service")
public interface NoteServiceClient {
    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    public String getNote(@PathVariable("book_id") int id);
}


