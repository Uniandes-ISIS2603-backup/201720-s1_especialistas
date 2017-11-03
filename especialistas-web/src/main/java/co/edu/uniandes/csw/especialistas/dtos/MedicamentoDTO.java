/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;

/**
 *
 * @author rc.tejon
 */


public class MedicamentoDTO extends EspecialistasDTO{
        
    private double precio;
    private String nombre;

    public MedicamentoDTO() {
        //inicialmente vacio
    }

    
    
    public MedicamentoDTO(MedicamentoEntity entity) {
        id=entity.getId();
        precio=entity.getPrecio();
        nombre=entity.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public MedicamentoEntity toEntity()
    {
        MedicamentoEntity entity =new MedicamentoEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setPrecio(this.precio);
        return entity;
    }
    
    
}
