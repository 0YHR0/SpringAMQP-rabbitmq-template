package com.example.listener.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

/**
 * @author YHR
 * @date 30/11/2022 29:13:46
 * @description
 */

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String msg){
//        System.out.println("get the message: " + msg);
//
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("Listener 1 --->get the message : " + msg + LocalTime.now());
        Thread.sleep(20);

    }
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue2(String msg) throws InterruptedException {
        System.out.println("Listener 2 --->get the message: " + msg + LocalTime.now());
        Thread.sleep(200);

    }

    @RabbitListener(queues = "yhr.queue1")
    public void listenFanoutQueue1(String msg){
        System.out.println("get the message from yhr.queue1 : " + msg);

    }
    @RabbitListener(queues = "yhr.queue2")
    public void listenFanoutQueue2(String msg){
        System.out.println("get the message from yhr.queue2 : " + msg);

    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "yhr.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}))
    public void directQueue1(String msg){
        System.out.println("DirectQueue1: " + msg);

    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "yhr.direct", type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}))
    public void directQueue2(String msg){
        System.out.println("DirectQueue2: " + msg);

    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topicExchange.queue1"),
            exchange = @Exchange(name = "yhr.topicExchange", type = ExchangeTypes.TOPIC),
            key = "man.#"))
    public void topicExchangeQueue1(String msg){
        System.out.println("TopicExchangeQueue1: " + msg);

    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topicExchange.queue2"),
            exchange = @Exchange(name = "yhr.topicExchange", type = ExchangeTypes.TOPIC),
            key = "man.hao"))
    public void topicExchangeQueue2(String msg){
        System.out.println("TopicExchangeQueue2: " + msg);

    }

    @RabbitListener(queues = "ObjectQueue")
    public void objectListener(Map<String, Object> msg){
        System.out.println("ObjectMessage: " + msg);

    }
}
