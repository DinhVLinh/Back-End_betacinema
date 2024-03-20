package com.example.beta.Ultis;

import com.example.beta.Services.Implements.UserDetailsImpServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenFilter extends OncePerRequestFilter {
    private final JwtServices jwtService;
    private final UserDetailsImpServices userDetailService;
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authen = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if(authen == null || !authen.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authen.substring(7);
        username = jwtService.extractUsername(jwt);
         if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserCustomDetails userCustomDetail = (UserCustomDetails) this.userDetailService.loadUserByUsername(username);
            if(jwtService.isTokenValid(jwt, userCustomDetail)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userCustomDetail, null, userCustomDetail.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
