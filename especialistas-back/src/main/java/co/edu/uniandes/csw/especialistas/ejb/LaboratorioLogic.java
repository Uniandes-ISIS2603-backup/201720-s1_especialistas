/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
import co.edu.uniandes.csw.especialistas.persistence.LaboratorioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dm.gutierrez11
 */
@Stateless
public class LaboratorioLogic {
    
    
    @Inject
    private LaboratorioPersistence persistence;
    
    /**
     * Se encarga de la creación de un laboratorio
     * @param entity
     * @return
     * @throws Exception 
     */
    public LaboratorioEntity createLaboratorio(LaboratorioEntity entity) throws Exception{
        persistence.create(entity);
        return entity;
    }
    /**
     * Busca todos los laboratorios
     * @return 
     */
    public List<LaboratorioEntity> getLaboratorios(){
        List<LaboratorioEntity> laboratorios = persistence.findAll();
        return laboratorios;
    }
    
    /**
     * Busca el laboratorio con el nombre dado por parámetro
     * @param name
     * @return
     * @throws Exception 
     */
    public LaboratorioEntity getLaboratorio(String name) throws Exception{
        LaboratorioEntity laboratorio = persistence.findByName(name);
        return laboratorio;
    }
    
    /**
     * Busca el laboratorio con el id dado por parámetro
     * @param id
     * @return
     */
    public LaboratorioEntity getLaboratorioById( Long id ) {
        LaboratorioEntity laboratorio = persistence.find(id);
        return laboratorio;
    }
    
    /**
     * Actualiza un laboratorio 
     * @param entity
     * @return 
     */
    public LaboratorioEntity updateLaboratorio( LaboratorioEntity entity ){
        LaboratorioEntity newLab = persistence.update(entity);
        return newLab;
    }
    
    public void deleteLaboratorio( Long id ){
        persistence.delete(id);
    }
}
