package lab2.claimservice.service;

import lab2.claimservice.repository.ClaimRepository;
import lab2.claimservice.repository.model.Claim;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

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

    public long createClaim(String email, String description) {
        final Claim claim = new Claim(email, description);
        final Claim savedClaim = claimRepository.save(claim);
        return savedClaim.getId();
    }

    public void updateClaim(long id, String email, String description, String status) {
        final Optional<Claim> maybeClaim = claimRepository.findById(id);
        if (maybeClaim.isEmpty()) throw new IllegalArgumentException("Claim not found");
        Claim claim = maybeClaim.get();
        if (email != null && !email.isBlank()) claim.setEmail(email);
        if (description != null && !description.isBlank()) claim.setDescription(description);
        if (status != null && !status.isBlank()) claim.setStatus(status);
        claimRepository.save(claim);
    }

    public void deleteClaim(long id) {
        claimRepository.deleteById(id);
    }
}
