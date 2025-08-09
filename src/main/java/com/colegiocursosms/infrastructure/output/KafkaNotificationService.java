package com.colegiocursosms.infrastructure.output;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class KafkaNotificationService {

      @KafkaListener(groupId = "group-0", topics = "${spring.kafka.topics.welcome}", containerFactory = "validMessageContainerFactory")
      public void listener2(String message) {
            log.info("Listener 1 ::: Resibiendo un mensaje {}", message);
      }
}
