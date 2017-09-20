/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    
    /**
     * Ubicación del hospital
     */
    @OneToOne
    private UbicacionEntity ubicacion;
    
    /**
     * Lista de consultorios
     */
    @PodamExclude
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultorioEntity> consultorios;

    /**
     * Getter de la lista de consultorios
     * @return lista con consultorios
     */
    public List<ConsultorioEntity> getConsultorios() {
        return consultorios;
    }

    /**
     * Setter de la lista de consultorios
     * @param consultorios lista de consultorios
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
     * Setter del atributo Ubicacion
     * @param ubicacion ubicación del hospital
     */
    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }
    
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
