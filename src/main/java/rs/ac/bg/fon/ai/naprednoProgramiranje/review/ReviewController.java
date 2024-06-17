package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Rest controller that ensures all endpoints for HTTP requests operated on Review class.
 * @author milos jolovic
 */
@RestController
public class ReviewController {
    /**
     * Private field direct reference to the ReviewService class.
     */
    private final ReviewService reviewService;

    /**
     * Constructor injection of review service dependency.
      * @param reviewService reference to the specific review Service.
     */
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    /**
     * Secured method for retrieving a specific Review.
     * @param requestedId of a Review to be found and retrieved.
     * @return Review to be shown.
     */
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @GetMapping("/reviews/{requestedId}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long requestedId){
        return ResponseEntity.ok(reviewService.getReview(requestedId));
    }
    /**
     * Secured method for retrieving all Reviews on all films.
     * @return List of all reviews.
     */
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
     @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews(){
        return ResponseEntity.ok(reviewService.getReviews());
    }
    /**
     * Secured method for adding a review.
     * @param review to be added.
     * @param filmId on which film to add a review.
     * @param loggedUser currently logged user that gives the review.
     * @return the given and persisted review in H2 db.
     */
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
   @PostMapping("/reviews/add/{filmId}")
    public ResponseEntity<Review> addReview(@RequestBody Review review, @PathVariable Long filmId, @AuthenticationPrincipal UserDetails loggedUser){
        return ResponseEntity.ok(reviewService.saveReview(review,filmId,loggedUser));
    }
    /**
     * Secured method that allows for a review to be changed only by the user that created
     * the review in the first place.
     * @param review that carries new info
     * @param requestedId old review to be changed.
     * @return the newly updated review
     */
    @PostAuthorize("returnObject.user.username == authentication.name")
     @PutMapping("/reviews/update/{requestedId}")
    public Review updateReview(@RequestBody Review review,@PathVariable Long requestedId){
        return reviewService.updateReview(review,requestedId);
    }
    /**
     * Secured method that allows admins and  user that posted newsletter to delete it.
     * @param requestedId of a newsletter to be found in H2 and deleted.
     * @return the given review that is deleted from H2 db.
     */
    @PostAuthorize("hasRole('ADMIN')||returnObject.user.username == authentication.name")
    @DeleteMapping("/reviews/delete/{requestedId}")
    public Review deleteReview(@PathVariable Long requestedId){
        return reviewService.DeleteReview(requestedId);
    }
}
