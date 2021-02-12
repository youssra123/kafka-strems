package ma.cloud.kafkastream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(AnalyticsBinding.class)
public class KafkastreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkastreamApplication.class, args);
    }

}
