package com.knoldus.demo;


import com.knoldus.kafka.Consumer;

import java.util.Arrays;
import java.util.List;

public class ConsumerApp {

    public static void main(String[] args){
        String groupId = "consumer-demo-group";
        List<String> topics = Arrays.asList("demo-topic");
        Consumer consumer = new Consumer(groupId,"localhost:9092" , topics);
        while (true){
            List<String> messages= consumer.read();
            System.out.println("messages count.................... " + messages.size());
            messages.forEach(record ->System.out.println("Reading message "+ record));
        }
    }

}

