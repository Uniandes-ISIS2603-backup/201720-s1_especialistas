/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
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
     * Atributo que reprecenta el codigo de seguridad de una targeta
     */
    private int codigoSeguridad;
    
    /**
     * Atributo que reprecenta el nombr en la targeta
     */
    private String nombre;
    
    
    /**
     * Atributo que reprecenta la direccion de una targeta
     */
    private String direccion;
    
    /**
     * Atributo que reprecenta la fecha de vencimiento una targeta en forma de MMAA
     */
    private int vencimiento;
    
    /**
     * atributo que reprecenta la relacion con el usuario
     */
    @PodamExclude
         @ManyToOne(fetch=FetchType.LAZY)
         
         private UsuarioEntity usuario;
    
    
    //Getters y setters
    
     public UsuarioEntity getUsuario() {
        return usuario;
    }
     
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }  
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
    
    /**
     * getter del atributo codigo
     * @return codigo de targeta
     */
    public int getCodigoSeguridad(){
        return this.codigoSeguridad;
    }
    
    /**
     * setter del atributo codigo
     * @param codigo de targeta a insertar
     */
    public void setCodigoSeguridad(int codigo){
        this.codigoSeguridad = codigo;
    }
    
    
    /**
     * getter del atributo nombre
     * @return nombre de targeta
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * setter del atributo codigo
     * @param codigo de targeta a insertar
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    
     /**
     * getter del atributo direccion
     * @return direccion de targeta
     */
    public String getDireccion(){
        return this.direccion;
    }
    
    /**
     * setter del atributo direccion
     * @param direccion de targeta a insertar
     */
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
     /**
     * getter del atributo nombre
     * @return nombre de targeta
     */
    public int getVencimiento(){
        return this.vencimiento;
    }
    
    /**
     * setter del atributo codigo
     * @param vencimiento de targeta a insertar
     */
    public void setVencimiento(int vencimiento){
        this.vencimiento = vencimiento;
    }
    
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
            if(obj.getClass()!=this.getClass()){
                return false;
            }
            if (this.getId() != null && ((TarjetaEntity)obj).getId() != null) {
                return this.getId().equals(((TarjetaEntity)obj).getId());
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
