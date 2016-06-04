package com.knoldus.demo;

import com.knoldus.worker.Worker;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;
import java.util.concurrent.*;

/**
 * add session timeout into server.properties before start this consumer
 * group.max.session.timeout.ms=900000
 */
public class BatchConsumerApp {

    public static void main(String[] args) {

        long pollTimeout = 1000;
        int concurrencyFactor = 5;

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "batch-consumer-group");
        props.put("client.id", UUID.randomUUID().toString());
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        //disable auto commit
        props.put("enable.auto.commit", false);
        //10 minute session timeout
        props.put("session.timeout.ms", 600 * 1000);
        // after each minute check heart beat
        props.put("heartbeat.interval.ms", 60 * 1000);
        //15 minute  request timeout
        props.put("request.timeout.ms", 900 * 1000);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("demo-topic"));
        int poolSize = concurrencyFactor * Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(poolSize);
        CompletionService<Boolean> completionService = new ExecutorCompletionService(es);

        try {
            while (true) {
                System.out.println("Polling................");
                ConsumerRecords<String, String> records = consumer.poll(pollTimeout);
                List<ConsumerRecord> recordList = new ArrayList();
                for (ConsumerRecord<String, String> record : records) {
                    recordList.add(record);
                    //collect records
                    if (recordList.size() == poolSize) {
                        int taskCount = poolSize;
                        //distribute these messages across the workers
                        recordList.forEach(recordTobeProcess -> completionService.submit(new Worker(recordTobeProcess)));
                        //collect the processing result
                        List<Boolean> resultList = new ArrayList();
                        while (taskCount > 0) {
                            try {
                                Future<Boolean> futureResult = completionService.poll(1, TimeUnit.SECONDS);
                                if (futureResult != null) {
                                    boolean result = futureResult.get().booleanValue();
                                    resultList.add(result);
                                    taskCount = taskCount - 1;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        // verify all result are ok then commit it otherwise reprocess it.
                        Map<TopicPartition, OffsetAndMetadata> commitOffset =
                                Collections.singletonMap(
                                        new TopicPartition(record.topic(), record.partition()),
                                        new OffsetAndMetadata(record.offset() + 1));
                        consumer.commitSync(commitOffset);
                        //clear all commit records from list
                        recordList.clear();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }


    }

}
