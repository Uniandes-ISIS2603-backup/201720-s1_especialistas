/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.Especializacion;

/**
 *
 * @author jl.patarroyo
 */
public class ConsultorioDetailDTO extends ConsultorioDTO {

    /**
     * Especialización del consultorio
     */
    private Especializacion especializacion;

    /**
     * Hospital que contiene al consultorio
     */
    private HospitalDTO hospital;

    /**
     * Constructor por defecto
     */
    public ConsultorioDetailDTO() {

    }

    /**
     * Constructor a partir de una entidad
     *
     * @param entity entidad con la información
     */
    public ConsultorioDetailDTO(ConsultorioEntity entity) {
        super(entity);
        if (entity.getEspecializacion() != null) {
            this.especializacion = entity.getEspecializacion();
        }
        if (entity.getHospital() != null) {
            HospitalDTO hospitaldto = new HospitalDTO(entity.getHospital());
            this.hospital = hospitaldto;
        }
    }

    /**
     * Getter del atributo especialización
     *
     * @return especialización del consultorio
     */
    public Especializacion getEspecializacion() {
        return especializacion;
    }

    /**
     * Setter del atributo especialización
     *
     * @param especializacion especialización del consultorio
     */
    public void setEspecializacion(Especializacion especializacion) {
        this.especializacion = especializacion;
    }

    /**
     * Getter del atributo hospital
     *
     * @return hospital que contiene al consultorio
     */
    public HospitalDTO getHospital() {
        return hospital;
    }

    /**
     * Setter del atributo hospital
     *
     * @param hospital hospital que contiene al consultorio
     */
    public void setHospital(HospitalDTO hospital) {
        this.hospital = hospital;
    }

    /**
     * Método encargado de convertir el DTO a Entity
     * @return ConsultorioEntity con la información
     */
    @Override
    public ConsultorioEntity toEntity() {
        ConsultorioEntity entity = super.toEntity();
        if(hospital != null)
        {
            entity.setHospital(hospital.toEntity());
        }
        if(especializacion != null)
        {
            entity.setEspecializacion(especializacion);
        }
        return entity;
    }

}
