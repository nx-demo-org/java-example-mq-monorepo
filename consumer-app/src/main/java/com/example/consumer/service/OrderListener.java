package com.example.consumer.service;

import com.example.consumer.avro.AvroCodec;
import com.example.contracts.avro.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderListener {

  private final AtomicLong received = new AtomicLong();

  public long receivedCount() {
    return received.get();
  }

  @RabbitListener(queues = "orders.v1.queue")
  public void onMessage(byte[] body) {
    var evt = AvroCodec.decode(
        body,
        OrderCreatedEvent.getClassSchema(),
        OrderCreatedEvent::new
    );

    received.incrementAndGet();
    System.out.println("Received: " + evt);
  }
}
