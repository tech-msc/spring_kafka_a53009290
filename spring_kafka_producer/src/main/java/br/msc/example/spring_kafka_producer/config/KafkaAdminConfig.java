package br.msc.example.spring_kafka_producer.config;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;

@Configuration
@RequiredArgsConstructor
public class KafkaAdminConfig {

  public final KafkaProperties kafkaProperties;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    var configs = new HashMap<String, Object>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    return new KafkaAdmin(configs);
  }

  @Bean
  public KafkaAdmin.NewTopics topics() {
    return new NewTopics(
        strTopicCreate()
    );
  }

  private NewTopic strTopicCreate() {
    return TopicBuilder.name("str-topic")
        // .partitions(2)
        .partitions(1) // kafdrop test
        .replicas(1)
        .build();
  }
}
