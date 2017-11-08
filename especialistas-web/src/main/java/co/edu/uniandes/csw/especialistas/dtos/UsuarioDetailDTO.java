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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ce.quintero
 */
public class UsuarioDetailDTO extends UsuarioDTO{
    
    /**
     * atributo que modela la targeta del usuario
     */
    private TarjetaDTO tarjeta;
    
    /**
     * lista de citasmedicas de un usuario
     */
    private List <CitaDTO> citasMedicas = new ArrayList<CitaDTO>();
    
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
        if(entity != null)
        {
            if(entity.getTarjeta() != null)
            {
                TarjetaDTO tarjeta2 = new TarjetaDTO(entity.getTarjeta());
                this.tarjeta = tarjeta2;
            }
            
            citasMedicas = new ArrayList<>();
            for (CitaEntity cita : entity.getCitas()) {
                citasMedicas.add(new CitaDTO(cita));
            }
            
        }
            
    }
    
    /**
     * Método encargado de convertir el detail a entity
     * @return UsuarioEntity con la información
     */
    @Override
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = super.toEntity();
        if(tarjeta != null)
            entity.setTarjeta(tarjeta.toEntity());
        if(entity.getCitas()!= null)
            {
                List<CitaEntity> citaEntity = new ArrayList<>();
                for (CitaDTO cita : citasMedicas){
                    citaEntity.add(cita.toEntity());
                }
                entity.setCitas(citaEntity);
            }
        return entity;
    }
    
    /**
     * Getter de targeta
     * @return 
     */
    public TarjetaDTO getTarjeta() {
        return this.tarjeta;
    }

    /**
     * Setter de targeta
     * @param tarjeta 
     */
    public void setTarjeta(TarjetaDTO tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    /**
     * Getter de los consultorios
     * @return 
     */
    public List<CitaDTO> getCitas() {
        return this.citasMedicas;
    }

    /**
     * Setter de los consultorios
     * @param citasMedicas Lista de citas medicas
     */
    public void setCitas(List<CitaDTO> citasMedicas) {
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
