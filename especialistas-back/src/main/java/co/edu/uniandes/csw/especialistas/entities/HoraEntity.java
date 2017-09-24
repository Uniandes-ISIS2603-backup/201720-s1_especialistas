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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.diaz
 */
@Entity
public class HoraEntity extends BaseEntity{
    
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    
/*  @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MEDICO_ID")
    private MedicoEntity medico;
    
    
   
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CITA_ID")
    private CitaEntity cita;
*/
    @PodamExclude
    @ManyToOne(fetch=FetchType.LAZY)
    private ConsultorioEntity consultorio;
    
    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Getter del atributo horaFin
     * @return HoraEntity con la información
     */
    public Date getHoraFin() {
        return horaFin;
    }

    /**
     * Setter del atributo horaFin
     * @param horaFin HoraEntity
     */
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

    /**
     * Getter del atributo consultorio
     * @return ConsultorioEntity con la información
     */
    public ConsultorioEntity getConsultorio() {
        return consultorio;
    }

    /**
     * Setter del atributo consultorio
     * @param consultorio ConsultorioEntity con la información
     */
    public void setConsultorio(ConsultorioEntity consultorio) {
        this.consultorio = consultorio;
    }
    
}
