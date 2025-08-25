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
      @Value("${spring.kafka.topics.auth-login}")
      private String welcomeTopic;
      @Value("${spring.kafka.topics.students-created}")
      private String studentRegisterTopic;
      @Value("${spring.kafka.topics.teacher-created}")
      private String teacherRegisterTopic;

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
                  TopicBuilder.name(welcomeTopic)
                        .partitions(4)
                        .replicas(2)
                        .build(),
                  TopicBuilder.name(studentRegisterTopic)
                        .partitions(3)
                        .replicas(2)
                        .build()
                  ,
                  TopicBuilder.name(teacherRegisterTopic)
                        .partitions(3)
                        .replicas(2)
                        .build()
            );
      }
}
