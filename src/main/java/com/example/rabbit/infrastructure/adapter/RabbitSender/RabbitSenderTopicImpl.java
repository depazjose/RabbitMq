package com.example.rabbit.infrastructure.adapter.RabbitSender;

import com.example.rabbit.domain.model.sender.Payload;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitSenderTopicImpl implements RabbitSenderTopic {

    @Value("${exchange.topic}")
    private String topicExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendToTopicExchange(Payload payload, String topic) {
        rabbitTemplate.convertAndSend(topicExchange, topic, payload);
    }
}
