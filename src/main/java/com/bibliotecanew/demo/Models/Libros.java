/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotecanew.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author l
 */
@Entity
@Table(name = "libros")
@Data
public class Libros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "libro_id")
    private Integer libroId;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "año_publicacion")
    private Integer añoPublicacion;
    @Column(name = "genero")
    private String genero;
    @JsonIgnore
    @JoinColumn(name = "autor_id", referencedColumnName = "autor_id")
    @ManyToOne
    private Autores autorId;
    @JsonIgnore
    @JoinColumn(name = "editorial_id", referencedColumnName = "editorial_id")
    @ManyToOne
    private Editoriales editorialId;

   
    
}
