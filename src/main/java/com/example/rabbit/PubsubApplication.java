package com.example.rabbit;

import com.example.rabbit.domain.model.sender.Payload;
import com.example.rabbit.infrastructure.adapter.RabbitSender.RabbitSenderTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PubsubApplication  implements CommandLineRunner {

	@Autowired
	private RabbitSenderTopic rabbitSenderTopic;

	public static void main(String[] args) {
		SpringApplication.run(PubsubApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		for (int x=0; x<=150; x++) {
			Payload payload = Payload.builder().message("messages" + x).build();
			rabbitSenderTopic.sendToTopicExchange(payload, "demo.mdt");
			Thread.sleep(1000);
			System.out.println("send message:" + x);
		}
	}
}
