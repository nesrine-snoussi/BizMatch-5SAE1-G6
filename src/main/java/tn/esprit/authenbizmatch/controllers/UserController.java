package tn.esprit.authenbizmatch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import tn.esprit.authenbizmatch.entities.AuthRequest;
import tn.esprit.authenbizmatch.entities.AuthResponse;
import tn.esprit.authenbizmatch.entities.User;
import tn.esprit.authenbizmatch.repositories.UserRepository;
import tn.esprit.authenbizmatch.security.JwtUtil;
import tn.esprit.authenbizmatch.services.UserServiceImp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(String.valueOf(userDetails));

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        //return new ResponseEntity<>("ca marche!", HttpStatus.OK);
        return "redirect:/login?logout"; // Rediriger avec un paramètre indiquant la déconnexion
    }

    @GetMapping("/test")
    public String test (@RequestBody String username) {
        String test = "Hello" + username;
        return test ;
    }


}
