/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *Clase que modela la entidad de un consultorio
 */
@Entity
public class ConsultorioEntity implements Serializable{
    /**
     * Atributo que modela el id del consultorio
     * Este valor es autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Atributo que modela el número del consultorio
     */
    private String numero;
    
    /**
     * Atributo que modela el hospital que contiene al consultorio
     */
    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private HospitalEntity hospital;

    /**
     * Getter del atributo id
     * @return id del consultorio
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter del atributo id
     * @param id id del consultorio
     */
    public void setId(Long id) {
        this.id = id;
    }  

    /**
     * Getter del atributo numero
     * @return numero del consultorio
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Setter del atributo número
     * @param numero número del consultorio
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Getter del atributo hospital
     * @return hospital que contiene al consultorio
     */
    public HospitalEntity getHospital() {
        return hospital;
    }

    /**
     * Setter del atributo hospital
     * @param hospital hospital que contiene al consultorio
     */
    public void setHospital(HospitalEntity hospital) {
        this.hospital = hospital;
    }
    
    /**
     * Método que compara dos objetos de tipo ConsultorioEntity
     * @param obj Consultorio a comparar
     * @return true si son el mismo consultorio, false de lo contrario
     */
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
    
    /**
     * Método que genera un hashCode del consultorio
     * @return código hash del consultorio
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
    
}