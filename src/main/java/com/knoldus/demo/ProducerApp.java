package com.knoldus.demo;


import com.knoldus.kafka.Producer;

public class ProducerApp {


    public static void main(String[] args) {
        final int tenMillion=1000000;
        int messageCount = tenMillion;
        String topic = "demo-topic";
        Producer producer = new Producer("localhost:9092");

        while (messageCount > 0) {
            int messageNo = tenMillion - messageCount;
            System.out.println("Send message.... " + messageNo);
            producer.send(topic, "message no- " + messageNo);
            messageCount = messageCount - 1;
        }

    }

}
