package lab2.claimservice.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class User {
    private String email;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String userType;
}
