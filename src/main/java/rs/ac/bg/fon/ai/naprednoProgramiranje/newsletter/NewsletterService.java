package rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * Service layer that handles all business logic with Newsletter class.
 * @author milos jolovic
 */
@Service
public class NewsletterService {
    /**
     * Private field reference to the User repo.
     */
    private final UserRepository userRepository;
    /**
     * Private field reference to the Newsletter repo.
     */
    private final NewsletterRepository newsletterRepository;

    /**
     * Constructor injection of the above defined repo dependencies.
     * @param userRepository reference to the specific user repo.
     * @param newsletterRepository reference to the specific newsletter repo.
     */
    @Autowired
    public NewsletterService(UserRepository userRepository, NewsletterRepository newsletterRepository) {
        this.userRepository = userRepository;
        this.newsletterRepository = newsletterRepository;
    }
    /**
     * Method for saving a specific Newsletter passed through method parameter into H2 db.
     * @param loggedUser currently logged user
     * @param newsletter to be saved
     * @return saved newsletter
     * @throws RuntimeException if user is not to be found in db.
     *
     */
    public Newsletter save(UserDetails loggedUser, Newsletter newsletter) {

        String username = loggedUser.getUsername();

        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        newsletter.setNewsletter_date(Date.valueOf(LocalDate.now()));
        newsletter.setNewsletter_user(user.get());
        return newsletterRepository.save(newsletter);
    }
    /**
     * Method that finds specific Newsletter.
     * @param id of a specific Newsletter.
     * @return found Newsletter
     * @throws RuntimeException if newsletter is not to be found.
     */
    public Optional<Newsletter> findOne(Long id) {
        Optional<Newsletter> newsletter = newsletterRepository.findById(id);
        if(newsletter.isEmpty())
            throw new RuntimeException("Newsletter not found!");
        return newsletter;
    }
    /**
     * Returns all newsletters from H2 db.
     * @return all newsletters from H2 db.
     */
    public List<Newsletter> findAll(){
        return newsletterRepository.findAll();
    }
}
