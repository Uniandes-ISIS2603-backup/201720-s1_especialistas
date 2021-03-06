/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;

/**
 *
 * @author rc.tejon
 */
public class FarmaciaDTO  extends EspecialistasDTO{
    private int radio;
    private String nombre;
    private UbicacionDTO ubicacion;

    public FarmaciaDTO() {
        //inicialmente vacio
    }
    
    

    public FarmaciaDTO(FarmaciaEntity entity) {
        id=entity.getId();
        radio=entity.getRadio();
        nombre=entity.getNombre();
        UbicacionEntity u =entity.getUbicacion();
        if(u!=null)
        {
        ubicacion=new UbicacionDTO(u);
        }
    }
    
    
    
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
        entity.setId(this.id);
        entity.setRadio(this.radio);
        entity.setNombre(this.nombre);
        entity.setId(this.id);
        entity.setRadio(this.radio);
        if(this.ubicacion!=null)
        {
            entity.setUbicacion(this.ubicacion.toEntity());
        }
        else{entity.setUbicacion(null);};
        return entity;
    }
}
