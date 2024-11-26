/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotecanew.demo.Security;

import com.bibliotecanew.demo.Common.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 *
 * @author l
 */

@Component
public class JwtBalancer {
    
    @Autowired
    JwtTokenUtils objtokenu;
    
 
    public boolean Authjwt(String Token){
        if(Token == null || Token.equals("")){
            throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token no puede ser nulo");
        }
        else{
            try {
                String RealToken = Token.substring(7);
                String Tokencheck = objtokenu.ValidateToken(RealToken);
                if(Tokencheck.equalsIgnoreCase("Valido")){
                    return true;
                }
                else{
                    throw  new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token no esta autorizado" + Tokencheck);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw  new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token tiene un formato incorrecto");
            }
        }
        
    }
    
    
    
    
}
