package com.knoldus.worker;


import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Worker implements Callable<Boolean> {

    ConsumerRecord record;

    public Worker(ConsumerRecord record) {
        this.record = record;
    }

    public Boolean call() {

        Map<String, Object> data = new HashMap<>();
        try {
            // processing steps would we here
            data.put("partition", record.partition());
            data.put("offset", record.offset());
            data.put("value", record.value());
            // processing taking one minute
            Thread.sleep(60*1000);
            System.out.println("Processing Thread-" + Thread.currentThread().getName() + " data:  " + data);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }


    }


}