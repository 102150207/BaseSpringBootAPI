package my.com.spring.boot.controller;

import my.com.spring.boot.entities.Role;
import my.com.spring.boot.entities.User;
import my.com.spring.boot.models.response.DataResponse;
import my.com.spring.boot.models.response.JwtAuthenticationResponse;
import my.com.spring.boot.models.user.UserLoginRequest;
import my.com.spring.boot.models.user.UserSignUpRequest;
import my.com.spring.boot.security.JwtTokenProvider;
import my.com.spring.boot.services.RoleService;
import my.com.spring.boot.services.UserService;
import my.com.spring.boot.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequest userLoginRequest){
        Boolean isAvailableUsername = userService.existsByUsername(userLoginRequest.getUsernameOrEmail());
        Boolean isAvailableEmail = userService.existsByEmail(userLoginRequest.getUsernameOrEmail());

        if(!(isAvailableUsername || isAvailableEmail)) {
            return ResponseEntity.badRequest().body(new DataResponse(false, HttpStatus.BAD_REQUEST.value(),Constant.USERNAME_OR_PASWORD_NO_EXIST));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUsernameOrEmail(),
                        userLoginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpRequest userSignUpRequest){

        if(userService.existsByUsername(userSignUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new DataResponse(false, Constant.USERNAME_USER_EXIST));
        }

        if(userService.existsByEmail(userSignUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new DataResponse(false, Constant.EMAIL_USER_EXIST));
        }

        // Creating user's account
        User user = new User(userSignUpRequest.getUsername(), userSignUpRequest.getPassword(),userSignUpRequest.getFullName(),
                userSignUpRequest.getEmail(), userSignUpRequest.getMobile());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.getRoleByName(Constant.USER);

        user.setRoles(Collections.singleton(userRole));

        User result = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new DataResponse(true, "User registered successfully"));
    }

}
