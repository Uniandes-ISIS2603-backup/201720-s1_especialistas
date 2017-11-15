/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;

/**
 * Clase que modela el DetailDTO de un consultorio
 *
 * @author jl.patarroyo
 */
public class ConsultorioDetailDTO extends ConsultorioDTO {

    /**
     * Atributo que modela el hospital que contiene al consultorio
     */
    private HospitalDTO hospital;

    /**
     * Constructor vacío
     */
    public ConsultorioDetailDTO() {
        //inicialmente vacio
    }

    /**
     * Constructor del DetailDTO a partir de una entidad
     *
     * @param entity entidad con la información
     */
    public ConsultorioDetailDTO(ConsultorioEntity entity) {
        super(entity);
        HospitalEntity entidadHospital = entity.getHospital();
        if (entidadHospital != null) {
            this.hospital = new HospitalDTO(entidadHospital);
        }
    }

    /**
     * Getter del atributo hospital
     *
     * @return hospital que contiene al hospital
     */
    public HospitalDTO getHospital() {
        return hospital;
    }

    /**
     * Setter del atributo hospital
     *
     * @param hospital hospital que contiene al hospital
     */
    public void setHospital(HospitalDTO hospital) {
        this.hospital = hospital;
    }

    /**
     * Método encargado de convertir el actual DetailDTO a una entidad
     *
     * @return entidad con la información
     */
    @Override
    public ConsultorioEntity toEntity() {
        ConsultorioEntity entity = super.toEntity();
        if (this.hospital != null) {
            HospitalEntity hospitalEntity = this.hospital.toEntity();
            entity.setHospital(hospitalEntity);
        }
        return entity;
    }

}
