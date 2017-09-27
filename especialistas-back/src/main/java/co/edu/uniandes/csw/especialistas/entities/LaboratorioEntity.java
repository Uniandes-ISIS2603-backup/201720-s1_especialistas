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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dm.gutierrez11
 */
@Entity
public class LaboratorioEntity extends BaseEntity implements Serializable {
    
    /**
     * Representa las exámenes con los que se asocia
     */
    @PodamExclude
    @ManyToMany
    private List<ExamenEntity> examenes;
   
    /**
     * radio que abarca el alcance del laboratorio
     */
    private double radio;
    
    /**
     * Representa el nombre del laboratorio
     */
    private String nombre;

    // getters y setters
    
    public List<ExamenEntity> getExamenes(){
        return examenes;
    }
    
    public void setExamenes(List<ExamenEntity> examenes){
        this.examenes = examenes;
    }
    
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
   public void setRadio(double radio){
       this.radio = radio;
   }
   
   public double getRadio(){
       return this.radio;
   }
}
