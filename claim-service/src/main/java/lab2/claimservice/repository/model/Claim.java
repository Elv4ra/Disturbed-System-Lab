package lab2.claimservice.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;
    private String description;
    private Timestamp date;
    private String status;

    public Claim() {}

    public Claim(long userId, String description) {
        this.userId = userId;
        this.description = description;
        this.date = new Timestamp(System.currentTimeMillis());
        this.status = "In Process";
    }
}
