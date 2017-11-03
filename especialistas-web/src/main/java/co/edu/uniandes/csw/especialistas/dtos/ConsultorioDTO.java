/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;

/**
 *
 * @author jl.patarroyo
 */
public class ConsultorioDTO {

    /**
     * Número del consultorio
     */
    protected String referenciaConsultorio;

    /**
     * Id del consultorio
     */
    protected Long id;

    /**
     * Constructor por defecto
     */
    public ConsultorioDTO() {
        //inicialmente vacio
    }

    /**
     * Constructor a partir de una entidad
     *
     * @param entity Entidad que contiene la información
     */
    public ConsultorioDTO(ConsultorioEntity entity) {
        this.referenciaConsultorio = entity.getReferenciaConsultorio();
        this.id = entity.getId();
    }

    /**
     * Getter del atributo número
     *
     * @return número del consultorio
     */
    public String getNumero() {
        return referenciaConsultorio;
    }

    /**
     * Setter del atributo número
     *
     * @param numero número del consultorio
     */
    public void setNumero(String numero) {
        this.referenciaConsultorio = numero;
    }

    /**
     * Getter del atributo Id
     *
     * @return Id del consultorio
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter del atributo id
     *
     * @param id id del consultorio
     */
    public void setId(Long id) {
        this.id = id;
    }

    public ConsultorioEntity toEntity() {
        ConsultorioEntity entity = new ConsultorioEntity();
        entity.setId(this.id);
        entity.setReferenciaConsultorio(this.referenciaConsultorio);
        return entity;
    }

}
