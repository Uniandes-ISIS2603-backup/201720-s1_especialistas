/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;

/**
 *
 * @author rc.tejon
 */
public class FarmaciaDTO  extends EspecialistasDTO{
    private int radio;
    private String nombre;
    private UbicacionDTO ubicacion;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
    
    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionDTO ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public FarmaciaEntity toEntity()
    {
        FarmaciaEntity entity = new FarmaciaEntity();
        entity.setRadio(this.radio);
        entity.setNombre(this.nombre);
        entity.setId(this.id);
        entity.setRadio(this.radio);
        System.err.println(ubicacion.getNombre());
        entity.setUbicacion(this.ubicacion.toEntity());
        return entity;
    }
}
