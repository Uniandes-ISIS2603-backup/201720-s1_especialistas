/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author ce.quintero
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
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
    
    @OneToOne()
    @JoinColumn(name="TARJETA_ID")
    private TarjetaEntity tarjeta;
    
    
    //Getters and setters
    /**
    * getter del atributo usuario
    * @return usuario asociado
    */
    public TarjetaEntity getTarjeta(){
       return this.tarjeta;
    }

    /**
    * setter del atributo usuario
    * @param tarjeta 
    */
    public void setTarjeta(TarjetaEntity tarjeta){
       this.tarjeta = tarjeta;
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
}
