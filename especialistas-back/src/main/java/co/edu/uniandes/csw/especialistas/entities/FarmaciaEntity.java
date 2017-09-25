/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import javax.persistence.Entity;

/**
 *
 * @author rc.tejon
 */
@Entity
public class FarmaciaEntity extends BaseEntity{
    
    private int radio;
    private String nombre;

    public int getRadio() {
        return radio;
    }

    public void setRadio(int precio) {
        this.radio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
