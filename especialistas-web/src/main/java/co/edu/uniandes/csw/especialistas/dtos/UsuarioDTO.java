/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;

/**
 *
 * @author ce.quintero
 */
public class UsuarioDTO {
    /*
    *Atributo que representa el nombre del usuario
    */
    private String nombre;
    
    /*
    *Atributo que representa el numero de cedula del usuario
    */
    private int cedula;
    
    /**
     * Atributo que reprecenta el id del usuario
     */
    private Long id;
    
    
    /**
     * Constructor por defecto
     */
    public UsuarioDTO()
    {
        
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity entidad que contiene la información
     */
    public UsuarioDTO(UsuarioEntity entity)
    {
        this.nombre = entity.getNombre();
        this.cedula = entity.getCedula();
        this.id = entity.getId();
    }
    
    /**
     * Getter del atributo nombre
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Setter del atributo nombre
     * @param nombre nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Getter del atributo nombre
     * @return cedula del usuario
     */
    public int getCedula() {
        return this.cedula;
    }
    
    /**
     * Setter del atributo nombre
     * @param cedula cedula del usuario
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    
    /**
     * Getter del atributo id
     * @return id del usuario
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Setter del atributo id
     * @param id id del usuario
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que convierte la clase a una entidad
     * @return UsuarioEntity con la información
     */
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setCedula(this.cedula);
        return entity;
    }
    
}

