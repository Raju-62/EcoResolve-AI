package com.ecoresolve.dto;

import com.ecoresolve.enums.Role;
import jakarta.validation.constraints.*;

public final class AuthDtos {
 private AuthDtos(){}
 public record RegisterRequest(@NotBlank @Size(max=100) String name,@NotBlank @Email String email,@NotBlank @Size(min=8,max=100) String password){}
 public record LoginRequest(@NotBlank @Email String email,@NotBlank String password){}
 public record UserView(Long id,String name,String email,Role role){}
 public record AuthResponse(String token,UserView user){}
}
