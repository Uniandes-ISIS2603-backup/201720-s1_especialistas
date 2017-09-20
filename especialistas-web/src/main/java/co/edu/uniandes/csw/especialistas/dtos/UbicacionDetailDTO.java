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
public class UbicacionDetailDTO extends EspecialistasDTO {
    
    private double longitd;
    private double latitud;
    private String nombre;

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitd() {
        return longitd;
    }

    public void setLongitd(double longitd) {
        this.longitd = longitd;
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
        entity.setLongitud(this.longitd);
        return entity;
    }
}
