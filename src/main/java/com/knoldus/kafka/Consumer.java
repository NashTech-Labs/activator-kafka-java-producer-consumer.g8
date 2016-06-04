package com.knoldus.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

public class Consumer {

    private final KafkaConsumer<String, String> consumer;

    public Consumer(String groupId, String servers, List<String> topics) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("client.id", UUID.randomUUID().toString());
        props.put("group.id", groupId);
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        consumer = new KafkaConsumer(props);
        consumer.subscribe(topics);
    }


    public List<String> read() {
        try {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(10000);
            List<String> records = new ArrayList();
            consumerRecords.forEach(record -> records.add(record.value()));
            return records;
        } catch (WakeupException e) {
            e.printStackTrace();
            return Arrays.asList();
        }
    }


    public void close() {
        consumer.close();
    }

}
