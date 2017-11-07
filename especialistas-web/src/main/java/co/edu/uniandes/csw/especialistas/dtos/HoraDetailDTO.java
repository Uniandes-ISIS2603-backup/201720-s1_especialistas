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
    
    private CitaDTO cita;
    
    private ConsultorioDTO consultorio;

    public HoraDetailDTO() {
        //inicialmente vacio
    }

    public HoraDetailDTO(HoraEntity entity) {
        super(entity);
        this.medico = new MedicoDTO(entity.getMedico());
        this.cita = entity.getCita() != null? new CitaDTO(entity.getCita()) : null;
        this.consultorio = new ConsultorioDTO(entity.getConsultorio());
    }

    public MedicoDTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoDTO medico) {
        this.medico = medico;
    }

    public CitaDTO getCita() {
        return cita;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    public ConsultorioDTO getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(ConsultorioDTO consultorio) {
        this.consultorio = consultorio;
    }

    @Override
    public HoraEntity toEntity() {
        HoraEntity entity = super.toEntity();
        entity.setMedico(medico.toEntity());
        entity.setCita(cita != null? cita.toEntity() : null);
        entity.setConsultorio(consultorio.toEntity());
        return entity;
    }

    
}
