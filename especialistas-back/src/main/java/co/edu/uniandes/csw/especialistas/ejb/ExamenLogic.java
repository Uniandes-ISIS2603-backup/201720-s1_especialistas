/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.persistence.ExamenPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author dm.gutierrez11
 */
@Stateless
public class ExamenLogic {
    
    
    private final ExamenPersistence persistence;
    
    public ExamenLogic(){
        persistence = null;
    }
    
    @Inject
    public ExamenLogic(ExamenPersistence persistence){
        Assert.notNull(persistence, "MyCollaborator must not be null!");
        this.persistence = persistence;
    }
    
    /**
     * Se encarga de la creación de un examen
     * @param entity
     * @return
     */
    public ExamenEntity createExamen(ExamenEntity entity){
        persistence.create(entity);
        return entity;
    }
    /**
     * Busca todos los examenes
     * @return 
     */
    public List<ExamenEntity> getExamenes(){ 
        return persistence.findAll();
    }
    
    /**
     * Busca el examen con el nombre dado por parámetro
     * @param name
     * @return
     */
    public ExamenEntity getExamen(String name){
        return  persistence.findByName(name);
    }
    
    /**
     * Busca el examen con el id dado por parámetro
     * @param id
     * @return
     */
    public ExamenEntity getExamenById( Long id ) {
        return persistence.find(id);
    }
    
    /**
     * Actualiza un examen
     * @param entity
     * @return 
     */
    public ExamenEntity updateExamen( ExamenEntity entity ){
        return persistence.update(entity);
    }
    
    public void deleteExamen( Long id ){
        persistence.delete(id);
    }
}
