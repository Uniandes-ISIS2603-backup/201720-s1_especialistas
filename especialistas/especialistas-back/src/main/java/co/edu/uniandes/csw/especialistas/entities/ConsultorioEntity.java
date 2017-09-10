/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Clase que modela la entidad de un consultorio
 * @author jl.patarroyo
 */
@Entity
public class ConsultorioEntity extends BaseEntity implements Serializable
{
    /**
     * Referencia del consultorio (e.g. W405)
     */
    private String referenciaConsultorio;
    
    /**
     * Getter del atributo referenciaConsultorio;
     * @return String con la referencia del consultorio
     */
    public String getReferenciaConsultorio() {
        return referenciaConsultorio;
    }
    
    /**
     * Setter del atributo consultorio
     * @param referenciaConsultorio String con la nueva referencia
     */
    public void setReferenciaConsultorio(String referenciaConsultorio) {
        this.referenciaConsultorio = referenciaConsultorio;
    }
    
}
