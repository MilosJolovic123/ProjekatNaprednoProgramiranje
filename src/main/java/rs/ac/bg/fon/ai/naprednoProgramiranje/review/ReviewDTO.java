package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import lombok.*;
/**
 * DTO class for retrieval and usage of Review.
 * @author milos jolovic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    /**
     * Comment of the review.
     */
    private String comment;
    /**
     * Grade of the review between 5 and 10.
     */
    private int grade;
}
