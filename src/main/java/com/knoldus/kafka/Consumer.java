package com.knoldus.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

public class Consumer {

    private final KafkaConsumer<String, String> consumer;
    private final List<String> topics;
    private final int id;


    public Consumer(int id, String groupId, String servers, List<String> topics) {
        this.id = id;
        this.topics = topics;
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
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
            consumerRecords.forEach( record -> records.add(record.value()));

        /*    for (ConsumerRecord<String, String> record : consumerRecords) {
                Map<String, Object> data = new HashMap<>();
                data.put("partition", record.partition());
                data.put("offset", record.offset());
                data.put("value", record.value());
                System.out.println(this.id + ": " + data);
                records.add(record.value());
            }*/
            return records;
        } catch (WakeupException e) {
            e.printStackTrace();
            // ignore for shutdown
            return Arrays.asList();
        } finally {
            //consumer.close();
        }
    }

    public void shutdown() {
        consumer.wakeup();
    }

}

/*
try {
  while (running) {
    ConsumerRecords<String, String> records = consumer.poll(1000);

    try {
      for (ConsumerRecord<String, String> record : records) {
        System.out.println(record.offset() + ": " + record.value());
        consumer.commitSync(Collections.singletonMap(record.partition(), new OffsetAndMetadata(record.offset() + 1)));
      }
    } catch (CommitFailedException e) {
      // application specific failure handling
    }
  }
} finally {
  consumer.close();
}
 */


/*
consumer.commitAsync(new OffsetCommitCallback() {
      @Override
      public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets,
                             Exception exception) {
        if (exception != null) {
          // application specific failure handling
        }
      }
    });
  }
} finally {
  consumer.close();
}
 */

/*
for more info
http://www.confluent.io/blog/tutorial-getting-started-with-the-new-apache-kafka-0.9-consumer-client
 */