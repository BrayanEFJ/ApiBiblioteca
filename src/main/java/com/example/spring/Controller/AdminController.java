/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.Controller;

import ch.qos.logback.core.joran.action.ActionUtil;
import com.example.spring.CustomException.CustomException;
import com.example.spring.Dto.DtoInfoLogin;
import com.example.spring.Dto.Dtologin;
import com.example.spring.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author l
 */
@RestController
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService objadmserv;

    @Autowired
    private ConfigurableApplicationContext context;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Dtologin login) {
        try {
            DtoInfoLogin token = objadmserv.login(login);
            if (token != null) {
                return ResponseEntity.ok(token);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "Token no válido");
            }
            
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatus()).body(e.toString());
        }

    }

    @GetMapping("/shutdown")
    public String shutdown() {
        SpringApplication.exit(context, () -> 0);
        return "La aplicación se está deteniendo...";
    }

}
