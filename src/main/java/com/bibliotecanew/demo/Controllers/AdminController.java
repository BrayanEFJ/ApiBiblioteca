// Paquete del controlador
package com.bibliotecanew.demo.Controllers;

// Importaciones necesarias para manejo de excepciones, DTOs, modelos, seguridad y servicios
import com.bibliotecanew.demo.Common.CustomException;
import com.bibliotecanew.demo.Dto.DtoInfoLogin;
import com.bibliotecanew.demo.Dto.DtoLogin;
import com.bibliotecanew.demo.Models.Admin;
import com.bibliotecanew.demo.Security.JwtBalancer;
import com.bibliotecanew.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Anotación que indica que este controlador devuelve datos JSON o XML
@RestController

// Permite el acceso desde cualquier origen (CORS)
@CrossOrigin("*")

// Define la URL base del controlador
@RequestMapping("/admin/")
public class AdminController {

    // Inyección de dependencia del servicio de administración
    @Autowired
    AdminService objserv;

    // Método POST para manejar el login del administrador en el endpoint /login
    //Recuerda que al ser un post tienes que pasarle un body de tipo "DtoLogin" a continuacion encontraras un ejemplo del body
//    {
//        "usuario": "admin12345",
//        "contraseña": "password12345",
//    }
    @PostMapping("login")
    public ResponseEntity<?> auth(@RequestBody DtoLogin dtolog){
        try {
            // Llama al servicio de autenticación pasando los datos de login
            DtoInfoLogin infoauth = objserv.auth(dtolog);
            
            // Si la autenticación es exitosa, devuelve los datos de login
            if(infoauth != null){
                return ResponseEntity.ok(infoauth);
            }
            else{
                // Si no se pudo generar el token correctamente, lanza una excepción personalizada
                throw new CustomException(HttpStatus.CONFLICT.value(), "El token no se genero correctamente");
            }
        } catch (CustomException e) {
            // Captura la excepción y devuelve un error con el código correspondiente
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
}
