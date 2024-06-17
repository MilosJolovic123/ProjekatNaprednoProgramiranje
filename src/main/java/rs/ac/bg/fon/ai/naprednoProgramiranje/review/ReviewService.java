package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.Film;
import rs.ac.bg.fon.ai.naprednoProgramiranje.film.FilmRepository;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, FilmRepository filmRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    public ReviewDTO getReview(Long requiredId){
        Optional<Review > r= reviewRepository.findById(requiredId);
        if(r.isEmpty())
            throw new RuntimeException("Review is not to be found!");
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setComment(r.get().getComment());
        reviewDTO.setGrade(r.get().getGrade());
        return reviewDTO;
    }

    public List<ReviewDTO> getReviews(){

        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDTO> reviewDTOs = new ArrayList<>();

        for(Review r:reviews){
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setComment(r.getComment());
            reviewDTO.setGrade(r.getGrade());
            reviewDTOs.add(reviewDTO);
        }

        return reviewDTOs;
    }

  public Review saveReview(Review review, Long filmId, UserDetails loggedUser){

        String username = loggedUser.getUsername();

        Optional<Film> filmOptional = filmRepository.findById(filmId);
        Optional<AppUser> userOptional = userRepository.findByUsername(username);

        if(filmOptional.isEmpty() || userOptional.isEmpty())
            throw new RuntimeException("Film or user is not found in H2!");

        review.setFilm(filmOptional.get());
        review.setUser(userOptional.get());

        if(review.getGrade()<5||review.getGrade()>10)
            throw new IllegalArgumentException("Film can't get a grade higher than 10 and less than 5.");

        review.setDateGiven(Date.valueOf(LocalDate.now()));


        return reviewRepository.save(review);
    }

     public Review updateReview(Review review, Long requestedId){
        Optional<Review> reviewOptional = reviewRepository.findById(requestedId);
        if(reviewOptional.isEmpty())
            throw new RuntimeException("Such review does not exist in H2!");

        reviewOptional.get().setComment(review.getComment());
        reviewOptional.get().setGrade(review.getGrade());
        reviewOptional.get().setDateGiven(review.getDateGiven());

        return reviewRepository.save(reviewOptional.get());

    }

    public Review DeleteReview(Long requestedId){
        Optional<Review> review = reviewRepository.findById(requestedId);
        if(review.isEmpty())
            throw new RuntimeException("Such review does not exist in H2!");
        reviewRepository.deleteById(requestedId);
        return review.get();
    }

}
