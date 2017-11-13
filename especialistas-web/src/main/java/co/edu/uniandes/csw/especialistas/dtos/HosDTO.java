/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;


import co.edu.uniandes.csw.especialistas.entities.HosEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;

/**
 *
 * @author rc.tejon
 */
public class HosDTO extends EspecialistasDTO {

    private String nombre;
    private UbicacionDTO ubicacion;

    public HosDTO() {
        //inicialmente vacio
    }

    public HosDTO(HosEntity entity) {
        id = entity.getId();
        nombre = entity.getNombre();
        UbicacionEntity u = entity.getUbicacion();
        if (u != null) {
            ubicacion = new UbicacionDTO(u);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    public HosEntity toEntity() {
        HosEntity entity = new HosEntity();
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
