/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;


import co.edu.uniandes.csw.especialistas.entities.HoraEntity;

/**
 *
 * @author JuanSebastian
 */
public class HoraDetailDTO extends HoraDTO{
    
    private MedicoDTO medico;

    public HoraDetailDTO() {
    }

    public HoraDetailDTO(HoraEntity entity) {
        super(entity);
        this.medico = new MedicoDTO(entity.getMedico());
    }

    public MedicoDTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoDTO medico) {
        this.medico = medico;
    }

    @Override
    public HoraEntity toEntity() {
        HoraEntity entity = super.toEntity();
        entity.setMedico(medico.toEntity());
        return entity;
    }

    
}
