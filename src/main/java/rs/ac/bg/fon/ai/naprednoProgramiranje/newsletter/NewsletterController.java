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
/**
 * Rest controller that ensures GET and POST endpoints of  Newsletter class.
 * @author milos jolovic
 */
@RestController
public class NewsletterController {
    /**
     * Private field, reference to the NewsletterService class.
     */
    private final NewsletterService newsletterService;

    /**
     * Constructor injection of newsletter service dependency.
     * @param newsletterService reference to the specific newsletter Service.
     */
    @Autowired
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }
    /**
     * Secured method for retrieving specific newsletter from H2 db.
     * @param requestedId  of a newsletter to be found and retrieved from H2.
     * @return requested newsletter.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/newsletter/{requestedId}")
    public Optional<Newsletter> getNewsletter(@PathVariable Long requestedId) {
        return newsletterService.findOne(requestedId);
    }
    /**
     * Secured method that gets all elements of Newsletter table in H2.
     * @return ONLY specific newsletter - the ones that belong to the logged user.
     */
    @PostFilter("filterObject.newsletter_user.username==authentication.name")
     @GetMapping("/newsletters")
    public List<Newsletter> getAllNewsletters() {
        return newsletterService.findAll();

    }
    /**
     * Secured method for applying to the newsletter.
     * @param loggedUser user currently logged on the API.
     * @param newsletter specific newsletter to be added.
     * @return newsletter added.
     */
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @PostMapping("/newsletter/add")
    public ResponseEntity<Newsletter> save(@AuthenticationPrincipal UserDetails loggedUser, @RequestBody Newsletter newsletter) {
        newsletter.setNewsletter_date(Date.valueOf(LocalDate.now()));
        return ResponseEntity.ok(newsletterService.save(loggedUser,newsletter));
    }

}
