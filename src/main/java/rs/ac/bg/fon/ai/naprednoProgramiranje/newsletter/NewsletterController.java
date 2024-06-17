package rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class NewsletterController {
    private final NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/newsletter/{requestedId}")
    public Optional<Newsletter> getNewsletter(@PathVariable Long requestedId) {
        return newsletterService.findOne(requestedId);
    }
    @PostFilter("filterObject.newsletter_user.username==authentication.name")
     @GetMapping("/newsletters")
    public List<Newsletter> getAllNewsletters() {
        return newsletterService.findAll();

    }
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @PostMapping("/newsletter/add")
    public ResponseEntity<Newsletter> save(@AuthenticationPrincipal UserDetails loggedUser, @RequestBody Newsletter newsletter) {
        newsletter.setNewsletter_date(Date.valueOf(LocalDate.now()));
        return ResponseEntity.ok(newsletterService.save(loggedUser,newsletter));
    }

}
