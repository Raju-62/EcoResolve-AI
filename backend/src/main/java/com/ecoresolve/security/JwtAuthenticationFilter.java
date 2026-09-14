package com.ecoresolve.security;
import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter; import java.io.IOException; import java.util.List;
@Component public class JwtAuthenticationFilter extends OncePerRequestFilter {
 private final JwtService jwt; private final org.springframework.security.core.userdetails.UserDetailsService users;
 public JwtAuthenticationFilter(JwtService jwt,org.springframework.security.core.userdetails.UserDetailsService users){this.jwt=jwt;this.users=users;}
 @Override protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
  String h=req.getHeader("Authorization"); if(h!=null&&h.startsWith("Bearer ")){String token=h.substring(7); if(jwt.isValid(token)){try{String email=jwt.extractEmail(token); var ud=users.loadUserByUsername(email); var auth=new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities()); SecurityContextHolder.getContext().setAuthentication(auth);}catch(Exception ignored){}}} chain.doFilter(req,res);
 }
}
