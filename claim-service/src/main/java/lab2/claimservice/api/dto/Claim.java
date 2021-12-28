package lab2.claimservice.api.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Claim {
    private String email;
    private String description;
    private Timestamp date;
    private String status;
}
