 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
     * Horas del consultorio
     */
    @OneToMany(mappedBy = "consultorio")
    private List<HoraEntity> horas;
    
    /**
     * Hospital que contiene el consultorio
     */
    @ManyToOne
    private HospitalEntity hospital;

    /**
     * Getter del atributo horas
     * @return Lista de horas
     */
    public List<HoraEntity> getHoras() {
        return horas;
    }

    /**
     * Setter del atributo horas
     * @param horas Lista de horas
     */
    public void setHoras(List<HoraEntity> horas) {
        this.horas = horas;
    }

    /**
     * Getter del atributo hospital
     * @return Hospital
     */
    public HospitalEntity getHospital() {
        return hospital;
    }

    /**
     * Setter del atributo Hospital
     * @param hospital Hospital
     */
    public void setHospital(HospitalEntity hospital) {
        this.hospital = hospital;
    }
    
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
