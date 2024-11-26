// Paquete del servicio
package com.bibliotecanew.demo.Services;

// Importaciones necesarias para excepciones, DTOs, modelos y repositorios
import com.bibliotecanew.demo.Common.CustomException;
import com.bibliotecanew.demo.Dto.DtoInfoLogin;
import com.bibliotecanew.demo.Dto.DtoLogin;
import com.bibliotecanew.demo.Models.Admin;
import com.bibliotecanew.demo.Repositories.AdminRepository;
import com.bibliotecanew.demo.Security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

// Anotación que indica que esta clase es un servicio de Spring
@Service
public class AdminService {

    // Inyección de dependencia del repositorio de administradores
    @Autowired
    AdminRepository objrep;

    // Inyección de dependencia para la utilidad de generación de tokens JWT
    @Autowired
    JwtTokenUtils objtokutils;

    // Método para autenticar un administrador
    public DtoInfoLogin auth(DtoLogin log){
        // Busca al administrador por su nombre de usuario
        Admin infoadmin = objrep.findByUsuario(log.getUsuario());
        if(infoadmin != null){
            // Verifica si la contraseña es correcta
            if(infoadmin.getContrasena().equals(log.getContraseña())){
                // Si la contraseña es correcta, genera un token JWT
                String token = objtokutils.GenerateToken(log.getUsuario());
                DtoInfoLogin info = new DtoInfoLogin(); // Crea un nuevo objeto DTO para la información de inicio de sesión
                info.setToken(token); // Establece el token en el DTO
                info.setAdmininfo(log); // Establece la información del administrador en el DTO
                return info; // Devuelve el DTO con la información del administrador y el token
            }
            else{
                // Si la contraseña es incorrecta, lanza una excepción personalizada
                throw new CustomException(HttpStatus.CONFLICT.value(), "La contraseña es incorrecta");
            }
        }
        else{
            // Si no se encuentra al administrador, lanza una excepción personalizada
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "admin no encontrado");
        }
    }
}
