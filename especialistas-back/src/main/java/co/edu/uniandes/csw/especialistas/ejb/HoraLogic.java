/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.persistence.HoraPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author JuanSebastian
 */
public class HoraLogic {
               
    @Inject
    private HoraPersistence persistence;
    
    /**
     * Método encargado de persistir una hora nueva
     * @param entity Entidad de la hora
     * @return Hora persistida
     */
    public HoraEntity createHora(HoraEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargado de eliminar una hora de la persistencia
     * @param id Id de la hora
     * @return true si la entidad fue eliminada, false de lo contrario
     */
    public boolean deleteHora(Long id)
    {
        persistence.delete(id);
        HoraEntity entity = persistence.find(id);
        
        //Se comprueba si se eliminó la entidad
        return entity == null;
    }
    
    /**
     * Método que retorna la lista de todas las horas
     * @return Lista con todas las entidades de las horas
     */
    public List<HoraEntity> getHoras()
    {
        List<HoraEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna una hora por su id
     * @param id id de la hora
     * @return HoraEntity de la hora buscado
     */
    public HoraEntity getHora(Long id)
    {
        HoraEntity entity = persistence.find(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de una hora
     * @param entity Hora con la nueva información
     * @return Entidad con la información de la hora actualizada
     */
    public HoraEntity updateHora(HoraEntity entity)
    {
        persistence.update(entity);
        return entity;
    }

}
