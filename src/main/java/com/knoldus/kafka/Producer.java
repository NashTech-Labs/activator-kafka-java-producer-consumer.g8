package com.knoldus.kafka;



import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.Future;

public class Producer {

    private final KafkaProducer<String, String> producer;


    public Producer(String servers) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("acks", "all");
        props.put("retries", 5);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        producer = new KafkaProducer<String, String>(props);
    }


    public Future<RecordMetadata> send(String topic, String record) {
    	ProducerRecord<String, String>	message = new ProducerRecord<String, String>(topic, record, record);
      return  producer.send(message, new CallbackHandler());
    }

}

class CallbackHandler implements  Callback { 
    public void onCompletion(RecordMetadata metadata, Exception e) {
        if(e != null)
            e.printStackTrace();
        System.out.println("The offset of the record we just sent is: " + metadata.offset());
    }
}