/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ConsEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.especialistas.persistence.ConsPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rc.tejon
 */
@Stateless
public class ConsLogic {
    @Inject
    private ConsPersistence persistence;
    
        /**
     * Método encargado de persistir un medicamento nuevo
     * @param entity Entidad del medicamento
     * @return Medicamento persistido
     */
    public ConsEntity createConsultorio(ConsEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargadod de eliminar un medicamento de la persistencia
     * @param id Id del medicamento
     * @return true si la entidad fue eliminada, false de lo contrario
     */
    public boolean deleteConsultorio(Long id)throws BusinessLogicException
    {
        boolean deleted = false;
        persistence.deleteById(id);
        ConsEntity entity = persistence.findById(id);
        
        //Se comprueba si se eliminó la entidad
        if(entity == null)
        {
            deleted = true;
        }
        return deleted;
    }
    
    /**
     * Método que retorna la lista de todos los medicamentos
     * @return Lista con todas las entidades de los medicamentos
     */
        public List<ConsEntity> getConsultorios()
    {
        List<ConsEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna un medicamento por su id
     * @param id id del medicamento
     * @return MedicamentoEntity del medicamento buscado
     */
    public ConsEntity getConsultorio(Long id)
    {
        ConsEntity entity = persistence.findById(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de un medicamento
     * @param entity medicamento con la nueva información
     * @return MedicamentoEntity con la información del medicamento actualizado
     */
    public ConsEntity updateConsultorio(ConsEntity entity)
    {
        persistence.update(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un medicamento por su nombre
     * @param numero nombre del medicamento
     * @return  MedicamentoEntity correspondiente al medicamento
     */
    public ConsEntity getConsultoriotoByNumero(String numero)
    {
        ConsEntity entity = persistence.findByName(numero);
        return entity;
    }
}