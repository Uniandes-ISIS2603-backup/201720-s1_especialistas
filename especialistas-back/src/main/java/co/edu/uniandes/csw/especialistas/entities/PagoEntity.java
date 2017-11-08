/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ce.quintero
 */
@Entity
public class PagoEntity implements Serializable{
    
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Atributo que reprecenta la referencia de un pago
     */
    private int ref;
    
    /**
     * Atributo que reprecenta el estado de una factura
     */
    private boolean pai;
    
    /**
     * Atributo que reprecenta el precio de una factura
     */
    private int precio;
    
    /**
     * metodo de pago usado
     */
    private String metodo;
    
    /**
     * atributo que reprecenta la relacion con el usuario
     */
    @PodamExclude
    @ManyToOne(fetch=FetchType.LAZY)     
    private UsuarioEntity usuario;
    
    
    //Getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
    
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    
    public int getRef(){
        return this.ref;
    }
    
    public void setRef(int ref){
        this.ref = ref;
    }
    
    public boolean getPai(){
        return this.pai;
    }
    
    public void setPai(boolean pai){
        this.pai = pai;
    }
    
    public int getPrecio(){
        return this.precio;
    }
    
    public void setPrecio(int precio){
        this.precio = precio;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
            if(obj.getClass()!=this.getClass()){
                return false;
            }
            if (this.getId() != null && ((PagoEntity)obj).getId() != null) {
                return this.getId().equals(((PagoEntity)obj).getId());
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
