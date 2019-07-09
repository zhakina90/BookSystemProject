package com.trilogyed.NoteListQueueConsumer;

import com.trilogyed.NoteListQueueConsumer.feign.NoteServiceClient;
import com.trilogyed.NoteListQueueConsumer.model.Note;
import com.trilogyed.NoteListQueueConsumer.util.messages.NoteListEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class MessageListener {

    @Autowired
    NoteServiceClient noteServiceClient;

    @RabbitListener(queues = NoteListQueueConsumerApplication.QUEUE_NAME)
    public void recieveMessage(Note note){


       if(note.getNote_id()== 0){
           try{

               noteServiceClient.addNote(note);
           }catch (BeanCreationException e){
               System.out.println(e);
           }
       }else{
           noteServiceClient.updateNote(note.getNote_id(), note);
       }



    }
}
