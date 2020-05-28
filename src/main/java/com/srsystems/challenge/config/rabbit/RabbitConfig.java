package com.srsystems.challenge.config.rabbit;

import com.srsystems.challenge.csv.receiver.CsvQueueReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class RabbitConfig {

    private String queueName = "test_queue";

//    @Bean
    Queue queue() {
        return new Queue(queueName);
    }

//    @Bean
    AbstractExchange exchange() {
        return new FanoutExchange("amq.fanout");
    }

//    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
        return BindingBuilder.bind(queue).to(exchange);
    }

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//        simpleMessageListenerContainer.setQueueNames(queueName);
//        simpleMessageListenerContainer.setMessageListener(listenerAdapter);
//
//        return simpleMessageListenerContainer;
//    }

//    @Bean
//    MessageListenerAdapter listenerAdapter(CsvQueueReceiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }

}
