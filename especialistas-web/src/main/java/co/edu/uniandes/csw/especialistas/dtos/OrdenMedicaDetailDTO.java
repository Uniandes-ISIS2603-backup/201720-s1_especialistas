/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jr.restom10
 */
public class OrdenMedicaDetailDTO extends OrdenMedicaDTO{
     private CitaDTO cita;
     private List<MedicamentoDTO> medicamentos;
     private List<ExamenDTO> examenes;
   

    public OrdenMedicaDetailDTO() {
        //inicialmente vacio
    }
    
    public OrdenMedicaDetailDTO(OrdenMedicaEntity entity) {
        super(entity);
        
        if(entity.getExamenes()!=null)
        {
            examenes = new ArrayList();
        
        for(ExamenEntity examen:entity.getExamenes() )
        {
            examenes.add(new ExamenDTO(examen));
        }
        
        }
        
        if(entity.getMedicamentos()!=null)
        {
            medicamentos = new ArrayList();
        
        for(MedicamentoEntity medicamento:entity.getMedicamentos() )
        {
            medicamentos.add(new MedicamentoDTO(medicamento));
        }
        
        }
        
        if(cita!=null)
        {
        this.cita= new CitaDTO(entity.getCita());
        }
    }
    
    
    public CitaDTO getCita() {
        return cita;
    }
    public List<ExamenDTO> getExamenes() {
        return examenes;
    }
    public List<MedicamentoDTO> getMedicamentos() {
        return medicamentos;
    }

    
    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }
     public void setExamenes(List<ExamenDTO> examen) {
        this.examenes = examen;
    }
      public void setMedicamentos(List<MedicamentoDTO> medicamento) {
        this.medicamentos = medicamento;
    }
  
      
      
      
       @Override
    public OrdenMedicaEntity toEntity() {
        OrdenMedicaEntity entity = super.toEntity();
        if(cita!=null)
        {
        entity.setCita(cita.toEntity());
        }
        
        if(this.getExamenes()!=null)
        {
        List<ExamenEntity> o= new ArrayList();
        for(ExamenDTO examen:this.getExamenes() )
        {
            o.add(examen.toEntity());
        }
        
        
        entity.setExamenes(o);
        }
        
        if(this.getMedicamentos()!=null)
        {
        List<MedicamentoEntity> m= new ArrayList();
        for(MedicamentoDTO medicamento:this.getMedicamentos() )
        {
            m.add(medicamento.toEntity());
        }
        
        
        entity.setMedicamentos(m);
        }
        
        return entity;
    }

}
