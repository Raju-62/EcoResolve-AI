package com.ecoresolve.service;
import com.ecoresolve.dto.AuthDtos.*; import com.ecoresolve.entity.User; import com.ecoresolve.enums.Role; import com.ecoresolve.repository.UserRepository; import com.ecoresolve.security.JwtService; import lombok.RequiredArgsConstructor; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service @RequiredArgsConstructor public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 @Transactional public void register(RegisterRequest r){String email=r.email().trim().toLowerCase(); if(users.existsByEmail(email)) throw new IllegalArgumentException("Email is already registered"); User u=new User();u.setName(r.name().trim());u.setEmail(email);u.setPasswordHash(encoder.encode(r.password()));u.setRole(Role.STUDENT);users.save(u);}
 @Transactional(readOnly=true) public AuthResponse login(LoginRequest r){User u=users.findByEmail(r.email().trim().toLowerCase()).orElseThrow(()->new IllegalArgumentException("Invalid email or password")); if(!encoder.matches(r.password(),u.getPasswordHash())) throw new IllegalArgumentException("Invalid email or password"); return new AuthResponse(jwt.generate(u),new UserView(u.getId(),u.getName(),u.getEmail(),u.getRole()));}
}
