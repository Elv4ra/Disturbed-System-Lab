package lab2.claimservice.service;

import lab2.claimservice.api.dto.User;
import lab2.claimservice.repository.ClaimRepository;
import lab2.claimservice.repository.model.Claim;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    private final String userServiceUrlAdress ="http://user-service:8080/users";

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public List<Claim> getAllClaimsByStatus(String status) {
        return claimRepository.findAllByStatus(status);
    }

    public List<Claim> getAllClaimsByDate(Timestamp date) {
        List<Claim> claims = claimRepository.findAll(), result = new ArrayList<>();
        for (Claim claim : claims) {
            if (claim.getDate().before(new Timestamp(date.getTime()+Long.parseLong("93600000")))
                    && claim.getDate().after(new Timestamp(date.getTime()-Long.parseLong("79200000"))))
            {
                result.add(claim);
            }
        }
        return result;
    }

    private boolean checkUser(long userId) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<Long> userRequest = new HttpEntity<>(userId);

        final ResponseEntity<User> userResponse = restTemplate
                .exchange(userServiceUrlAdress + "/dto/" + userId,
                        HttpMethod.GET, userRequest, User.class);

        return userResponse.getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public long createClaim(long userId, String description) {
        if (!checkUser(userId)) throw new IllegalArgumentException("User Not Found");
        final Claim claim = new Claim(userId, description);
        final Claim savedClaim = claimRepository.save(claim);
        return savedClaim.getId();
    }

    public void updateClaim(long id, long userId, String description, String status) {
        final Optional<Claim> maybeClaim = claimRepository.findById(id);
        if (maybeClaim.isEmpty()) throw new IllegalArgumentException("Claim not found");
        Claim claim = maybeClaim.get();
        if (checkUser(userId)) claim.setUserId(userId);
        if (description != null && !description.isBlank()) claim.setDescription(description);
        if (status != null && !status.isBlank()) claim.setStatus(status);
        claimRepository.save(claim);
    }

    public void deleteClaim(long id) {
        claimRepository.deleteById(id);
    }
}
