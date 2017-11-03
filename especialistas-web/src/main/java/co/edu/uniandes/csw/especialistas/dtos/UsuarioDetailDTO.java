/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
import java.util.List;

/**
 *
 * @author ce.quintero
 */
public class UsuarioDetailDTO extends UsuarioDTO{
    
    /**
     * atributo que modela la targeta del usuario
     */
    private TarjetaEntity tarjeta;
    
    /**
     * lista de citasmedicas de un usuario
     */
    private List <CitaEntity> citasMedicas;
    
    /**
     * Ubicacion del usuario
     */
    private UbicacionEntity ubicacion;
    
    
    /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO()
    {
        //crea un usuario detail vacio
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity Entidad que contiene la información
     */
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super(entity);
    }
    
    /**
     * Método encargado de convertir el detail a entity
     * @return UsuarioEntity con la información
     */
    @Override
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = super.toEntity();
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
    
    /**
     * Getter de los consultorios
     * @return 
     */
    public List<CitaEntity> getCitas() {
        return this.citasMedicas;
    }

    /**
     * Setter de los consultorios
     * @param citasMedicas Lista de citas medicas
     */
    public void setConsultorios(List<CitaEntity> citasMedicas) {
        this.citasMedicas = citasMedicas;
    }
    
    /**
     * Getter de la ubicación
     * @return Ubicación del hospital
     */
    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }

    /**
     * Setter de la ubicación
     * @param ubicacion Ubicación del hospital
     */
    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }
}
