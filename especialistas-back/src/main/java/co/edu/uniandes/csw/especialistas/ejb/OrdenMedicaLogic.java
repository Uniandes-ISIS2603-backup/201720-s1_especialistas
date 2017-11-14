/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
import co.edu.uniandes.csw.especialistas.persistence.OrdenMedicaPersistence;
import java.util.List;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author jr.restom10
 */
public class OrdenMedicaLogic {
    
    
    private final OrdenMedicaPersistence persistence;
    
    public OrdenMedicaLogic(){
        persistence = null;
    }
    
    @Inject
    public OrdenMedicaLogic(OrdenMedicaPersistence persistence){
        Assert.notNull(persistence, "MyCollaborator must not be null!");
        this.persistence = persistence;
    }
    
    /**
     * Método encargado de persistir un OrdenMedica nuevo
     * @param entity Entidad del OrdenMedica
     * @return OrdenMedica persistido
     */
    public OrdenMedicaEntity createOrdenMedica(OrdenMedicaEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargadod de eliminar un OrdenMedica de la persistencia
     * @param id Id del OrdenMedica
     * @return true si la entidad fue eliminada, false de lo contrario
     */
    public boolean deleteOrdenMedica(Long id)
    {
        boolean deleted = false;
        persistence.deleteById(id);
        OrdenMedicaEntity entity = persistence.findById(id);
        
        //Se comprueba si se eliminó la entidad
        if(entity == null)
        {
            deleted = true;
        }
        return deleted;
    }
    
    /**
     * Método que retorna la lista de todos las OrdenMedicas
     * @return Lista con todas las entidades de las OrdenMedicas
     */
    public List<OrdenMedicaEntity> getOrdenesMedicas()
    {
        return persistence.findAll();
        
    }
    
    /**
     * Método que retorna una OrdenMedica por su id
     * @param id id del OrdenMedica
     * @return OrdenMedicaEntity del OrdenMedica buscado
     */
    public OrdenMedicaEntity getOrdenMedica(Long id)
    {
        return persistence.findById(id);
        
    }
    
    /**
     * Método encargado de actualizar la información de un OrdenMedica
     * @param id
     * @param entity OrdenMedica con la nueva información
     * @return Entidad con la información del OrdenMedica actualizado
     */
    public OrdenMedicaEntity updateOrdenMedica(OrdenMedicaEntity entity)
    {
        persistence.update(entity);
        return entity;
    }
    
    
}
