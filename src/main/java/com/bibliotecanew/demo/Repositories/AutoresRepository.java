// Paquete del repositorio
package com.bibliotecanew.demo.Repositories;

// Importaciones necesarias para el modelo Autores y las funcionalidades de JPA
import com.bibliotecanew.demo.Models.Autores;
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz base para la persistencia de datos
import org.springframework.data.jpa.repository.Query; // Para definir consultas personalizadas
import org.springframework.stereotype.Repository; // Anotación que indica que esta interfaz es un repositorio

/**
 * Esta interfaz AutoresRepository es responsable de proporcionar operaciones de acceso a datos
 * para la entidad Autores, extendiendo JpaRepository para obtener funcionalidades CRUD básicas.
 * 
 * @author l
 */
@Repository // Anotación que indica que esta interfaz es un componente de acceso a datos en el contexto de Spring
public interface AutoresRepository extends JpaRepository<Autores, Integer> { // Extiende JpaRepository para operaciones CRUD

    /**
     * Esta consulta personalizada busca un autor en la base de datos
     * utilizando el documento proporcionado.
     * 
     * @param Documento El documento del autor que se busca.
     * @return Un objeto Autores que representa al autor encontrado o null si no existe.
     */
    @Query(value = "select * from autores where Documento = ?1", nativeQuery = true) // Consulta SQL nativa para buscar un autor por su documento
    Autores traerautorunico(String Documento); // Método que usa la consulta definida anteriormente

    /**
     * Este método busca un autor por su documento utilizando 
     * la convención de nombres de Spring Data JPA.
     * 
     * @param Documento El documento del autor que se busca.
     * @return Un objeto Autores que representa al autor encontrado o null si no existe.
     */
    Autores findByDocumento(String Documento); // Método que aprovecha la convención de Spring Data JPA
}
