// Paquete del repositorio
package com.bibliotecanew.demo.Repositories;

// Importaciones necesarias para el modelo Admin y las funcionalidades de JPA
import com.bibliotecanew.demo.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz base para la persistencia de datos
import org.springframework.data.jpa.repository.Query; // Para definir consultas personalizadas
import org.springframework.stereotype.Repository; // Anotación que indica que esta interfaz es un repositorio

/**
 * Esta interfaz AdminRepository es responsable de proporcionar operaciones de acceso a datos
 * para la entidad Admin, extendiendo JpaRepository para obtener funcionalidades CRUD básicas.
 * 
 * @author l
 */
@Repository // Anotación que indica que esta interfaz es un componente de acceso a datos en el contexto de Spring
public interface AdminRepository extends JpaRepository<Admin, Integer> { // Extiende JpaRepository para operaciones CRUD

    /**
     * Esta consulta personalizada busca un administrador en la base de datos
     * utilizando el nombre de usuario proporcionado.
     * 
     * @param usuario El nombre de usuario del administrador que se busca.
     * @return Un objeto Admin que representa al administrador encontrado o null si no existe.
     */
    @Query(value = "SELECT * from admin where usuario = ?1", nativeQuery = true) // Consulta SQL nativa para buscar un administrador por su usuario
    Admin traeradmin(String usuario); // Método que usa la consulta definida anteriormente

    /**
     * Este método busca un administrador por su nombre de usuario utilizando 
     * la convención de nombres de Spring Data JPA.
     * 
     * @param usuario El nombre de usuario del administrador que se busca.
     * @return Un objeto Admin que representa al administrador encontrado o null si no existe.
     */
    Admin findByUsuario(String usuario); // Método que aprovecha la convención de Spring Data JPA
}
