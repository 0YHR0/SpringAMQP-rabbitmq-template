package com.example.publisher;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class PublisherApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //    @Test
//    void contextLoads() {
//    }
    @Test
    public void textMq(){
        String queueName = "simple.queue";
        String message = "hello from yhr";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    void testFanoutPublish() {
        String exchangeName = "yhr.fanout";
        String msg = "Hello, everyone! I'm bubu";
        rabbitTemplate.convertAndSend(exchangeName, "", msg);
    }

    @Test
    public void textWorkMq() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello from yhr__";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
        

    }

    @Test
    void testDirectPublish() {
        String exchangeName = "yhr.direct";
        String msg = "Hello, everyone! I'm red";
        rabbitTemplate.convertAndSend(exchangeName, "red", msg);
    }

    @Test
    void testTopicPublish() {
        String exchangeName = "yhr.topicExchange";
        String msg = "Hello, everyone! I'm man.yang";
        rabbitTemplate.convertAndSend(exchangeName, "man.hao", msg);
    }

    @Test
    void testObjectPublish(){
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "yang");
        msg.put("age", 23);
        rabbitTemplate.convertAndSend("ObjectQueue", msg);

    }


}