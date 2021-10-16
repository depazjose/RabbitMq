package com.example.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Value("${routing.topic.rabbitmq.#}")
  private String topicRabbitMQRoutingKey;

  @Bean
  public Binding bindingTopicExchangeQueueCTopicRabbitMQ(TopicExchange topicExchange, Queue queueC) {
    return BindingBuilder.bind(queueC).to(topicExchange).with(topicRabbitMQRoutingKey);
  }
}
