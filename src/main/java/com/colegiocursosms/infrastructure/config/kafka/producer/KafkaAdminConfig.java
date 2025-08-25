package com.colegiocursosms.infrastructure.config.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class KafkaAdminConfig {

      private final KafkaProperties kafkaProperties;

      @Value("${spring.kafka.topics.courses-scheduled}")
      private String courseScheduledRegisterTopic;

      @Bean
      public KafkaAdmin kafkaAdmin() {
            var configs = new HashMap<String, Object>();
            configs.put(
                  AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                  kafkaProperties.getBootstrapServers()
            );
            return new KafkaAdmin(configs);
      }

      @Bean
      public KafkaAdmin.NewTopics topics() {
            return new KafkaAdmin.NewTopics(
                  TopicBuilder.name(courseScheduledRegisterTopic)
                        .partitions(1)
                        .replicas(2)
                        .build()
            );
      }
}
