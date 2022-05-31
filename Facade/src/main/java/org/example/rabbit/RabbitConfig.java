package org.example.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Artyom Kulagin
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue cityIdentifierInQueue() {
        return new Queue("cityIdentifierInQueue");
    }

    @Bean
    public Queue cityIdentifierOutQueue() {
        return new Queue("cityIdentifierOutQueue");
    }

    @Bean
    public Queue weatherReporterInQueue() {
        return new Queue("weatherReporterInQueue");
    }

    @Bean
    public Queue weatherReporterOutQueue() {
        return new Queue("weatherReporterOutQueue");
    }

    @Bean
    public Queue messageSenderInQueue() {
        return new Queue("messageSenderInQueue");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMaxConcurrentConsumers(5);
        return factory;
    }

    @Bean
    public static RabbitSender sender() {
        return new RabbitSender();
    }
}
