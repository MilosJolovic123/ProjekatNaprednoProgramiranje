package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

      @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    @GetMapping("/reviews/{requestedId}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long requestedId){
        return ResponseEntity.ok(reviewService.getReview(requestedId));
    }
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
     @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews(){
        return ResponseEntity.ok(reviewService.getReviews());
    }
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
   @PostMapping("/reviews/add/{filmId}")
    public ResponseEntity<Review> addReview(@RequestBody Review review, @PathVariable Long filmId, @AuthenticationPrincipal UserDetails loggedUser){
        return ResponseEntity.ok(reviewService.saveReview(review,filmId,loggedUser));
    }
    @PostAuthorize("returnObject.user.username == authentication.name")
     @PutMapping("/reviews/update/{requestedId}")
    public Review updateReview(@RequestBody Review review,@PathVariable Long requestedId){
        return reviewService.updateReview(review,requestedId);
    }
    @PostAuthorize("hasRole('ADMIN')||returnObject.user.username == authentication.name")
    @DeleteMapping("/reviews/delete/{requestedId}")
    public Review deleteReview(@PathVariable Long requestedId){
        return reviewService.DeleteReview(requestedId);
    }
}
