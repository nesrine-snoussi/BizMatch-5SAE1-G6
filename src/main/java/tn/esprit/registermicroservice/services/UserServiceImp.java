package tn.esprit.registermicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.registermicroservice.entities.PasswordResetToken;
import tn.esprit.registermicroservice.entities.User;
import tn.esprit.registermicroservice.repositories.PasswordResetTokenRepository;
import tn.esprit.registermicroservice.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    // private final PasswordEncoder passwordEncoder;
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder; // Autowire Spring's PasswordEncoder



    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteResetToken(PasswordResetToken resetToken) {
        passwordResetTokenRepository.delete(resetToken);
    }


    public PasswordResetToken findResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token).orElse(null);
    }

    public void saveResetToken(User user, String resetToken) {
        // Create a new PasswordResetToken entity
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(resetToken);

        // Save the PasswordResetToken entity to the database
        passwordResetTokenRepository.save(passwordResetToken);
    }
    public void resetPassword(User user, String newPassword) {
        // Encode the new password before saving it
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
    public UserServiceImp(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User registerUser(User user) {
        // Encode the password before saving it to the database
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setConfirmpassword(null); // Clear the confirmPassword field

        return userRepository.save(user);
    }
}
