/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

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
    
    //Getters and setters
    
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
