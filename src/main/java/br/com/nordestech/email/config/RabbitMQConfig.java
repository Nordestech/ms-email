package br.com.nordestech.email.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EMAIL_EXCHANGE = "email.v1.events";
    public static final String QUEUE_WELCOME_EMAIL = "email.welcome.queue";
    public static final String ROUTING_KEY_WELCOME_EMAIL = "email.welcome";

    // 1. Fila que armazenará as mensagens
    @Bean
    public Queue emailWelcomeQueue() {
        return QueueBuilder.durable(QUEUE_WELCOME_EMAIL)
                .build();
    }

    // 2. Exchange do tipo Direct
    @Bean
    public DirectExchange emailWelcomeExchange() {
        return new DirectExchange(EMAIL_EXCHANGE);
    }

    // 3. Vinculo (Binding) entre a Fila e a Exchange através de uma Routing Key
    @Bean
    public Binding emailWelcomeBinding(@Qualifier("emailWelcomeQueue") Queue emailWelcomeQueue, DirectExchange emailWelcomeExchange) {
        return BindingBuilder.bind(emailWelcomeQueue)
                .to(emailWelcomeExchange)
                .with(ROUTING_KEY_WELCOME_EMAIL);
    }

    // 4. Conversor para enviar/receber objetos em formato JSON (Jackson)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}