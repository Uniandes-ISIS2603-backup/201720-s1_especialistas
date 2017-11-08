/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.eclipse.persistence.jpa.config.Cascade;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * 
 */
@Entity
public class ConsEntity implements Serializable{
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
            if(obj.getClass()!=this.getClass()){
                return false;
            }
            if (this.getId() != null && ((ConsEntity)obj).getId() != null) {
                return this.getId().equals(((ConsEntity)obj).getId());
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