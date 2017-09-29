/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jr.restom10
 */
@Entity
public class OrdenMedicaEntity implements Serializable{
    
    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descripcion;
    
    @PodamExclude
    @ManyToOne
    @JoinColumn(name="CITA_ID")
    private CitaEntity cita;
    
    @PodamExclude
    @OneToMany
    @JoinColumn(name="MEDICAMENTO_ID")
    private List <MedicamentoEntity> medicamentos;
    
    @PodamExclude
    @OneToMany
    @JoinColumn(name="EXAMEN_ID")
    private List <ExamenEntity> examenes;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
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
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
        if (this.getId() != null && ((OrdenMedicaEntity)obj).getId() != null) {
            return this.getId().equals(((OrdenMedicaEntity)obj).getId());
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
