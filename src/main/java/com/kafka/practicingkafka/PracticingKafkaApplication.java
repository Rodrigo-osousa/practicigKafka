package com.kafka.practicingkafka;

import com.kafka.practicingkafka.anotherForm.ProducerEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;


@SpringBootApplication
public class PracticingKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticingKafkaApplication.class, args);


		PracticingKafkaApplication application = new PracticingKafkaApplication();
		application.kafkaRun();
	}
		private void kafkaRun() {
			ProducerEvent producer = new ProducerEvent();
			producer.runKafka();
		}


	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			for(int i = 0; i < 10000; i++) {
			kafkaTemplate.send("testtopic","Hello Kafka!" + i);}
		};
	}

}
