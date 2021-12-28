package lab2.claimservice.api;


import lab2.claimservice.repository.model.Claim;
import lab2.claimservice.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public ResponseEntity<List<Claim>> findAll() {
        List<Claim> claims = claimService.getAllClaims();
        if (claims.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(claims);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Claim>> findAllByStatus(@PathVariable String status) {
        List<Claim> claims = claimService.getAllClaimsByStatus(status);
        if (claims.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(claims);
    }

    @GetMapping("/date/{input}")
    public ResponseEntity<List<Claim>> findAllByDate(@PathVariable String input) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date temp = formatter.parse(input);
            Timestamp date = new Timestamp(temp.getTime());
            List<Claim> claims = claimService.getAllClaimsByDate(date);
            if (claims.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(claims);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody lab2.claimservice.api.dto.Claim claim) {
        final String email = claim.getEmail();
        final String description = claim.getDescription();
        try {
            final long id = claimService.createClaim(email, description);
            final String location = String.format("/claims/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody lab2.claimservice.api.dto.Claim claim) {
        final String email = claim.getEmail();
        final String description = claim.getDescription();
        final String status = claim.getStatus();
        try {
            claimService.updateClaim(id, email, description, status);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }

}
