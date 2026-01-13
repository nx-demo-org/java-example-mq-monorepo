package com.example.consumer.web;

import com.example.consumer.service.OrderListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConsumerController {

  private final OrderListener listener;

  public ConsumerController(OrderListener listener) {
    this.listener = listener;
  }

  @GetMapping("/status")
  public Map<String, Object> status() {
    return Map.of("receivedCount", listener.receivedCount());
  }
}
