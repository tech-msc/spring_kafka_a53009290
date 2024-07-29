package br.msc.example.spring_kafka_producer.resources;

import br.msc.example.spring_kafka_producer.services.StringProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class StringProducerResource {

  private final StringProducerService stringProducerService;

  @PostMapping
  public ResponseEntity<?> sendMessage(@RequestBody String entity) {
    stringProducerService.sendMessage(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
