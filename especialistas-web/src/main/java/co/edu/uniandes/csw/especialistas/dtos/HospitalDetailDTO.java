/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import java.util.List;

/**
 *
 * @author jl.patarroyo
 */
public class HospitalDetailDTO extends HospitalDTO
{
    
    /**
     * Lista de consultorios
     */
    private List<ConsultorioEntity> consultorios;
    
    /**
     * Ubicacion del hospital
     */
    private UbicacionEntity ubicacion;
    
    /**
     * Constructor por defecto
     */
    public HospitalDetailDTO(){      
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
        HospitalEntity entity = super.toEntity();
        entity.setConsultorios(this.consultorios);
        entity.setUbicacion(this.ubicacion);
        return entity;
    }

    /**
     * Getter de los consultorios
     * @return 
     */
    public List<ConsultorioEntity> getConsultorios() {
        return consultorios;
    }

    /**
     * Setter de los consultorios
     * @param consultorios Lista de consultorios
     */
    public void setConsultorios(List<ConsultorioEntity> consultorios) {
        this.consultorios = consultorios;
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
