/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

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
    
    @PodamExclude
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ConsultorioEntity> consultorios = new ArrayList<>();
    
    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private UbicacionEntity ubicacion;
    
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

    /**
     * Getter del atributo consultorios
     * @return Lista de consultorios
     */
    public List<ConsultorioEntity> getConsultorios() {
        return consultorios;
    }

    /**
     * Setter del atributo consultorios
     * @param consultorios Lista de consultorios
     */
    public void setConsultorios(List<ConsultorioEntity> consultorios) {
        this.consultorios = consultorios;
    }

    /**
     * Getter del atributo ubicación
     * @return Ubicación del hospital
     */
    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }

    /**
     * Setter del atributo ubicación
     * @param ubicacion Ubicación del consultorio
     */
    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
   
}
