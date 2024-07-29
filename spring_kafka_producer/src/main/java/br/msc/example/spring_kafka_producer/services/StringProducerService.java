package br.msc.example.spring_kafka_producer.services;


import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class StringProducerService {

  private static final String TOPIC = "str-topic";
  private static final int TIMEOUT = 7;

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message) {
    Objects.requireNonNull(message, "message not be null");

    final var producerRecordMessage =
        new ProducerRecord<String, String>(TOPIC, "data", message);

    try {
      SendResult<String, String> result = kafkaTemplate.send(producerRecordMessage)
          .get(TIMEOUT, TimeUnit.SECONDS);

      log.info("Send message with success: partition=[{}], offset=[{}], data: {}",
          result.getRecordMetadata().partition(),
          result.getRecordMetadata().offset(),
          message);

    } catch (Exception e) {
      log.error("error at send message: {}", e.getMessage());
    }

  }

}
