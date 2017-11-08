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
    
    private UsuarioDTO usuario;
    
     public TarjetaDetailDTO()
    {
        //crea una targeta detail vacia
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity Entidad que contiene la información
     */
    public TarjetaDetailDTO(TarjetaEntity entity)
    {
        super(entity);
        if(entity.getUsuario()!= null)
            {
                UsuarioDTO usuario2 = new UsuarioDTO(entity.getUsuario());
                this.usuario = usuario2;
            }
    }
    
    /**
     * Método encargado de convertir el detail a entity
     * @return UsuarioEntity con la información
     */
    @Override
    public TarjetaEntity toEntity()
    {
        TarjetaEntity entity = super.toEntity();
        if(usuario != null)
            entity.setUsuario(usuario.toEntity());
        return entity;
    }
    
    
    /**
     * Getter de targeta
     * @return 
     */
    public UsuarioDTO getUsuario() {
        return this.usuario;
    }

    /**
     * Setter de targeta
     * @param tarjeta 
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
