/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;

/**
 *
 * @author ce.quintero
 */
public class UsuarioDTO {
    /*
    *Atributo que representa el nombre del usuario
    */
    private String nombreD;
    
    /*
    *Atributo que representa el numero de cedula del usuario
    */
    private int cedulaD;
    
    /*
    *Atributo que representa el nik del usuario
    */
    private String nickD;
    
    /*
    *Atributo que representa la clave del usuario
    */
    private String passD;
    
     /*
    *Atributo que representa el rol del usuario
    */
    private String rol;
    
    /**
     * Atributo que reprecenta el id del usuario
     */
    private Long id;
    
    
    /**
     * Constructor por defecto
     */
    public UsuarioDTO()
    {
        //crea un usuario vacio
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity entidad que contiene la información
     */
    public UsuarioDTO(UsuarioEntity entity)
    {
        this.nombreD = entity.getNombre();
        this.cedulaD = entity.getCedula();
        this.rol = entity.getRol();
        this.nickD = entity.getNick();
        this.passD = entity.getPass();
        this.id = entity.getId();
    }
    
    /**
     * Getter del atributo nombre
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombreD;
    }
    
    /**
     * Setter del atributo nombre
     * @param nombre nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombreD = nombre;
    }
    
    
    /**
     * getter del atributo nick
     * @return 
     */
    public String getNick(){
        return this.nickD;
    }
    
    /**
     * setter del atributo nick
     * @param nick 
     */
    public void setNick(String nick){
        this.nickD = nick;
    }
    
    /**
     * getter del atributo pass
     * @return 
     */
    public String getPass(){
        return this.passD;
    }
    
    /**
     * setter del atributo pass
     * @param pass 
     */
    public void setPass(String pass){
        this.passD = pass;
    }
    
    /**
     * getter del atributo rol
     * @return 
     */
    public String getRol(){
        return this.rol;
    }
    
    /**
     * setter del atributo rol
     * @param rol
     */
    public void setRol(String rol){
        this.rol = rol;
    }

    
    /**
     * Getter del atributo nombre
     * @return cedula del usuario
     */
    public int getCedula() {
        return this.cedulaD;
    }
    
    /**
     * Setter del atributo nombre
     * @param cedula cedula del usuario
     */
    public void setCedula(int cedula) {
        this.cedulaD = cedula;
    }
    
    /**
     * Getter del atributo id
     * @return id del usuario
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Setter del atributo id
     * @param id id del usuario
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que convierte la clase a una entidad
     * @return UsuarioEntity con la información
     */
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombreD);
        entity.setCedula(this.cedulaD);
        entity.setRol(this.rol);
        entity.setNick(this.nickD);
        entity.setPass(this.passD);
        return entity;
    }
    
}

