package ma.cloud.kafkastream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ma.cloud.kafkastream.entities.Bill;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class BillsProcessing {
    ObjectMapper objectMapper = new ObjectMapper();
    @StreamListener
    @SendTo({AnalyticsBinding.BILLS_OUT})
    public KStream<String, Double> process(@Input(AnalyticsBinding.BILLS_IN) KStream<Long,String> events){
        return events
                .map((k,v)->new KeyValue<>(k, bill(v)))
                .map((k,v)->new KeyValue<>(v.getClientName(),v.getAmount()))
                .groupByKey()
                .windowedBy(TimeWindows.of(5000))
                .reduce(Double::sum).toStream()
                .map((k,v)->new KeyValue<>(k.key(),v));
    }

    public Bill bill(String strObject){
        Bill bill=new Bill();
        try {
            bill=objectMapper.readValue(strObject,Bill.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bill;
    }


}
