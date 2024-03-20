package com.example.beta.Ultis;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtServices {
    private static String SERCET_KEY = "p7lrcccy87yemz2czal347tyhi3lod7m7dldt5fgeluirod9oplwdur8j3hf4l4d";
    private static long expireInMs = 86400000;

    private static final long refreshExpiration = 604800000;


    public String extractUsername(String token) {
        return extractClam(token, Claims::getSubject);
    }
    public <T> T extractClam(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClams(token);
        return claimsTFunction.apply(claims);
    }

    public Claims extractAllClams(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String generateRefreshToken(Map<String, Object> extractClam,
            UserCustomDetails userCustomDetails
    ) {
        return buildToken(extractClam,userCustomDetails);
    }

    public String generateRefreshToken(UserCustomDetails userCustomDetail) {
        return generateToken(new HashMap<>(), userCustomDetail);
    }

    public String generateToken(UserCustomDetails userCustomDetail) {
        return generateToken(new HashMap<>(), userCustomDetail);
    }
    public String generateToken(Map<String, Object> extractClam, UserCustomDetails userCustomDetail) {
        return buildToken(extractClam, userCustomDetail);
    }

    public String buildToken(Map<String, Object> extractClam, UserCustomDetails userCustomDetail) {
        return Jwts
                .builder()
                .setClaims(extractClam)
                .claim("roleName", userCustomDetail.getUser().getRole().getRoleName())
                .setSubject(userCustomDetail.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireInMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClam(token, Claims::getExpiration);
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SERCET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
