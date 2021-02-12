package ma.cloud.kafkastream;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface AnalyticsBinding {
    String BILLS_OUT = "TOTAL_CUSTOMERS";
    String BILLS_IN = "FACTURATION";

    @Output(BILLS_OUT)
    KStream<String, Double> billsOut();

    @Input(BILLS_IN)
    KStream<Long, String> billsIn();
}