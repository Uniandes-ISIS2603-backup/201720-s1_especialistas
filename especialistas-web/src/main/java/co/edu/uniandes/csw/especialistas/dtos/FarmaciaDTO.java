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
    private UbicacionDetailDTO ubicacion;
    
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
    
    public UbicacionDetailDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionDetailDTO ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public FarmaciaEntity toEntity()
    {
        FarmaciaEntity entity = new FarmaciaEntity();
        entity.setRadio(this.radio);
        entity.setNombre(this.nombre);
        entity.setId(this.id);
        entity.setRadio(this.radio);
        entity.setUbicacion(this.ubicacion.toEntity());
        return entity;
    }
}
