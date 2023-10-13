package tn.esprit.registermicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.registermicroservice.dto.MailRequest;
import tn.esprit.registermicroservice.dto.MailResponse;
import tn.esprit.registermicroservice.entities.PasswordResetToken;
import tn.esprit.registermicroservice.entities.User;
import tn.esprit.registermicroservice.services.EmailService;
import tn.esprit.registermicroservice.services.UserService;
import tn.esprit.registermicroservice.services.UserServiceImp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:4200")
public class registerController {
    @Autowired
    private UserService userService2;
    @Autowired
    private EmailService service;
    @Lazy
    @Autowired
    private UserServiceImp userService;
    @PostMapping("/user")
    public User registerUser(@RequestBody User user) {
        return userService2.registerUser(user);
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Generate and save a password reset token
        String resetToken = generateResetToken();
        userService.saveResetToken(user, resetToken);

        // Send an email with the reset token to the user
        String resetLink = "http://localhost:4200/resetPassword?token=" + resetToken;
        // String emailBody = createEmailBody(resetLink);

        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo(user.getEmail());
        mailRequest.setSubject("Password Reset Request");
       // mailRequest.setName(user.getNom());
        Map<String, Object> model = new HashMap<>();
       // model.put("Name", user.getNom());
        model.put("resetLink", resetLink);

        //EmailService.sendEmail(user.getEmail(), "Password Reset Request", model);

        MailResponse mailResponse =service.sendEmail(mailRequest, model);

        if (mailResponse.isStatus()) {
            return ResponseEntity.ok("Password reset instructions sent to your email.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send the email.");
        }

    }


    //    String emailBody = createEmailBody("email-template.ftl", model);

    //EmailService.sendEmail(user.getEmail(), "Password Reset Request", model);
    // return service.sendEmail(null, model)
    //  MailResponse mailResponse = EmailService.sendEmail(mailRequest, model);

    //service.sendEmail(user.getEmail(), "Password Reset Request", model);
    //     return ResponseEntity.ok("Password reset instructions sent to your email.");
    // }
    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        // Find the user associated with the token
        PasswordResetToken resetToken = userService.findResetToken(token);
        if (resetToken == null || resetToken.isExpired()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }

        // Reset the user's password
        userService.resetPassword(resetToken.getUser(), newPassword);

        // Delete the used reset token
        userService.deleteResetToken(resetToken);

        return ResponseEntity.ok("Password reset successfully");
    }




}

