/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class JwtService {
    public static final String SECRET_KEY = "11111111111111111111111111111111";
    public static final byte[] SHARED_SECRET_KEY = SECRET_KEY.getBytes();
    public static final int EXPIRE_TIME = 86400000;
    
    public String generateToken(String username) throws JOSEException{
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        
        JWTClaimsSet claimSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME)).build();
        
        Payload payload = new Payload(claimSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        
        jwsObject.sign(new MACSigner(SHARED_SECRET_KEY));
        return jwsObject.serialize();
    }
}
