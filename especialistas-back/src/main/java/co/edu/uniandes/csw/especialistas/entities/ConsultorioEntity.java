/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que modela la entidad de un consultorio
 * @author jl.patarroyo
 */
@Entity
public class ConsultorioEntity implements Serializable
{
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Hospital que contiene al consultorio
     */
    @PodamExclude
    @ManyToOne
    private HospitalEntity hospital;
    
    /**
     * Lista de horas que usan el consultorio
     */
    @PodamExclude
    @OneToMany(mappedBy = "consultorio", fetch = FetchType.LAZY)
    private List<HoraEntity> horas = new ArrayList<>();
    
    /**
     * Especializacion del consultorio
     */
    private Especializacion especializacion;
    
    /**
     * Referencia del consultorio (e.g. W405)
     */
    private String referenciaConsultorio;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    /**
     * Getter del hospital
     * @return HospitalEntity del hospital
     */
    public HospitalEntity getHospital() {
        return hospital;
    }

    /**
     * Setter del hospital
     * @param hospital HospitalEntity nuevo
     */
    public void setHospital(HospitalEntity hospital) {
        this.hospital = hospital;
    }

    /**
     * Getter de la lista de HoraEntity
     * @return Lista con las horas
     */
    public List<HoraEntity> getHoras() {
        return horas;
    }
    
    /**
     * Setter de las horas
     * @param horas Lista de horas
     */
    public void setHoras(List<HoraEntity> horas) {
        this.horas = horas;
    }

    /**
     * Getter del atributo especialización
     * @return especialización del consultorio
     */
    public Especializacion getEspecializacion() {
        return especializacion;
    }

    /**
     * Setter del atributo especialización
     * @param especializacion especialización del consultorio
     */
    public void setEspecializacion(Especializacion especializacion) {
        this.especializacion = especializacion;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
            if(obj.getClass()!=this.getClass()){
                return false;
            }
            if (this.getId() != null && ((ConsultorioEntity)obj).getId() != null) {
                return this.getId().equals(((ConsultorioEntity)obj).getId());
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
    
}
