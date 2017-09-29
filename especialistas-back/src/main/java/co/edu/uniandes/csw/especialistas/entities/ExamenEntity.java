/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dm.gutierrez11
 */

@Entity
public class ExamenEntity implements Serializable {
    
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Lista que representa los objetos Laboratorio con los que se relaciona
     */
    @PodamExclude
    @ManyToMany(mappedBy = "examenes")
    private List<LaboratorioEntity> laboratorios;
    
   /**
    * Atributo que representa el nombre del examen
    */
    private String nombre;
    
    /*
    * Atributo que representa el precio del examen
    */
    private Double precio;
    
    /*
    * Atributo que representa las recomendaciones del examen
    */
    private String recomendacion;
    
    //Getters y setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
    public void setLaboratorios(List<LaboratorioEntity> laboratorios){
        this.laboratorios = laboratorios;
    }
    
    public List<LaboratorioEntity> getLaboratorios(){
        return laboratorios;
    }
    
    public String getnombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public Double getPrecio(){
        return this.precio;
    }
    
    public void setPrecio(Double precio){
        this.precio = precio;
    }
    
    public String getRecomedacion(){
        return this.recomendacion;
    }
    
    public void setRecomedacion(String recomendacion){
        this.recomendacion = recomendacion;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
        if (this.getId() != null && ((ExamenEntity)obj).getId() != null) {
            return this.getId().equals(((ExamenEntity)obj).getId());
        }
        return super.equals(obj);
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
