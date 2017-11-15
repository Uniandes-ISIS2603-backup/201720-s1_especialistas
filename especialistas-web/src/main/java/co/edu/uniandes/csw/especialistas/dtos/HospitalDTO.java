/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;


import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;

/**
 * Clase que modela el DTO de un hospital
 * @author jl.patarroyo
 */
public class HospitalDTO extends EspecialistasDTO {

    /**
     * Atributo que modela el nombre del hospital
     */
    private String nombre;
    
    /**
     * Atributo que modela la ubicación de un hospital
     */
    private UbicacionDTO ubicacion;

    /**
     * Constructor vacío
     */
    public HospitalDTO() {
        //VACÍO
    }

    /**
     * Constructor del DTO a partir de una entidad
     * @param entity entidad del hospital
     */
    public HospitalDTO(HospitalEntity entity) {
        id = entity.getId();
        nombre = entity.getNombre();
        UbicacionEntity u = entity.getUbicacion();
        if (u != null) {
            ubicacion = new UbicacionDTO(u);
        }
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
     * Getter del atributo ubicacion
     * @return ubicación del hospital
     */
    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    /**
     * Setter del atributo ubicacion
     * @param ubicacion ubicación del hospital
     */
    public void setUbicacion(UbicacionDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Método que convierte el actual DTO a una entidad
     * @return entidad con la información del hospital
     */
    public HospitalEntity toEntity() {
        HospitalEntity entity = new HospitalEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setId(this.id);
        if (this.ubicacion != null) {
            entity.setUbicacion(this.ubicacion.toEntity());
        } else {
            entity.setUbicacion(null);
        };
        return entity;
    }
}
