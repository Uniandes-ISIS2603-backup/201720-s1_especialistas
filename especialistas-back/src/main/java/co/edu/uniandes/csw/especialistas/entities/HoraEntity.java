/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class HoraEntity implements Serializable{
    
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
    
    @PodamExclude
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MEDICO_ID")
    private MedicoEntity medico;
    
    @PodamExclude
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CITA_ID")
    private CitaEntity cita;

    @PodamExclude
    @ManyToOne(fetch=FetchType.LAZY)
    private ConsultorioEntity consultorio;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
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
    
    
    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public ConsultorioEntity getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(ConsultorioEntity consultorio) {
        this.consultorio = consultorio;
    }

    public CitaEntity getCita() {
        return cita;
    }

    public void setCita(CitaEntity cita) {
        this.cita = cita;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
        if (this.getId() != null && ((HoraEntity)obj).getId() != null) {
            return this.getId().equals(((HoraEntity)obj).getId());
        }
        return super.equals(obj);
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
