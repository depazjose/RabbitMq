package com.example.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

  @Value("${routing.topic.rabbitmq.#}")
  private String topicRabbitMQRoutingKey;

  @Value("${exchange.topic}")
  private String topicExchange;

  @Value("${queue.Dev}")
  private String queueDev;

  @Bean
  public Binding bindingTopicExchangeQueueCTopicRabbitMQ(TopicExchange topicExchange, Queue queueC) {
    return BindingBuilder.bind(queueC).to(topicExchange).with(topicRabbitMQRoutingKey);
  }

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(topicExchange);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory rabbitConnectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }

  @Bean
  public Queue queueDev() {
    return new Queue(queueDev);
  }
}
