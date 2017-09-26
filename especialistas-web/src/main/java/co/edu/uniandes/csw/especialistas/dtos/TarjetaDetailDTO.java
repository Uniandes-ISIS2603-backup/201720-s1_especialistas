/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;

/**
 *
 * @author ce.quintero
 */
public class TarjetaDetailDTO extends TarjetaDTO{
     public TarjetaDetailDTO()
    {
        
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity Entidad que contiene la información
     */
    public TarjetaDetailDTO(TarjetaEntity entity)
    {
        super(entity);
    }
    
}
