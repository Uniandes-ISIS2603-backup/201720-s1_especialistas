/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
import co.edu.uniandes.csw.especialistas.persistence.CitaPersistence;

import java.util.List;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author jr.restom10
 */
public class CitaLogic {
    
    
    private final CitaPersistence persistence;
    
    private final HoraLogic horaLogic;
    
    private final UsuarioLogic usuarioLogic;
     
    private final OrdenMedicaLogic ordenMedicaLogic;
     
     public CitaLogic(){
        persistence = null;
        horaLogic = null;
        usuarioLogic = null;
        ordenMedicaLogic = null;
    }
    
    @Inject
    public CitaLogic(CitaPersistence persistence,HoraLogic horaLogic,UsuarioLogic usuarioLogic,OrdenMedicaLogic ordenMedicaLogic){
        Assert.notNull(persistence, "MyCollaborator must not be null!");
        Assert.notNull(horaLogic, "MyCollaborator must not be null!");
        Assert.notNull(usuarioLogic, "MyCollaborator must not be null!");
        Assert.notNull(ordenMedicaLogic, "MyCollaborator must not be null!");
        
        this.persistence = persistence;
        this.horaLogic = horaLogic;
        this.usuarioLogic = usuarioLogic;
        this.ordenMedicaLogic = ordenMedicaLogic;
    }
    
    
    /**
     * Método encargado de persistir un Cita nuevo
     * @param entity Entidad del Cita
     * @return Cita persistido
     */
    public CitaEntity createCita(CitaEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargadod de eliminar un Cita de la persistencia
     * @param id Id del Cita
     * @return true si la entidad fue eliminada, false de lo contrario
     */
    public void deleteCita(Long id)
    {
        
        CitaEntity cita = getCita(id);
        HoraEntity hora=cita.getHora();
        if(hora!=null)
        {
            hora.setCita(null);
            horaLogic.updateHora(hora.getId(), hora);
        }
        
        UsuarioEntity usuario=cita.getUsuario();
        if(usuario!=null)
        {
           List <CitaEntity> list= usuario.getCitas();
           list.remove(cita);
           usuario.setCitas(list);
           usuarioLogic.updateUsuario(usuario);
        }
        
        for(OrdenMedicaEntity orden : cita.getOrdenesMedicas()){
            orden.setCita(null);
            ordenMedicaLogic.updateOrdenMedica(orden);
        }
        persistence.deleteById(id);
        
        
       
        
    }
    
    /**
     * Método que retorna la lista de todos las Cita
     * @return Lista con todas las entidades de las Citas
     */
    public List<CitaEntity> getCitas()
    {
        return persistence.findAll();
    }
    
    /**
     * Método que retorna una Cita por su id
     * @param id id del Cita
     * @return CitaEntity del Cita buscado
     */
    public CitaEntity getCita(Long id)
    {
        return persistence.findById(id);
    }
    
    /**
     * Método encargado de actualizar la información de un Cita
     * @param entity Cita con la nueva información
     * @return Entidad con la información del Cita actualizado
     */
    public CitaEntity updateCita( CitaEntity entity)
    {       
        persistence.update(entity);
        return entity;
    }
    
   
    
}
