/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.persistence.CitaPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jr.restom10
 */
public class CitaLogic {
    
    @Inject
    private CitaPersistence persistence;
    
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
    public boolean deleteCita(Long id)
    {
        boolean deleted = false;
        persistence.deleteById(id);
        CitaEntity entity = persistence.findById(id);
        
        //Se comprueba si se eliminó la entidad
        if(entity == null)
        {
            deleted = true;
        }
        return deleted;
    }
    
    /**
     * Método que retorna la lista de todos las Cita
     * @return Lista con todas las entidades de las Citas
     */
    public List<CitaEntity> getCitas()
    {
        List<CitaEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna una Cita por su id
     * @param id id del Cita
     * @return CitaEntity del Cita buscado
     */
    public CitaEntity getCita(Long id)
    {
        CitaEntity entity = persistence.findById(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de un Cita
     * @param id Id de la cita a modificar
     * @param entity Cita con la nueva información
     * @return Entidad con la información del Cita actualizado
     */
    public CitaEntity updateCita(Long id, CitaEntity entity)
    {
        entity.setId(id);
        persistence.update(entity);
        return entity;
    }
    
   
    
}
