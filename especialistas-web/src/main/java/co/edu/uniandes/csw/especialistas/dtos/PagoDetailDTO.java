/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.PagoEntity;

/**
 *
 * @author ce.quintero
 */
public class PagoDetailDTO extends PagoDTO{
    /**
     * atributo que modela la tarjeta
     */
    private UsuarioDTO usuario;
    
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
        if(entity.getUsuario()!= null)
            {
                UsuarioDTO tarjeta2 = new UsuarioDTO(entity.getUsuario());
                this.usuario = tarjeta2;
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
        if(usuario != null)
            entity.setUsuario(this.usuario.toEntity());
        return entity;
    }
    
    /**
     * Get de tarjeta
     * @return 
     */
    public UsuarioDTO getUsuario() {
        return this.usuario;
    }

    /**
     * Set de tarjeta
     * @param usuario 
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
}
