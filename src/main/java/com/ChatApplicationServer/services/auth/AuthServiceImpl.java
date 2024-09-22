package com.ChatApplicationServer.services.auth;

import com.ChatApplicationServer.dto.SignupRequest;
import com.ChatApplicationServer.dto.UserDto;
import com.ChatApplicationServer.entity.User;
import com.ChatApplicationServer.enums.UserRole;
import com.ChatApplicationServer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto createUser(SignupRequest signupRequest) throws Exception {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.USER);
        User createdUser = userRepository.save(user);
        UserDto createdUserDto = new UserDto();
        createdUserDto.setId(createdUser.getId());
        return createdUserDto;
    }


    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    public User login(User user) {
        Optional<User> dbUser = userRepository.findFirstByEmail(user.getEmail());
        if(dbUser.isPresent() && user.getPassword().equals(dbUser.get().getPassword())) {
            return dbUser.get();
        }
        return null;
    }

    public User getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

}
