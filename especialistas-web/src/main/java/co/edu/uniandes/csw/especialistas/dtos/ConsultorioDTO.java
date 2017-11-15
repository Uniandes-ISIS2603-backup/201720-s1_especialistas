/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;


/**
 * Clase que modela el DTO de un consultorio
 * @author jl.patarroyo
 */
public class ConsultorioDTO extends EspecialistasDTO {

    /**
     * Atributo que modela el número del consultorio
     */
    private String numero;

    /**
     * Constructor vacío
     */
    public ConsultorioDTO() {
        //VACÍO
    }

    /**
     * Constructor a partir de una entidad
     * @param entity entidad que contiene la información
     */
    public ConsultorioDTO(ConsultorioEntity entity) {
        id = entity.getId();
        numero = entity.getNumero();
    }

    /**
     * Getter del atributo numero
     * @return numero del consultorio
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Setter del atributo numero
     * @param numero número del consultorio
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Método que convierte el actual DTO a una entidad
     * @return entidad con la información del consultorio
     */
    public ConsultorioEntity toEntity() {
        ConsultorioEntity entity = new ConsultorioEntity();
        entity.setId(this.id);
        entity.setNumero(this.numero);
        return entity;
    }

}
