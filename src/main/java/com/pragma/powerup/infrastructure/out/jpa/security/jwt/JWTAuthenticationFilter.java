package com.pragma.powerup.infrastructure.out.jpa.security.jwt;

import com.pragma.powerup.infrastructure.out.jpa.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String email;

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        email = jwtService.getEmailFromToken(token);

        if(email != null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if(jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                if (checkUserRole(userDetails, request)) {
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean checkUserRole(UserDetails userDetails, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/user/") || requestURI.equals("/restaurant/")) {
            return userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("admin"));
          } else if (requestURI.equals("/plate/") || requestURI.equals("/plate/changeState/") || requestURI.equals("/user/employee/")) {
                return userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("owner"));
            } else if (requestURI.equals("/user/order/")) {
                    return userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("client"));

        }
        return false;
    }

    private String getTokenFromRequest(HttpServletRequest request) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){

            return authHeader.substring(7);

        }

        return null;
    }
}
