package com.knoldus.demo;


import com.knoldus.kafka.Producer;

import java.util.stream.IntStream;

public class ProducerApp {


    public static void main(String[] args) {

        String topic = "demo-topic";
        Producer producer = new Producer("localhost:9092");
        // Send one million message to kafka queue
        IntStream.range(1, 1000001)
                .forEach(
                        messageNo -> {
                            System.out.println("Send message.... " + messageNo);
                            producer.send(topic, "message no- " + messageNo);
                        });

    }

}
