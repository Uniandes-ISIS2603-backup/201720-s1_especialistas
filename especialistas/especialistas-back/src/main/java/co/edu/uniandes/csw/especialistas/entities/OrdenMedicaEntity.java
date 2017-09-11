/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author jr.restom10
 */
public class OrdenMedicaEntity {
    
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name="CITA_ID")
    private CitaEntity cita;
    
    @OneToMany
    @JoinColumn(name="MEDICAMENTO_ID")
    private List <MedicamentoEntity> medicamentos;
    
    @OneToMany
    @JoinColumn(name="EXAMEN_ID")
    private List <ExamenEntity> examenes;
    
    public List <MedicamentoEntity> getMedicamentos()
    {
        return medicamentos;
    }
    
    public void setMedicamentos(List <MedicamentoEntity> s)
    {
        medicamentos=s;
    }
    
    public List <ExamenEntity> getExamenes()
    {
        return examenes;
    }
    
    public void setExamenes(List <ExamenEntity> s)
    {
        examenes=s;
    }
    
    public CitaEntity getCita()
    {
        return cita;
    }
    
    public void setCita(CitaEntity s)
    {
        cita=s;
    }
    
    public String getDescripcion()
    {
        return descripcion;
    }
    
    public void setDescripcion(String s)
    {
        descripcion=s;
    }
    
    
    
}
