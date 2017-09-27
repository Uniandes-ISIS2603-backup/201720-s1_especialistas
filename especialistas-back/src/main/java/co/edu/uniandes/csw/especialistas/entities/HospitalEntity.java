/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Clase que modela la entidad de un hospital
 * @author jl.patarroyo
 */
@Entity
public class HospitalEntity extends BaseEntity implements Serializable 
{
    /**
     * Nombre del hospital
     */
    private String nombre;
    
    /**
     * Getter del atributo nombre
     * @return String con el nombre del hospital
     */
    public String getNombre() 
    {
        return nombre;
    }
    
    /**
     * Setter del atributo nombre
     * @param nombre String del nuevo nombre
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
   
}
