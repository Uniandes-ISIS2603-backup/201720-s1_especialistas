/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;


import co.edu.uniandes.csw.especialistas.entities.CitaEntity;

/**
 *
 * @author jr.restom10
 */
public class CitaDTO {
    
    private String comentarios;
    private Long id;
    

    /**
     * Constructor por defecto
     */
    public CitaDTO()
    {
        //inicialmente vacio
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity entidad que contiene la información
     */
    public CitaDTO(CitaEntity entity)
    {
        this.comentarios = entity.getComentarios();
        this.id = entity.getId();
    }
    
    /**
     * Getter del atributo comentarios
     * @return comentarios del Cita
     */
    public String getComentarios() {
        return comentarios;
    }
    
    /**
     * Setter del atributo comentarios
     * @param comentarios comentarios del Cita
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    /**
     * Getter del atributo id
     * @return id del Cita
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Setter del atributo id
     * @param id id del Cita
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que convierte la clase a una entidad
     * @return CitaEntity con la información
     */
    public CitaEntity toEntity()
    {
        CitaEntity entity = new CitaEntity();
        entity.setId(this.id);
        entity.setComentarios(this.comentarios);
        return entity;
    }

}
