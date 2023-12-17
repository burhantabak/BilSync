package tr.edu.bilkent.bilsync.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.service.TokenService;
import tr.edu.bilkent.bilsync.service.UserInfoService;

import java.io.IOException;

/**
 * Filter class responsible for JWT authentication.
 * Extends OncePerRequestFilter to ensure it is only executed once per request.
 */
@Service
public class JWTAuthFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserInfoService userInfoService;

    /**
     * Constructor for JWTAuthFilter.
     *
     * @param tokenService     The service for handling JWT tokens.
     * @param userInfoService  The service for retrieving user information.
     */
    public JWTAuthFilter(TokenService tokenService, UserInfoService userInfoService) {
        this.tokenService = tokenService;
        this.userInfoService = userInfoService;
    }

    /**
     * Performs the JWT authentication process.
     *
     * @param request     The HTTP servlet request.
     * @param response    The HTTP servlet response.
     * @param filterChain The filter chain.
     * @throws ServletException If a servlet exception occurs.
     * @throws IOException      If an I/O exception occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        System.out.println(request.toString());
        final String jwt;
        final String userEmail;

        // Check if the Authorization header is present and starts with "Bearer "
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        // Extract the JWT token
        jwt = authHeader.substring(7);
        userEmail = tokenService.extractUsername(jwt);

        // Check if the user is not already authenticated
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userInfoService.loadUserByUsername(userEmail);

            // For admin user
            if(userEmail.equals("admin@bilkent.edu.tr")){
                userDetails = new UserEntity("admin@bilkent.edu.tr","admin","admin");
            }

            // If user details are retrieved, validate the token
            if(userDetails != null){
                if(tokenService.validateToken(jwt,userDetails)){
                    // Set authentication token in SecurityContextHolder
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Continue with the filter chain
            filterChain.doFilter(request,response);
        }
    }

}
