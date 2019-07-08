package com.trilogyed.NoteListQueueConsumer;

import com.trilogyed.NoteListQueueConsumer.feign.NoteServiceClient;
import com.trilogyed.NoteListQueueConsumer.model.Note;
import com.trilogyed.NoteListQueueConsumer.util.messages.NoteListEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class MessageListener {

    @Autowired
    NoteServiceClient noteServiceClient;

    @RabbitListener(queues = NoteListQueueConsumerApplication.QUEUE_NAME)
    public void recieveMessage(NoteListEntry msg){

//        try{
//            if(msg.getBookId() == 0){
//                noteServiceClient.addNote(new Note());
//            } else{
//                noteServiceClient.updateNote(msg.getBookId(), new Note());
//            }
//        } catch (IllegalArgumentException e){
//            return null;
//        }
//
//        return msg;

        if(msg.getBookId() == 0){
            noteServiceClient.addNote(new Note());
        } else{
            noteServiceClient.updateNote(msg.getBookId(), new Note());
        }



    }
}
