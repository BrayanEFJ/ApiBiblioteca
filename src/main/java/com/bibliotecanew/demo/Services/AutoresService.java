// Paquete del servicio
package com.bibliotecanew.demo.Services;

// Importaciones necesarias para excepciones, modelos y repositorios
import com.bibliotecanew.demo.Common.CustomException;
import com.bibliotecanew.demo.Models.Autores;
import com.bibliotecanew.demo.Repositories.AutoresRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

// Anotación que indica que esta clase es un servicio de Spring
@Service
public class AutoresService {

    // Inyección de dependencia del repositorio de autores
    @Autowired
    private AutoresRepository objrepaut;

    // Método para obtener la lista de todos los autores
    public List<Autores> traerautores(){    
        return objrepaut.findAll(); // Llama al método del repositorio que obtiene todos los autores
    }

    // Método para obtener un autor específico basado en su documento
    public Autores traerautor(String Documento){
        Autores infoautor = objrepaut.findByDocumento(Documento); // Busca el autor por su documento
        if(infoautor != null){
            return infoautor; // Si el autor existe, se devuelve
        }
        else{
            // Si no se encuentra, lanza una excepción personalizada
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El autor no fue encontrado");
        }
    }

    // Método para crear un nuevo autor
    public String crearautor(Autores aut){
        // Verifica si ya existe un autor con el mismo documento
        Autores infoaut = objrepaut.findByDocumento(aut.getDocumento());
        if(infoaut == null){
            objrepaut.save(aut); // Si no existe, guarda el nuevo autor
            return "El autor se guardo exitosamente"; // Mensaje de éxito
        }
        else{
            // Si ya existe, lanza una excepción personalizada
            throw new CustomException(HttpStatus.CONFLICT.value(), "El autor ya existe en la base de datos");
        } 
    }

    // Método para actualizar un autor existente
    public String actualizarautor(Autores aut){
        // Busca el autor por su documento
        Autores infoaut = objrepaut.findByDocumento(aut.getDocumento());
        if(infoaut != null){
            // Si el autor existe, actualiza sus datos
            infoaut.setNombre(aut.getNombre());
            infoaut.setBiografia(aut.getBiografia());
            infoaut.setActivo(aut.getActivo());
            objrepaut.save(infoaut); // Guarda los cambios
            return "El autor se actualizo exitosamente"; // Mensaje de éxito
        }
        else{
            // Si no se encuentra, lanza una excepción personalizada
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El autor no fue encontrado para actualizar");
        } 
    }

    // Método para eliminar un autor basado en su documento
    public String eliminarautor(String Documento){
        // Busca el autor por su documento y lo guarda en el objeto del modelo llamado infoaut
        Autores infoaut = objrepaut.findByDocumento(Documento);
        if(infoaut != null){
            objrepaut.deleteById(infoaut.getAutorId()); // Si el autor existe, lo elimina
            return "El autor se elimino correctamente"; // Mensaje de éxito
        }
        else{
            // Si no se encuentra, lanza una excepción personalizada
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El autor no fue encontrado para eliminar");
        } 
    }
}
