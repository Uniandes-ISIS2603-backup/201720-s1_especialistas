/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.especialistas.persistence.ConsultorioPersistence;
import co.edu.uniandes.csw.especialistas.persistence.HospitalPersistence;
import java.util.List;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 * Clase que modela la lógica de los consultorios
 * @author jl.patarroyo
 */
public class ConsultorioLogic {
    
    /**
     * Injección de la persistencia de consultorios
     */
    @Inject
    private ConsultorioPersistence persistence;
    
    /**
     * Injección de la persistencia de hospitales
     */
    @Inject
    HospitalPersistence hospitalPersistence;
    

    
    public ConsultorioLogic(){
        persistence = null;
    }
    
    @Inject
    public ConsultorioLogic(ConsultorioPersistence persistence){
        Assert.notNull(persistence, "MyCollaborator must not be null!");
        this.persistence = persistence;
    }
    
    /**
     * Método encargado de crear un consultorio
     * @param entity entidad del consultorio que se va a crear
     * @return entidad creada
     * @throws BusinessLogicException si ya existe un consultorio con el número que tiene la entidad
     */
    public ConsultorioEntity createConsultorio(ConsultorioEntity entity) throws BusinessLogicException {
        ConsultorioEntity consultorio = persistence.findById(entity.getId());
        if (consultorio == null) {
            persistence.create(entity);
            return entity;
        } else {
            throw new BusinessLogicException("Ya existe un consultorio con el nombre '" + consultorio.getNumero() + "'");
        }
    }

    /**
     * Método encargado de eliminar un consultorio
     * @param id id del consultorio que se quiere eliminar
     * @return null si fue eliminado, el objeto de lo contrario
     * @throws BusinessLogicException si no existe un consultorio con el id proporcionado
     */
    public ConsultorioEntity deleteConsultorio(Long id) throws BusinessLogicException {
        ConsultorioEntity consultorio = getConsultorio(id);
        if (consultorio == null) {
            throw new BusinessLogicException("No se puede eliminar el consultorio con id " + id + " porque no existe");
        } else {
            persistence.deleteById(id);
            return persistence.findById(id);
        }
    }

    /**
     * Método que obtiene todos los consultorios
     * @return lista con los consultorios
     * @throws BusinessLogicException si no hay consultorios
     */
    public List<ConsultorioEntity> getConsultorios() throws BusinessLogicException {
        List<ConsultorioEntity> lista = persistence.findAll();
        if (lista == null || lista.isEmpty()) {
            throw new BusinessLogicException("No hay consultorios registrados en el sistema");
        } else {
            return persistence.findAll();
        }
    }

    /**
     * Método que obtiene un consultorio por su id
     * @param id id del consultorio buscado
     * @return consultorio buscado
     * @throws BusinessLogicException si no existe un consultorio con el id proporcionado 
     */
    public ConsultorioEntity getConsultorio(Long id) throws BusinessLogicException {
        ConsultorioEntity consultorio = persistence.findById(id);
        if (consultorio == null) {
            throw new BusinessLogicException("No existe un consultorio con el id " + id);
        } else {
            return consultorio;
        }

    }

    /**
     * Método encargado de actualizar la información de un consultorio
     * @param entity entidad actualizada
     * @return entidad actualizada
     */
    public ConsultorioEntity updateConsultorio(ConsultorioEntity entity) {
        persistence.update(entity);
        return entity;
    }

    /**
     * Método encargado de buscar un consultorio por su numero
     * @param numero numero del consultorio
     * @return consultorio buscado
     * @throws BusinessLogicException si no existe un consultorio con el numero proporcionado 
     */
    public ConsultorioEntity getConsultorioByNumero(String numero) throws BusinessLogicException {
       ConsultorioEntity consultorio = persistence.findByName(numero);
       if(consultorio == null){
           throw new BusinessLogicException("No existe un consultorio con el numero '" + numero + "'");
       }else{
           return consultorio;
       }
    }
}
