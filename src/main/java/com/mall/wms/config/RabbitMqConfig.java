package com.mall.wms.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author GCC
 * create on 2020/4/15 10:32
 */
@Configuration
public class RabbitMqConfig {

    @Bean("oneRabbitConnectionFactory")
    @Primary
    public ConnectionFactory oneRabbitConnectionFactory(
            @Value("${spring.rabbitmq.one.host}") String host,
            @Value("${spring.rabbitmq.one.port}") int port,
            @Value("${spring.rabbitmq.one.username}") String username,
            @Value("${spring.rabbitmq.one.password}") String password,
            @Value("${spring.rabbitmq.one.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean("twoRabbitConnectionFactory")
    public ConnectionFactory twoRabbitConnectionFactory(
            @Value("${spring.rabbitmq.two.host}") String host,
            @Value("${spring.rabbitmq.two.port}") int port,
            @Value("${spring.rabbitmq.two.username}") String username,
            @Value("${spring.rabbitmq.two.password}") String password,
            @Value("${spring.rabbitmq.two.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean(name = "oneRabbitTemplate")
    public RabbitTemplate firstRabbitTemplate(
            @Qualifier("oneRabbitConnectionFactory") ConnectionFactory connectionFactory
    ) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name="twoRabbitTemplate")
    public RabbitTemplate secondRabbitTemplate(
            @Qualifier("twoRabbitConnectionFactory") ConnectionFactory connectionFactory
    ){
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name="oneFactory")
    public SimpleRabbitListenerContainerFactory hospSyncFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("oneRabbitConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }


    @Bean(name="twoFactory")
    public SimpleRabbitListenerContainerFactory hPayFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("twoRabbitConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
