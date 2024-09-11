package org.example.justadminnouserv1.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.justadminnouserv1.entity.Role;
import org.example.justadminnouserv1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt =request.getHeader("Authorization").substring(7);
        if (jwt != null){
            if (getSignKey() != null){
                Claims claims = null;
                try {
                    claims = Jwts.parserBuilder()
                            .setSigningKey(getSignKey()) // Secret key eklenir
                            .build() // JwtParser oluşturulur
                            .parseClaimsJws(jwt) // Token doğrulanır
                            .getBody(); // Token içeriği alınır
                }catch (Exception e){
                    logger.info(e.getMessage());
                }

                if (claims != null){
                    String username = String.valueOf(claims.get("username"));
                    List<String> authorities = (List<String>) claims.get("authorities");
                /*
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

                for (int i = 0;i< authorities.size();i++){
                    if (authorities.get(i).equals("ADMIN")){
                        grantedAuthorities.add(Role.ROLE_ADMIN);
                    } else if (authorities.get(i).equals("USER")) {
                        grantedAuthorities.add(Role.ROLE_USER);
                    }
                }*/
                    List<GrantedAuthority> grantedAuthorities = authorities.stream()
                            .map(Role::fromString) // String'den Role'e dönüştür
                            .collect(Collectors.toList());

                    logger.info("username: "+username+" auth: "+grantedAuthorities);
                    logger.info("authorixation:"+authorities);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,grantedAuthorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else {
                    throw new RuntimeException("JWT not parsed -JWTTokenValidatorFilter");
                }
            }
        }
        filterChain.doFilter(request,response);
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor("9wTq6NjcJwE3BcXt9wTq6NjcJwE3BcXt".getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/auth/login") || request.getHeader("Authorization")==null;
    }
}
