package lab2.claimservice.api.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Claim {
    private long userId;
    private String description;
    private Timestamp date;
    private String status;
}
