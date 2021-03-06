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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dm.gutierrez11
 */
@Entity
public class LaboratorioEntity implements Serializable {

    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Representa los exámenes con los que se asocia
     */
    @PodamExclude
    @ManyToMany (cascade = CascadeType.PERSIST)
    private List<ExamenEntity> examenes;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "ubicacionID")
    private UbicacionEntity ubicacion;

    /**
     * Representa el nombre del laboratorio
     */
    private String nombre;

    // getters y setters
    public List<ExamenEntity> getExamenes() {
        if(examenes == null){
            examenes = new ArrayList<>();
        }
        return examenes;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
    public void setExamenes(List<ExamenEntity> examenes) {
        this.examenes = examenes;
    }
    
    public void addExamen(ExamenEntity entity){
        if(!examenes.contains(entity))
        examenes.add(entity);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(UbicacionEntity ub) {
        this.ubicacion = ub;
    }

    public UbicacionEntity getUbicacion() {
        return this.ubicacion;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
            if(obj.getClass()!=this.getClass()){
                return false;
            }
            if (this.getId() != null && ((LaboratorioEntity)obj).getId() != null) {
                return this.getId().equals(((LaboratorioEntity)obj).getId());
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
