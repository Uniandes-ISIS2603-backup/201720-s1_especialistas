/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.persistence.HospitalPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jl.patarroyo
 */
@Stateless
public class HospitalLogic {
    
    @Inject
    private HospitalPersistence persistence;
    
    /**
     * Método encargado de persistir un hospital nuevo
     * @param entity Entidad del hospital
     * @return Hospital persistido
     */
    public HospitalEntity createHospital(HospitalEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargadod de eliminar un hospital de la persistencia
     * @param id Id del hospital
     * @return true si la entidad fue eliminada, false de lo contrario
     */
    public boolean deleteHospital(Long id)
    {
        boolean deleted = false;
        persistence.delete(id);
        HospitalEntity entity = persistence.find(id);
        
        //Se comprueba si se eliminó la entidad
        if(entity == null)
        {
            deleted = true;
        }
        return deleted;
    }
    
    /**
     * Método que retorna la lista de todos los hospitales
     * @return Lista con todas las entidades de los hospitales
     */
    public List<HospitalEntity> getHospitales()
    {
        List<HospitalEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna un hospital por su id
     * @param id id del hospital
     * @return HospitalEntity del hospital buscado
     */
    public HospitalEntity getHospital(Long id)
    {
        HospitalEntity entity = persistence.find(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de un hospital
     * @param entity Hospital con la nueva información
     * @return Entidad con la información del hospital actualizado
     */
    public HospitalEntity updateHospital(HospitalEntity entity)
    {
        persistence.upadte(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un hospital por su nombre
     * @param nombre nombre del hospital
     * @return  HospitalEntity correspondiente al hospital
     */
    public HospitalEntity getHospitalByName(String nombre)
    {
        HospitalEntity entity = persistence.findByReference(nombre);
        return entity;
    }
    
}
