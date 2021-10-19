package com.example.rabbit.infrastructure.adapter.RabbitSender;

import com.example.rabbit.domain.model.sender.Payload;

public interface RabbitSenderTopic {
    void sendToTopicExchange(Payload payload, String topic);
}
