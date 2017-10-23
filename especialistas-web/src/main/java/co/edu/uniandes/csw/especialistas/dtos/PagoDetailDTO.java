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
 * @author jl.patarroyo
 */
public class PagoDetailDTO extends PagoDTO{
    /**
     * atributo que modela la targeta
     */
    private TarjetaEntity tarjeta;
    
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
    }
    
    /**
     * Método encargado de convertir el detail a entity
     * @return UsuarioEntity con la información
     */
    @Override
    public PagoEntity toEntity()
    {
        PagoEntity entity = super.toEntity();
        entity.setTarjeta(this.tarjeta);
        return entity;
    }
    
    /**
     * Getter de targeta
     * @return 
     */
    public TarjetaEntity getTargeta() {
        return this.tarjeta;
    }

    /**
     * Setter de targeta
     * @param tarjeta 
     */
    public void setTarjeta(TarjetaEntity tarjeta) {
        this.tarjeta = tarjeta;
    }
    
}
