package rs.ac.bg.fon.ai.naprednoProgramiranje.review;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    private String comment;
    private int grade;
}
