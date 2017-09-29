/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *clase que modela la entidad tarjeta
 * @author ce.quintero
 */
@Entity
public class TarjetaEntity implements Serializable{
    
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
    * Atributo que representa el numero de la targeta
    */
    private int numero;
    
    /**
     * 
     */
    @PodamExclude
    private PagoEntity pago;
    
    
    /**
     * 
     */
    @PodamExclude
    private UsuarioEntity usuario;
    
    
    //Getters y setters
     
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
    /**
     * getter del atributo numero
     * @return numero de targeta
     */
    public int getNumero(){
        return this.numero;
    }
    
    /**
     * setter del atributo numero
     * @param numero de targeta a insertar
     */
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
        if (this.getId() != null && ((TarjetaEntity)obj).getId() != null) {
            return this.getId().equals(((TarjetaEntity)obj).getId());
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
