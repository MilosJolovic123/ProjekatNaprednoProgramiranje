package rs.ac.bg.fon.ai.naprednoProgramiranje.newsletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NewsletterService {
    private final UserRepository userRepository;
    private final NewsletterRepository newsletterRepository;

    @Autowired
    public NewsletterService(UserRepository userRepository, NewsletterRepository newsletterRepository) {
        this.userRepository = userRepository;
        this.newsletterRepository = newsletterRepository;
    }

    public Newsletter save(UserDetails loggedUser, Newsletter newsletter) {

        String username = loggedUser.getUsername();

        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if(newsletter.getNewsletter_date().toLocalDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date can't be after current date!");
        newsletter.setNewsletter_user(user.get());
        return newsletterRepository.save(newsletter);
    }

    public Optional<Newsletter> findOne(Long id) {
        Optional<Newsletter> newsletter = newsletterRepository.findById(id);
        if(newsletter.isEmpty())
            throw new RuntimeException("Newsletter not found!");
        return newsletter;
    }

    public List<Newsletter> findAll(){
        return newsletterRepository.findAll();
    }
}
