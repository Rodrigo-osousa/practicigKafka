package com.kafka.practicingkafka.anotherForm;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class ProducerEvent {

    private final Producer<String, String> producer;

    public ProducerEvent() {
        producer = createProducer();

    }

    private Producer<String, String> createProducer(){
        if (producer!= null) {
            return producer;
        }

        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("serializer.class","kafka.serializer.DefaultEncoder");

    return new KafkaProducer<String, String>(props);
    }
    public void runKafka() {
        String key = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String message = sdf.format(new Date());
        message += "|" + key;
        message += "|New_Message";

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                "EventRecord", key, message);
        producer.send(record);
        producer.flush();
        producer.close();

    }
}
