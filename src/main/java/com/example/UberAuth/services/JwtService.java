package com.example.UberAuth.services;

import com.example.UberAuth.Enums.BookingStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


@Service
public class JwtService implements CommandLineRunner {
    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(Map<String, Object> payload, String email){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000);
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claims(payload)
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();

    }

    private Claims extractAllPayload(String token ){
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolve){
        final Claims claims = extractAllPayload(token);
        return claimsResolve.apply(claims);
    }

    private Boolean isTokenExpired(String token){
//        return extractClaim(token, Claims::getExpiration); // it return date not boolean value or it don't compare value
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

//    private String extractUserName(String token){
//        return extractClaim(token, Claims::getSubject);
//    }

    private String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Boolean validateToken(String token, String email){
        final String userEmailFetchedFromToken = extractEmail(token);
        return (userEmailFetchedFromToken.equals(email)) && !isTokenExpired(token);
    }

    private Object extractPayload(String token, String payloadKey){
        Claims claims = extractAllPayload(token);
        return (Object) claims.get(payloadKey);
    }


    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }




    @Override
    public void run(String... args) throws Exception {
        System.out.println("+++++++++++");
        Map<String, Object> mp = new HashMap<>();
        mp.put("name", "abhishek");
        mp.put("phoneNumber", "123456789");
        mp.put("email", "Abhi@gmail.com");

        String result = createToken(mp, "Abhi");
        System.out.println("Token: "+result);
        System.out.println(extractPayload(result, "email").toString());

    }
}
