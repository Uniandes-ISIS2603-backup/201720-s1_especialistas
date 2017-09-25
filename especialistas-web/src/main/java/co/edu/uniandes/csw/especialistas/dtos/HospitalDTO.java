/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;

/**
 *
 * @author jl.patarroyo
 */
public class HospitalDTO 
{
    private String nombre;
    private Long id;
    //private UbicacionEntity ubicacion;

    /**
     * Constructor por defecto
     */
    public HospitalDTO()
    {
        
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity entidad que contiene la información
     */
    public HospitalDTO(HospitalEntity entity)
    {
        this.nombre = entity.getNombre();
        this.id = entity.getId();
    }
    
    /**
     * Getter del atributo nombre
     * @return nombre del hospital
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Setter del atributo nombre
     * @param nombre nombre del hospital
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Getter del atributo id
     * @return id del hospital
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Setter del atributo id
     * @param id id del hospital
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que convierte la clase a una entidad
     * @return HospitalEntity con la información
     */
    public HospitalEntity toEntity()
    {
        HospitalEntity entity = new HospitalEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        return entity;
    }
    
}
