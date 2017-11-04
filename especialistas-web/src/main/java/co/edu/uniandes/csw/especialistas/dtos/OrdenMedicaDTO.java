/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;

/**
 *
 * @author jr.restom10
 */
public class OrdenMedicaDTO {
    
    
    private String descripcion;
    private Long id;
    

    /**
     * Constructor por defecto
     */
    public OrdenMedicaDTO()
    {
        //inicialmente vacio
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity entidad que contiene la información
     */
    public OrdenMedicaDTO(OrdenMedicaEntity entity)
    {
        this.descripcion = entity.getDescripcion();
        this.id = entity.getId();
    }
    
    /**
     * Getter del atributo descripcion
     * @return descripcion del OrdenMedica
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Setter del atributo descripcion
     * @param descripcion descripcion del OrdenMedica
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Getter del atributo id
     * @return id del OrdenMedica
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Setter del atributo id
     * @param id id del OrdenMedica
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que convierte la clase a una entidad
     * @return OrdenMedicaEntity con la información
     */
    public OrdenMedicaEntity toEntity()
    {
        OrdenMedicaEntity entity = new OrdenMedicaEntity();
        entity.setId(this.id);
        entity.setDescripcion(this.descripcion);
        return entity;
    }
    
}
