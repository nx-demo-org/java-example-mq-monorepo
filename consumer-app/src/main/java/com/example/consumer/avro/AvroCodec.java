package com.example.consumer.avro;

import org.apache.avro.Schema;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecord;

import java.util.function.Supplier;

public final class AvroCodec {
  private AvroCodec() {}

  public static <T extends SpecificRecord> T decode(byte[] bytes, Schema schema, Supplier<T> instanceFactory) {
    try {
      var reader = new SpecificDatumReader<T>(schema);
      var decoder = DecoderFactory.get().binaryDecoder(bytes, null);
      return reader.read(instanceFactory.get(), decoder);
    } catch (Exception e) {
      throw new RuntimeException("Failed to decode Avro record", e);
    }
  }
}
