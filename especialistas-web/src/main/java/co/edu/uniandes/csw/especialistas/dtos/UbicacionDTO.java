/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;

/**
 *
 * @author rc.tejon
 */
public class UbicacionDTO extends EspecialistasDTO {
    
    
    private double longitud;
    private double latitud;
    private String nombre;

    public UbicacionDTO() {
    }
    
    
    
    public UbicacionDTO(UbicacionEntity entity) {
        id=entity.getId();
        nombre=entity.getNombre();
        latitud=entity.getLatitud();
        longitud=entity.getLongitud();
    }
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public UbicacionEntity toEntity()
    {
        UbicacionEntity entity = new UbicacionEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setLatitud(this.latitud);
        entity.setLongitud(this.longitud);
        return entity;
    }
}
