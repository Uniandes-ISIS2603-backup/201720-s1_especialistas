/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author js.diaz
 */
@Entity
public class HoraEntity extends BaseEntity{
    
    private Date horaInicio;
    
    private Date horaFin;
    
/*    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MEDICO_ID")
    private MedicoEntity medico;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONSULTORIO_ID")
    private ConsultorioEntity consultorio;
   
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CITA_ID")
    private CitaEntity cita;
*/
    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
/*
    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
        if(medico != null)
            medico.getAgenda().add(this);
    }

    public ConsultorioEntity getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(ConsultorioEntity consultorio) {
        this.consultorio = consultorio;
        consultorio.getHoras().add(this);
    }

    public CitaEntity getCita() {
        return cita;
    }

    public void setCita(CitaEntity cita) {
        this.cita = cita;
        if(cita != null && cita.getHora() == null)
            cita.setHora(this);
    }
 */
    
}
