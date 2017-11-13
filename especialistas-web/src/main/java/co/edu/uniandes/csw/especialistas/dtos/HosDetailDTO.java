/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.HosEntity;
import java.util.List;

/**
 *
 * @author rc.tejon
 */
public class HosDetailDTO extends HosDTO {

    public HosDetailDTO() {
        //inicialmente vacio
    }

    public HosDetailDTO(HosEntity entity) {
        super(entity);
        
    }
    private List<ConsDTO> consultorios;

    public List<ConsDTO> getConsultorios() {
        return consultorios;
    }

    public void setConsultorios(List<ConsDTO> consultorios) {
        this.consultorios = consultorios;
    }

    @Override
    public HosEntity toEntity() {
        HosEntity entity = super.toEntity();
        

        return entity;
    }
}
