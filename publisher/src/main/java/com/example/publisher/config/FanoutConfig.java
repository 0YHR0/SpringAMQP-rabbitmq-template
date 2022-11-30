package com.example.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YHR
 * @date 30/11/2022 08:14:19
 * @description
 */

@Configuration
public class FanoutConfig {
    //fanout
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("yhr.fanout");
    }

    //queue1
    @Bean
    public Queue queue1(){
        return new Queue("yhr.queue1");
    }

    //queue2
    @Bean
    public Queue queue2(){
        return new Queue("yhr.queue2");
    }

    //bind fanout with queue1 and queue2
    @Bean
    public Binding binding1(Queue queue1, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(queue1)
                .to(fanoutExchange);
    }

    @Bean
    public Binding binding2(Queue queue2, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(queue2)
                .to(fanoutExchange);
    }

    @Bean
    public Queue objectQueue(){
        return new Queue("ObjectQueue");
    }
}
