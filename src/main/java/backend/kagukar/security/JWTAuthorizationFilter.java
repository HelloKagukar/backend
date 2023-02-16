package backend.kagukar.security;

import backend.kagukar.exception.AttemptAuthorizationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

import static backend.kagukar.security.SecurityConstants.*;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException, IOException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        } else {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
            try {
                usernamePasswordAuthenticationToken = getAuthentication(request);
            } catch (AttemptAuthorizationException exception) {
                exception.printStackTrace();
            }
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws AttemptAuthorizationException {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
            System.out.println(token);
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            throw new AttemptAuthorizationException("Failed authorization");
        }
        else {
            throw new AttemptAuthorizationException("User authorization failed.");
        }
    }
}
