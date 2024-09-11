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
        System.out.println("jwt: "+jwt);
        logger.info("Role.ROLE_ADMIN: "+ Role.ROLE_ADMIN + " \nRole.ROLE_ADMIN.getAuthority():" + Role.ROLE_ADMIN.getAuthority()+ " \nRole.ROLE_ADMIN.getRole()"+ Role.ROLE_ADMIN.getRole()+" \nRole.ROLE_ADMIN.name():"+Role.ROLE_ADMIN.name());
        if (jwt != null){
            System.out.println("1");
            if (getSignKey() != null){
                System.out.println("2");

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

                String username = String.valueOf(claims.get("username"));
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                List<String> authorities = (List<String>) claims.get("authorities");
                for (int i = 0;i< authorities.size();i++){
                    if (authorities.get(i).equals("ADMIN")){
                        grantedAuthorities.add(Role.ROLE_ADMIN);
                    } else if (authorities.get(i).equals("USER")) {
                        grantedAuthorities.add(Role.ROLE_USER);
                    }
                }

                logger.info("username: "+username+" auth: "+grantedAuthorities);
                logger.info("authorixation:"+authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor("9wTq6NjcJwE3BcXt9wTq6NjcJwE3BcXt".getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        logger.info("diğerleri");
        boolean x =  request.getServletPath().equals("/auth/login") || request.getHeader("Authorization")==null;
        logger.info("x: "+ x);
        return x;
    }
}
