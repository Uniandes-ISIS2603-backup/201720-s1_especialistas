/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import java.util.ArrayList;
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
    private List<ConsultorioDTO> consultorios;
    
    /**
     * Ubicacion del hospital
     */
    private UbicacionDTO ubicacion;
    
    /**
     * Constructor por defecto
     */
    public HospitalDetailDTO()
    {
        super();
    }
    
    /**
     * Constructor a partir de una entidad
     * @param entity Entidad que contiene la información
     */
    public HospitalDetailDTO(HospitalEntity entity)
    {
        super(entity);
        if(entity.getUbicacion() != null)
        {
            UbicacionEntity ubicacionEntity = entity.getUbicacion();
            this.ubicacion = new UbicacionDTO(ubicacionEntity);
        }
        if(entity.getConsultorios() != null)
        {
            consultorios = new ArrayList<>();
            for(ConsultorioEntity consultorio: entity.getConsultorios())
            {
                consultorios.add(new ConsultorioDTO(consultorio));
            }
        }
    }
    
    /**
     * Método encargado de convertir el detail a entity
     * @return HospitalEntity con la información
     */
    @Override
    public HospitalEntity toEntity()
    {
        HospitalEntity entity = super.toEntity();
        if(ubicacion != null)
        {
            entity.setUbicacion(ubicacion.toEntity());
        }
        if(consultorios != null)
        {
            List<ConsultorioEntity> lista = new ArrayList<>();
            for(ConsultorioDTO consultorio: consultorios)
            {
                lista.add(consultorio.toEntity());
            }
            entity.setConsultorios(lista);
        }
        return entity;
    }

    /**
     * Getter de los consultorios
     * @return 
     */
    public List<ConsultorioDTO> getConsultorios() {
        return consultorios;
    }

    /**
     * Setter de los consultorios
     * @param consultorios Lista de consultorios
     */
    public void setConsultorios(List<ConsultorioDTO> consultorios) {
        this.consultorios = consultorios;
    }

    /**
     * Getter de la ubicación
     * @return Ubicación del hospital
     */
    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    /**
     * Setter de la ubicación
     * @param ubicacion Ubicación del hospital
     */
    public void setUbicacion(UbicacionDTO ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
