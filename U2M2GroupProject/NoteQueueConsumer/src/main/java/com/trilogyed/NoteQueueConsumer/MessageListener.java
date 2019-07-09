package com.trilogyed.NoteQueueConsumer;

import com.trilogyed.NoteQueueConsumer.util.messages.NoteListEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(NoteListEntry msg) {
        System.out.println(msg.toString());
    }
}
