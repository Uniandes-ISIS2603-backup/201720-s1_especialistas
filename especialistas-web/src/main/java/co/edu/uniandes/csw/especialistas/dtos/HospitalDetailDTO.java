/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;

/**
 *
 * @author jl.patarroyo
 */
public class HospitalDetailDTO extends HospitalDTO{
    
    /**
     * Constructor por defecto
     */
    public HospitalDetailDTO()
    {
        
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity Entidad que contiene la información
     */
    public HospitalDetailDTO(HospitalEntity entity)
    {
        super(entity);
    }
    
    /**
     * Método encargado de convertir el detail a entity
     * @return HospitalEntity con la información
     */
    @Override
    public HospitalEntity toEntity()
    {
        return super.toEntity();
    }
}
