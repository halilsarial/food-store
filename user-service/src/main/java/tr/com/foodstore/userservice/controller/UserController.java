package tr.com.foodstore.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import tr.com.foodstore.userservice.authconfig.JwtTokenUtil;
import tr.com.foodstore.userservice.model.domain.ConfirmationToken;
import tr.com.foodstore.userservice.model.domain.User;
import tr.com.foodstore.userservice.model.dto.userservice.AuthenticationDto;
import tr.com.foodstore.userservice.model.dto.userservice.UserDto;
import tr.com.foodstore.userservice.service.ConfirmationTokenService;
import tr.com.foodstore.userservice.service.EmailSenderService;
import tr.com.foodstore.userservice.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/users/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        userService.createUser(userService.transformUserFromDto(userDto));
        return ResponseEntity.ok().body("The user created successfully!");
    }

    @PostMapping(value = "/users/signIn")
    public ResponseEntity<?> signInWithUserInformation(@RequestBody AuthenticationDto authenticationDto, HttpServletRequest request) throws Exception {
        authenticate(authenticationDto.getUserName(), authenticationDto.getPassword());
        User user = userService.getUserByUserName(authenticationDto.getUserName());

        ConfirmationToken confirmationToken = confirmationTokenService.produceConfirmationToken(user);
        confirmationTokenService.createConfirmationToken(confirmationToken);

        emailSenderService.sendEmail("Complete Registration!",
                "To confirm your account, please click here : " + request.getRequestURL().toString() + "?token=" + confirmationToken.getConfirmationToken(),
                user.getEmail()
        );
        return new ResponseEntity<>("A verification email has been sent to your email address!", HttpStatus.OK);
    }

    @GetMapping(value = "/users/signIn")
    public ResponseEntity<?> verifyToken(@RequestParam String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);
        if (confirmationToken != null && confirmationToken.getUser() != null) {
            User user = confirmationToken.getUser();
            HttpHeaders headers = new HttpHeaders();
            headers.add("jwt-access-token", jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(user.getUserName())));
            return new ResponseEntity<>("Login Successfully! Welcome " + user.getName() + " " + user.getSurname(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>("The link is invalid or broken!", HttpStatus.BAD_REQUEST);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
