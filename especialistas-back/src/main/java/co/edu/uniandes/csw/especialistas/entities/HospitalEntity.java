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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que modela la entidad de un hospital
 */
@Entity
public class HospitalEntity implements Serializable {

    /**
     * Atributo que modela el id del hospital
     * Este valor es autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que modela el nombre de un hospital
     */
    private String nombre;

    /**
     * Atributo que modela la ubicación de un hospital
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicadionID")
    private UbicacionEntity ubicacion;
    
    /**
     * Atributo que modela los consultorios de un hospital
     */
    @PodamExclude
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultorioEntity> consultorios = new ArrayList<>();

    /**
     * Getter del atributo id
     * @return id del hospital
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter del atributo id
     * @param id id del hospital
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter del atributo nombre
     * @return nombre del hospital
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre nombre del hospital
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo ubicacion
     * @return ubicación del hospital
     */
    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }

    /**
     * Setter del atributo ubicacion
     * @param ubicacion ubicación del hospital
     */
    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Getter del atributo consultorios
     * @return lista de consultorios del hospital
     */
    public List<ConsultorioEntity> getConsultorios() {
        return consultorios;
    }

    /**
     * Setter del atributo consultorios
     * @param consultorios lista de consultorios del hospital
     */
    public void setConsultorios(List<ConsultorioEntity> consultorios) {
        this.consultorios = consultorios;
    }

    /**
     * Método que compara dos objetos de tipo HospitalEntity
     * @param obj hospital que se desea comparar
     * @return true si son el mismo hospital, false de lo contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass() != this.getClass()) {
                return false;
            }
            if (this.getId() != null && ((HospitalEntity) obj).getId() != null) {
                return this.getId().equals(((HospitalEntity) obj).getId());
            }
        }
        return false;
    }

    /**
     * Método que genera el hashCode de un hospital
     * @return hash code del hospital
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
