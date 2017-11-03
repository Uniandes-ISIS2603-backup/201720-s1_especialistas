/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanSebastian
 */
public class MedicoDetailDTO extends MedicoDTO
{
    private List<HoraDTO> agenda;

    public MedicoDetailDTO() {
        //inicialmente vacio
    }
    
    public MedicoDetailDTO(MedicoEntity entity) {
        super(entity);
        agenda = new ArrayList<HoraDTO>();
        entity.getAgenda().forEach((hora) -> {
            agenda.add(new HoraDTO(hora));
        });
    }

    public List<HoraDTO> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<HoraDTO> agenda) {
        this.agenda = agenda;
    }

    @Override
    public MedicoEntity toEntity() {
        MedicoEntity entity = super.toEntity();
        List<HoraEntity> agenda = new ArrayList<HoraEntity>();
        this.agenda.forEach((hora) -> {
            agenda.add(hora.toEntity());
        });
        entity.setAgenda(agenda);
        return entity;
    }
    
}
