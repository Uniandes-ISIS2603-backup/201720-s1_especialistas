/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.Especializacion;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;
import co.edu.uniandes.csw.especialistas.persistence.MedicoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author JuanSebastian
 */
@Stateless
public class MedicoLogic {
           
    
    private final MedicoPersistence persistence;
    
    
    private final HoraLogic horaLogic;
    
    public MedicoLogic(){
        persistence = null;
        horaLogic = null;
    }
    
    @Inject
    public MedicoLogic(MedicoPersistence persistence, HoraLogic horaLogic){
        Assert.notNull(persistence, "MyCollaborator must not be null!");
        Assert.notNull(horaLogic, "horaLogic must not be null!");
        this.persistence = persistence;
        this.horaLogic = horaLogic;
    }
    
    /**
     * Método encargado de persistir un medico nuevo
     * @param entity Entidad del medico
     * @return Medico persistido
     */
    public MedicoEntity createMedico(MedicoEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargado de eliminar un medico de la persistencia
     * @param id Id del medico
     */
    public void deleteMedico(Long id)
    {
        MedicoEntity medico = getMedico(id);
        for(HoraEntity hora : medico.getAgenda()){
            hora.setMedico(null);
            horaLogic.updateHora(hora.getId(), hora);
        }
        persistence.delete(id);
    }
    
    /**
     * Método que retorna la lista de todos los medicos
     * @return Lista con todas las entidades de los medicos
     */
    public List<MedicoEntity> getMedicos()
    {
        return persistence.findAll();
        
    }
    
    /**
     * Método que retorna un medico por su id
     * @param id id del medico
     * @return MedicoEntity del medico buscado
     */
    public MedicoEntity getMedico(Long id)
    {
        return persistence.find(id);
        
    }
    
    /**
     * Método encargado de actualizar la información de un medico
     * @param id Id del medico a actualizar
     * @param entity Medico con la nueva información
     * @return Entidad con la información del medico actualizado
     */
    public MedicoEntity updateMedico(Long id, MedicoEntity entity)
    {
        entity.setId(id);
        persistence.update(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un medico por su nombre
     * @param nombre nombre del medico
     * @return  MedicoEntity correspondiente al medico
     */
    public MedicoEntity getMedicoByNombre(String nombre)
    {
        return persistence.findByNombre(nombre);
    }
    
    /**
     * Método encargado de buscar los medicos de una especializacion
     * @param especializacion especializacion del medico
     * @return  MedicoEntity correspondiente al medico
     */
    public List<MedicoEntity> getMedicosByEspecializacion(String especializacion)
    {
        return persistence.findByEspecializacion(Enum.valueOf(Especializacion.class, especializacion));
    }
    
    
}
