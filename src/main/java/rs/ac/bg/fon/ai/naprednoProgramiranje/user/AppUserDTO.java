package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import lombok.*;
/**
 * DTO class of a user.
 * @author milos jolovic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUserDTO {
    /**
     * Username of a user.
     */
    private String username;
}
