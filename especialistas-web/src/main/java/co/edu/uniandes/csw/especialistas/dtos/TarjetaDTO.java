/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;

/**
 *
 * @author jl.patarroyo
 */
public class TarjetaDTO {
    /**
    * Atributo que representa el numero de la targeta
    */
    private int numero;
    
    /**
     * atributo que modela el id de la tarjeta
     */
    private Long id;
    
    /**
     * Constructor por defecto
     */
    public TarjetaDTO()
    {
        //crea una targeta vacia
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity entidad que contiene la información
     */
    public TarjetaDTO(TarjetaEntity entity)
    {
        this.numero = entity.getNumero();
        this.id = entity.getId();
    }
    
    /**
     * Getter del atributo nombre
     * @return cedula del usuario
     */
    public int getNumero() {
        return this.numero;
    }
    
    /**
     * Setter del atributo nombre
     */
    public void setNumero(int numero) {
        this.numero = numero;
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
    public TarjetaEntity toEntity()
    {
        TarjetaEntity entity = new TarjetaEntity();
        entity.setId(this.id);
        entity.setNumero(this.numero);
        return entity;
    }
}
