package ma.cloud.kafkastream.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private Long id;
    private String number;
    private String clientName;
    private double amount;
}
