package com.ecoresolve.security;
import com.ecoresolve.repository.UserRepository; import lombok.RequiredArgsConstructor; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service; import java.util.List;
@Service @RequiredArgsConstructor public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository repo;
 public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{var u=repo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found")); return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getPasswordHash(),List.of(new SimpleGrantedAuthority("ROLE_"+u.getRole().name())));}
}
