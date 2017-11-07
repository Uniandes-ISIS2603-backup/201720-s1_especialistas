/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsEntity;
import co.edu.uniandes.csw.especialistas.entities.HosEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rc.tejon
 */
public class ConsDetailDTO extends ConsDTO {

    private HosDTO hospital;

    public ConsDetailDTO() {
        //inicialmente vacio
    }

    public ConsDetailDTO(ConsEntity entity) {
        super(entity);
        if (entity.getHospital() == null) {
            entity.setHospital(new HosEntity());
        }
        this.hospital = new HosDTO(entity.getHospital());
    }
    
    @Override
    public ConsEntity toEntity() {
        ConsEntity entity = super.toEntity();
        if (hospital == null) {
            hospital = new HosDTO();
        }
        entity.setHospital(this.hospital.toEntity());
        return entity;
    }

}
