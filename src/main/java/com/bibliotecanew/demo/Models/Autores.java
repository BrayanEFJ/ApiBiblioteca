// Paquete del modelo
package com.bibliotecanew.demo.Models;

// Importaciones necesarias para la serialización, anotaciones de JPA y Lombok
import com.fasterxml.jackson.annotation.JsonIgnore; // Para ignorar campos en la serialización JSON
import com.fasterxml.jackson.annotation.JsonManagedReference; // Para manejar referencias entre entidades
import java.io.Serializable; // Para hacer que la clase sea serializable
import java.util.List; // Para trabajar con listas
import jakarta.persistence.Basic; // Para definir propiedades básicas de JPA
import jakarta.persistence.Column; // Para mapear columnas de la base de datos
import jakarta.persistence.Entity; // Para marcar la clase como entidad JPA
import jakarta.persistence.GeneratedValue; // Para definir estrategias de generación de ID
import jakarta.persistence.GenerationType; // Estrategias de generación de ID
import jakarta.persistence.Id; // Para marcar la propiedad como ID
import jakarta.persistence.Lob; // Para definir campos de tipo LOB (grande)
import jakarta.persistence.NamedQueries; // Para definir consultas nombradas
import jakarta.persistence.NamedQuery; // Para definir una consulta nombrada
import jakarta.persistence.OneToMany; // Para definir relaciones uno a muchos
import jakarta.persistence.Table; // Para definir la tabla de la base de datos
import lombok.Data; // Para generar automáticamente métodos getters, setters y otros

/**
 * Esta clase representa el modelo de datos para los autores en la aplicación.
 * Se asigna a la tabla "autores" en la base de datos.
 * 
 * @author l
 */
@Entity // Marca esta clase como una entidad JPA que se puede mapear a la base de datos
@Table(name = "autores") // Especifica que esta entidad está mapeada a la tabla "autores"
@Data // Genera automáticamente métodos getters, setters, toString, hashCode y equals
public class Autores implements Serializable { // Implementa Serializable para permitir la serialización

    private static final long serialVersionUID = 1L; // Versión de la clase para serialización

    @Id // Marca este campo como la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación de ID
    @Basic(optional = false) // Indica que este campo no puede ser nulo
    @Column(name = "autor_id") // Mapea este campo a la columna "autor_id" en la tabla
    private Integer autorId; // ID único para cada autor

    @Column(name = "nombre") // Mapea este campo a la columna "nombre" en la tabla
    private String nombre; // Nombre del autor

    @Lob // Indica que este campo puede contener un gran objeto (grande)
    @Column(name = "biografia") // Mapea este campo a la columna "biografia" en la tabla
    private String biografia; // Biografía del autor

    @Column(name = "Documento") // Mapea este campo a la columna "Documento" en la tabla
    private String documento; // Documento de identificación del autor (por ejemplo, DNI)

    @Column(name = "Activo") // Mapea este campo a la columna "Activo" en la tabla
    private Boolean activo; // Indica si el autor está activo o no

    // Relación uno a muchos con la entidad Libros
    @OneToMany(mappedBy = "autorId") // Define la relación con la entidad Libros, donde "autorId" es la clave foránea
    private List<Libros> librosList; // Lista de libros asociados al autor
}
