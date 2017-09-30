/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.persistence.MedicamentoPersistence;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rc.tejon
 */
@Stateless
public class MedicamentoLogic {
    @Inject
    private MedicamentoPersistence persistence;
    
        /**
     * Método encargado de persistir un medicamento nuevo
     * @param entity Entidad del medicamento
     * @return Medicamento persistido
     */
    public MedicamentoEntity createMedicamento(MedicamentoEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargadod de eliminar un medicamento de la persistencia
     * @param id Id del medicamento
     * @return true si la entidad fue eliminada, false de lo contrario
     */
    public boolean deleteMedicamento(Long id)throws BusinessLogicException
    {
        boolean deleted = false;
        persistence.deleteById(id);
        MedicamentoEntity entity = persistence.findById(id);
        
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
        public List<MedicamentoEntity> getMedicamentos()
    {
        List<MedicamentoEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna un medicamento por su id
     * @param id id del medicamento
     * @return MedicamentoEntity del medicamento buscado
     */
    public MedicamentoEntity getMedicamento(Long id)
    {
        MedicamentoEntity entity = persistence.findById(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de un medicamento
     * @param entity medicamento con la nueva información
     * @return MedicamentoEntity con la información del medicamento actualizado
     */
    public MedicamentoEntity updateMedicamento(MedicamentoEntity entity)
    {
        persistence.update(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un medicamento por su nombre
     * @param nombre nombre del medicamento
     * @return  MedicamentoEntity correspondiente al medicamento
     */
    public MedicamentoEntity getMedicametoByName(String nombre)
    {
        MedicamentoEntity entity = persistence.findByName(nombre);
        return entity;
    }
}
