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

/**
 *
 * @author dm.gutierrez11
 */
@Stateless
public class ExamenLogic {
    
    @Inject
    private ExamenPersistence persistence;
    
    /**
     * Se encarga de la creación de un examen
     * @param entity
     * @return
     * @throws Exception 
     */
    public ExamenEntity createExamen(ExamenEntity entity) throws Exception{
        persistence.create(entity);
        return entity;
    }
    /**
     * Busca todos los examenes
     * @return 
     */
    public List<ExamenEntity> getExamenes(){
        List<ExamenEntity> examenes = persistence.findAll();
        return examenes;
    }
    
    /**
     * Busca el examen con el nombre dado por parámetro
     * @param name
     * @return
     * @throws Exception 
     */
    public ExamenEntity getExamen(String name) throws Exception{
        ExamenEntity examen = persistence.findByName(name);
        return examen;
    }
    
    /**
     * Busca el examen con el id dado por parámetro
     * @param id
     * @return
     */
    public ExamenEntity getExamenById( Long id ) {
        ExamenEntity examen = persistence.find(id);
        return examen;
    }
    
    /**
     * Actualiza un examen
     * @param entity
     * @return 
     */
    public ExamenEntity updateExamen( ExamenEntity entity ){
        ExamenEntity newExamen = persistence.update(entity);
        return newExamen;
    }
    
    public void deleteExamen( Long id ){
        persistence.delete(id);
    }
}
