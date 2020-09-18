package com.foodapp.genericfoodapp.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenUtil {
	
	private static final String ISSUER = "FOODAPP"; 
	private static final String SUBJECT = "LOGIN";
	private static final Long DURATION = 21600000L;
	
	
	
	public static void main(String args[]) {
		String jwt = createJWT("1", "issuer", "subject", 21600000L);
		System.out.println(jwt);
		Claims decodeJWT = decodeJWT(jwt);
		
		decodeJWT.getId();
		
	}
	
	public static String createToken (Integer id) {
		return createJWT(String.valueOf(id) , ISSUER, SUBJECT, DURATION);
	} 
	
	
	public static String createJWT( String id, String issuer, String subject, long ttlMillis) {
		  
	    //The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("SECRET_KEY");
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	            .setIssuedAt(now)
	            .setSubject(subject)
	            .setIssuer(issuer)
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary("SECRET_KEY"))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}
	 

}
