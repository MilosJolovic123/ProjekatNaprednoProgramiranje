package rs.ac.bg.fon.ai.naprednoProgramiranje.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser createUser(AppUser appUser) {
        if(appUser ==null)
            throw new RuntimeException("The provided app user is null!");
        return userRepository.save(appUser);
    }

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

   public AppUserDTO getUserById(Long id) {
        Optional<AppUser> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new RuntimeException("The provided user does not exist in H2!");
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setUsername(user.get().getUsername());
        return userDTO;
    }

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

    public void deleteUser(Long requestedId) {
        Optional<AppUser> userToDelete = userRepository.findById(requestedId);
        if(userToDelete.isEmpty())
            throw new RuntimeException("The provided user does not exist in DB!");
        userRepository.deleteById(requestedId);
    }



}

