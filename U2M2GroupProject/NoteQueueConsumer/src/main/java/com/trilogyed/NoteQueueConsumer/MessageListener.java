package com.trilogyed.NoteQueueConsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @RabbitListener(queues = EmailListQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(EmailListEntry msg) {
        System.out.println(msg.toString());
    }
}
