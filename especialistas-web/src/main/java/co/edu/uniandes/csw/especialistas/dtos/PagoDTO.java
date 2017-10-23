/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.PagoEntity;

/**
 *
 * @author jl.patarroyo
 */
public class PagoDTO {
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
     * Atributo que reprecenta el id del usuario
     */
    private Long id;
    
    
    /**
     * Constructor por defecto
     */
    public PagoDTO()
    {
       //crea un pago vacio 
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity entidad que contiene la información
     */
    public PagoDTO(PagoEntity entity)
    {
        this.ref = entity.getRef();
        this.precio = entity.getPrecio();
        this.pai = entity.getPai();
        this.id = entity.getId();
    }
    
    /**
     * Getter del atributo nombre
     * @return referencia del pago
     */
    public int getRef() {
        return this.ref;
    }
    
    /**
     * Setter del atributo nombre
     * @param ref referencia del pago
     */
    public void setRef(int ref) {
        this.ref = ref;
    }
    
    /**
     * Getter del atributo nombre
     * @return referencia del pago
     */
    public int getPrecio() {
        return this.precio;
    }
    
    /**
     * Setter del atributo nombre
     * @param precio
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    /**
     * Getter del atributo nombre
     * @return referencia del pago
     */
    public boolean getPai() {
        return this.pai;
    }
    
    /**
     * Setter del atributo nombre
     * @param pai estado del pago
     */
    public void setPai(boolean pai) {
        this.pai = pai;
    }
    
    /**
     * Getter del atributo id
     * @return id del pago
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Setter del atributo id
     * @param id id del pago
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que convierte la clase a una entidad
     * @return UsuarioEntity con la información
     */
    public PagoEntity toEntity()
    {
        PagoEntity entity = new PagoEntity();
        entity.setId(this.id);
        entity.setRef(this.ref);
        entity.setPrecio(this.precio);
        entity.setPai(this.pai);
        return entity;
    }
}
