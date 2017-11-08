/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.PagoEntity;
import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;

/**
 *
 * @author ce.quintero
 */
public class PagoDetailDTO extends PagoDTO{
    /**
     * atributo que modela la tarjeta
     */
    private TarjetaDTO tarjeta;
    
    /**
     * Constructor por defecto
     */
    public PagoDetailDTO()
    {
        //crea un pagoDetail vacio
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity Entidad que contiene la información
     */
    public PagoDetailDTO(PagoEntity entity)
    {
        super(entity);
        if(entity.getTarjeta() != null)
            {
                TarjetaDTO tarjeta2 = new TarjetaDTO(entity.getTarjeta());
                this.tarjeta = tarjeta2;
            }
    }
    
    /**
     * Método encargado de convertir el detail a entity
     * @return UsuarioEntity con la información
     */
    @Override
    public PagoEntity toEntity()
    {
        PagoEntity entity = super.toEntity();
        if(tarjeta != null)
            entity.setTarjeta(this.tarjeta.toEntity());
        return entity;
    }
    
    /**
     * Get de tarjeta
     * @return 
     */
    public TarjetaDTO getTarjeta() {
        return this.tarjeta;
    }

    /**
     * Set de tarjeta
     * @param tarjeta 
     */
    public void setTarjeta(TarjetaDTO tarjeta) {
        this.tarjeta = tarjeta;
    }
    
}
