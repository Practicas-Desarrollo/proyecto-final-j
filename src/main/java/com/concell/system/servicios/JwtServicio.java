package com.concell.system.servicios;

import com.concell.system.modelos.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtServicio {

  @Value("${spring.application.security.jwt.secret-key}")
  private String secretKey;

  @Value("${spring.application.security.jwt.expiration}")
  private long tiempoExpiracion;

  public String crearToken(Usuario usuario) {
    Map<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("roles", usuario.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));

    return generarToken(extraClaims, usuario);
  }

  public String getEmailFromToken(String token) {
    return obtenerClaim(token, Claims::getSubject);
  }

  public List<String> obtenerRolesFromToken(String token) {
    Claims claims = obtenerClaims(token);
    return claims.get("roles", List.class);
  }

  public boolean esTokenValido(String token, UserDetails userDetails) {
    String email = getEmailFromToken(token);
    List<String> tokenRoles = obtenerRolesFromToken(token);
    List<String> userRoles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return email.equals(userDetails.getUsername())
            && !esTokenExpirado(token)
            && tokenRoles.containsAll(userRoles);
  }

  private String generarToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))
            .signWith(obtenerKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  private Key obtenerKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Claims obtenerClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(obtenerKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  public boolean esTokenExpirado(String token) {
    return obtenerExpiracion(token).before(new Date());
  }

  public Date obtenerExpiracion(String token) {
    return obtenerClaim(token, Claims::getExpiration);
  }

  public <T> T obtenerClaim(String token, Function<Claims, T> claimsResolver) {
    Claims claims = obtenerClaims(token);
    return claimsResolver.apply(claims);
  }
}
