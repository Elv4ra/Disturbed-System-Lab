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

    private String email;
    private String description;
    private Timestamp date;
    private String status;

    public Claim() {}

    public Claim(String email, String description) {
        this.email = email;
        this.description = description;
        this.date = new Timestamp(System.currentTimeMillis());
        this.status = "In Process";
    }
}
