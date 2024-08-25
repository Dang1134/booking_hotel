package com.dang.booking.utils;

import com.dang.booking.security.DataSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtHelper {
    @Value("${jwt.private-key}")
    private String key;

    private long plusTime = 60 * 60 * 1000 * 12;
    public String generateToken(DataSecurity data) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));

        Date currentDate = new Date();
        long futureTime = currentDate.getTime() + plusTime;
        Date futureDate = new Date(futureTime);



        return Jwts.builder()
                .claim("data", data)
                .signWith(secretKey)
                .expiration(futureDate)
                .compact();
    }

    public DataSecurity decodeToken(String token){
//        String roleName = "";
//        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
//        try{
//            roleName = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
//        }catch(RuntimeException ex){
//            System.out.println("Error decode token : " + ex.getMessage());
//        }
//        return roleName;
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        Claims claims;
        try{
            claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        }catch(RuntimeException ex){
            System.out.println("Error decoding token: " + ex.getMessage());
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        DataSecurity dataSecurity = objectMapper.convertValue(claims.get("data"), DataSecurity.class);
        return dataSecurity;
    }
}
