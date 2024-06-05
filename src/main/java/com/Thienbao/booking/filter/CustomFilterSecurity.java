package com.Thienbao.booking.filter;

import com.Thienbao.booking.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomFilterSecurity extends OncePerRequestFilter {

    @Autowired
    JwtHelper jwtHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorValue =  request.getHeader("Authorization");

        if(authorValue != null && authorValue.startsWith("Bearer ")){
            String token = authorValue.substring(7);
            if(!token.isEmpty()){
                String roleName  =  jwtHelper.decodeToken(token);
              if (!roleName.isEmpty()){
                  List<GrantedAuthority> roleList = new ArrayList<>();
                  SimpleGrantedAuthority role = new SimpleGrantedAuthority(roleName);
                  roleList.add(role);

                  UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken("",
                          "",roleList);
                  // Tạo chứng thực
                  SecurityContext context = SecurityContextHolder.getContext();
                  context.setAuthentication(authenToken);
              }
            }
        }
        filterChain.doFilter(request,response);
    }
}
