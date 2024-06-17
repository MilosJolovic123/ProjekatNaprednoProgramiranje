package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Service layer that handles all business logic of working with User class.
 * @author milos jolovic
 */
@Service
public class UserService {
    /**
     * Reference to the UserRepository.
     */
    final UserRepository userRepository;

    /**
     * constructor injection for the dependency userRepository.
     * @param userRepository reference to the user repository.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Method for creation of a User.
     * @param appUser to be created
     * @throws RuntimeException if the provided user is null.
     * @return User created.
     */
    public AppUser createUser(AppUser appUser) {
        if(appUser ==null)
            throw new RuntimeException("The provided app user is null!");
        return userRepository.save(appUser);
    }
    /**
     * Method for retrieval of all Users.
     * @return list of all Users.
     */
    public List<AppUserDTO> getAllUsers() {
        List<AppUser> users = userRepository.findAll();

        List<AppUserDTO> userDTOs = new ArrayList<>();

        for(AppUser u:users){
            AppUserDTO userDTO = new AppUserDTO();
            userDTO.setUsername(u.getUsername());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
    /**
     * Retrieves a specific user.
     * @param id of a user to be retrieved.
     * @throws RuntimeException if the user does not exist in H2.
     * @return User retrieved.
     */
   public AppUserDTO getUserById(Long id) {
        Optional<AppUser> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new RuntimeException("The provided user does not exist in H2!");
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setUsername(user.get().getUsername());
        return userDTO;
    }
    /**
     * Method for updating a specific User.
     * @param appUser carries new info.
     * @param requestedId user to be updated.
     * @throws RuntimeException the given user does not exist!
     * @return newly updated user.
     */
    public AppUser updateUser(AppUser appUser,Long requestedId) {

        Optional<AppUser> userToUpdate = userRepository.findById(requestedId);
        if(userToUpdate.isEmpty())
            throw new RuntimeException("The given user does not exist!");
        userToUpdate.get().setUsername(appUser.getUsername());
        userToUpdate.get().setPassword(appUser.getPassword());
        userToUpdate.get().setRoles(appUser.getRoles());
        userToUpdate.get().setNameAndLastName(appUser.getNameAndLastName());
        userToUpdate.get().setReviewSet(appUser.getReviewSet());
        userToUpdate.get().setNewsletterSet(appUser.getNewsletterSet());

        return userRepository.save(userToUpdate.get());
    }
    /**
     * Method for deleting a specific user.
     * @param requestedId of a user to be retrieved and Deleted
     * @throws RuntimeException if the user is already not in H2.
     */
    public void deleteUser(Long requestedId) {
        Optional<AppUser> userToDelete = userRepository.findById(requestedId);
        if(userToDelete.isEmpty())
            throw new RuntimeException("The provided user does not exist in DB!");
        userRepository.deleteById(requestedId);
    }



}

