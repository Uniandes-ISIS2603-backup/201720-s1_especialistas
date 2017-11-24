/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.especialistas.persistence.LaboratorioPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author dm.gutierrez11
 */
@Stateless
public class LaboratorioLogic {
    
    
    
    private final LaboratorioPersistence persistence;
    
    public LaboratorioLogic(){
        persistence = null;
    }
    
    @Inject
    public LaboratorioLogic(LaboratorioPersistence persistence){
        Assert.notNull(persistence, "MyCollaborator must not be null!");
        this.persistence = persistence;
    }
    
    /**
     * Se encarga de la creación de un laboratorio
     * @param entity
     * @return
     */
    public LaboratorioEntity createLaboratorio(LaboratorioEntity entity){
        persistence.create(entity);
        return entity;
    }
    /**
     * Busca todos los laboratorios
     * @return 
     */
    public List<LaboratorioEntity> getLaboratorios(){
        return persistence.findAll();
        
    }
    
    /**
     * Busca el laboratorio con el nombre dado por parámetro
     * @param name
     * @return
     */
    public LaboratorioEntity getLaboratorio(String name){
        return persistence.findByName(name);
        
    }
    
    /**
     * Busca el laboratorio con el id dado por parámetro
     * @param id
     * @return
     */
    public LaboratorioEntity getLaboratorioById( Long id ) {
        return persistence.find(id);
        
    }
    
    /**
     * Actualiza un laboratorio 
     * @param entity
     * @return 
     */
    public LaboratorioEntity updateLaboratorio( LaboratorioEntity entity ){
        return persistence.update(entity);
        
    }
    
    /**
     * Agrega un examen a un laboratorio
     * @param exam
     * @param entity 
     */
    public LaboratorioEntity addExam(ExamenEntity exam, Long id) throws BusinessLogicException{
        LaboratorioEntity laboratorio = persistence.find(id);
        if(laboratorio == null){
            throw new BusinessLogicException("no existe un laboratorio con el id " + id);
        }else{
            
        persistence.addExam(laboratorio, exam);
        }
        return laboratorio;
    }
    
    public void deleteLaboratorio( Long id ){
        persistence.delete(id);
    }
}
