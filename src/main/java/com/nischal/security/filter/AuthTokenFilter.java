package com.nischal.security.filter;

import com.nischal.security.constant.SecurityConstants;
import com.nischal.security.exception.AuthenticationFailedException;
import com.nischal.security.util.JwtUtil;
import com.nischal.security.util.LogUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthTokenFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null) {
            LogUtil.exception("authorization header is null");
            throw new AuthenticationFailedException();
        }
        if (authHeader.isBlank()) {
            LogUtil.exception("authorization header is blank");
            throw new AuthenticationFailedException();
        }
        if (authHeader.contains(SecurityConstants.JWT_PREFIX)) {
            String token = authHeader.replace("Bearer", "");
            if (token.trim().isEmpty()) {
                LogUtil.exception("authorization token is empty");
                throw new AuthenticationFailedException();
            }
            if (jwtUtil.validateToken(token)) {

                try {
                    Jws<Claims> data = jwtUtil.getData(token);
                    Claims body = data.getBody();
                    String email = body.get("email", String.class);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } catch (Exception exception){
                    LogUtil.exception(exception.getMessage());
                    throw new AuthenticationFailedException();
                }
            }
        } else {
            LogUtil.exception("authorization token prefix is not present ");
            throw new AuthenticationFailedException();
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        if (path.equals("/login")) {
            return true;
        }
        return false;
    }
}
