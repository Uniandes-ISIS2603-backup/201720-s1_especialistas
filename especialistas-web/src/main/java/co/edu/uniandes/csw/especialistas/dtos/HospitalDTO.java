/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;

/**
 *
 * @author jl.patarroyo
 */
public class HospitalDTO extends EspecialistasDTO{

    private String nombre;
    

    /**
     * Constructor por defecto
     */
    public HospitalDTO() {
        //inicialmente vacio
    }

    /**
     * Constructor a partir de una entidad
     *
     * @param entity entidad que contiene la información
     */
    public HospitalDTO(HospitalEntity entity) {
        this.nombre = entity.getNombre();
        this.id = entity.getId();
    }

    /**
     * Getter del atributo nombre
     *
     * @return nombre del hospital
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     *
     * @param nombre nombre del hospital
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que convierte la clase a una entidad
     *
     * @return HospitalEntity con la información
     */
    public HospitalEntity toEntity() {
        HospitalEntity entity = new HospitalEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        return entity;
    }

}
