// Paquete que contiene el controlador
package com.bibliotecanew.demo.Controllers;

// Importaciones necesarias para manejar excepciones personalizadas, DTOs, modelos, seguridad y servicios
import com.bibliotecanew.demo.Common.CustomException;
import com.bibliotecanew.demo.Dto.DtoLogin;
import com.bibliotecanew.demo.Models.Autores;
import com.bibliotecanew.demo.Security.JwtBalancer;
import com.bibliotecanew.demo.Services.AutoresService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Anotación que indica que es un controlador que retorna directamente datos JSON o XML, en vez de vistas
@RestController

// Permite el acceso desde cualquier origen (CORS)
@CrossOrigin("*")

// Define la URL base del controlador
@RequestMapping("/autores/")
public class AutoresController {

    // Inyección de dependencia del servicio que maneja la lógica de los autores
    @Autowired
    private AutoresService objservaut;

    // Inyección de dependencia para manejar y verificar los tokens JWT
    @Autowired
    private JwtBalancer objbal;
    

    // Método GET para traer todos los autores desde el endpoint /traerautores
    //ejemplo del endpoint localhost:8081/autores/traerautores
    @GetMapping("traerautores")
    public ResponseEntity<?> traerautores(@RequestHeader(value = "Authorization") String Token) {
        try {
            // Verifica si el token es válido
            boolean response = objbal.Authjwt(Token);
            if (response) {
                // Si el token es válido, trae la lista de autores desde el servicio
                List<Autores> autlist = objservaut.traerautores();
                // Devuelve la lista de autores con un código de respuesta 200 (OK)
                return ResponseEntity.ok(autlist);
            } else {
                // Si el token no es válido, lanza una excepción personalizada
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }
        } catch (CustomException e) {
            // Captura la excepción y devuelve un error con el código correspondiente
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }

    // Método GET para traer un autor específico según el documento desde el endpoint /traerautor
    //ejemplo del endpoint localhost:8081/autores/traerautor?Documeto=12324
    @GetMapping("traerautor")
    public ResponseEntity<?> traerautor(@RequestParam String Documento, @RequestHeader(value = "Authorization") String Token) {
        try {
            // Verifica si el token es válido
            boolean response = objbal.Authjwt(Token);
            if (response) {
                // Si el token es válido, trae el autor correspondiente desde el servicio
                Autores aut = objservaut.traerautor(Documento);
                // Devuelve el autor con un código de respuesta 200 (OK)
                return ResponseEntity.ok(aut);
            } else {
                // Si el token no es válido, lanza una excepción personalizada
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }
        } catch (CustomException e) {
            // Captura la excepción y devuelve un error con el código correspondiente
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }

    //Recuerda que al ser un post tienes que pasarle un body de tipo "Autores" a continuacion encontraras un ejemplo del body
//    {
//        "nombre": "Gabriel García Márquez",
//        "biografia": "Gabriel García Márquez fue un novelista, cuentista y periodista colombiano, considerado uno de los autores más importantes del siglo XX. Ganador del Premio Nobel de Literatura en 1982.",
//        "documento": "123456789",
//        "activo": true
//    }
    // Método POST para crear un nuevo autor desde el endpoint /crearautor
    //ejemplo del endpoint localhost:8081/autores/crearautor
    @PostMapping("crearautor")
    public ResponseEntity<?> crearautor(@RequestBody Autores aut, @RequestHeader(value = "Authorization") String Token) {
        try {
            // Verifica si el token es válido
            boolean response = objbal.Authjwt(Token);
            if (response) {
                // Si el token es válido, crea el autor utilizando el servicio
                String resserv = objservaut.crearautor(aut);
                // Devuelve el resultado de la creación con un código de respuesta 200 (OK)
                return ResponseEntity.ok(resserv);
            } else {
                // Si el token no es válido, lanza una excepción personalizada
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }
        } catch (CustomException e) {
            // Captura la excepción y devuelve un error con el código correspondiente
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
    
    //Recuerda que al ser un put tienes que pasarle un body de tipo "Autores" a continuacion encontraras un ejemplo del body
//    {
//        "nombre": "Gabriel García Márquez",
//        "biografia": "Gabriel García Márquez fue un novelista, cuentista y periodista colombiano, considerado uno de los autores más importantes del siglo XX. Ganador del Premio Nobel de Literatura en 1982.",
//        "documento": "123456789",
//        "activo": true
//    }
    // Método PUT para actualizar un autor desde el endpoint /actualizarautor
    //ejemplo del endpoint localhost:8081/autores/actualizarautor
    @PutMapping("actualizarautor")
    public ResponseEntity<?> actualizarautor(@RequestBody Autores aut, @RequestHeader(value = "Authorization") String Token) {
        try {
            // Verifica si el token es válido
            boolean response = objbal.Authjwt(Token);
            if (response) {
                // Si el token es válido, actualiza el autor utilizando el servicio
                String actserv = objservaut.actualizarautor(aut);
                // Devuelve el resultado de la actualización con un código de respuesta 200 (OK)
                return ResponseEntity.ok(actserv);
            } else {
                // Si el token no es válido, lanza una excepción personalizada
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }
        } catch (CustomException e) {
            // Captura la excepción y devuelve un error con el código correspondiente
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
    
    
    // Método DELETE para eliminar un autor según su documento desde el endpoint /eliminarautor
    //ejemplo del endpoint localhost:8081/autores/eliminarautor?Documento=123445
    @DeleteMapping("eliminarautor")
    public ResponseEntity<?> eliminar(@RequestParam String Documento, @RequestHeader(value = "Authorization") String Token) {
        try {
            // Verifica si el token es válido
            boolean response = objbal.Authjwt(Token);
            if (response) {
                // Si el token es válido, elimina el autor utilizando el servicio
                String eliserv = objservaut.eliminarautor(Documento);
                // Devuelve el resultado de la eliminación con un código de respuesta 200 (OK)
                return ResponseEntity.ok(eliserv);
            } else {
                // Si el token no es válido, lanza una excepción personalizada
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }
        } catch (CustomException e) {
            // Captura la excepción y devuelve un error con el código correspondiente
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
}
