/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author dm.gutierrez11
 */
@Entity
public class LaboratorioEntity extends BaseEntity implements Serializable {
    
    /**
     * Representa el nombre del laboratorio
     */
    private String nombre;

    // getter y setter
    
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
