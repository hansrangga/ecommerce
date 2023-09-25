package com.ecomm.genshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.genshop.DTO.AccountDTO;
import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.service.AccountService;

@RestController
@RequestMapping("/account")
public class AuthController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountDTO.getEmail(),
                            accountDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        if (accountService.findByUserName(account.getUserName()).isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        if (accountService.findByEmail(account.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
