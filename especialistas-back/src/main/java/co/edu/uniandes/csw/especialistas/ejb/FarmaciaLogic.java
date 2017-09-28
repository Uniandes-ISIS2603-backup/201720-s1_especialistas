/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.persistence.FarmaciaPersitence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rc.tejon
 */
@Stateless
public class FarmaciaLogic {

    @Inject
    private FarmaciaPersitence persistence;
    
        /**
     * Método encargado de persistir un farmacia nuevo
     * @param entity Entidad del farmacia
     * @return Farmacia persistido
     */
    public FarmaciaEntity createFarmacia(FarmaciaEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargadod de eliminar un farmacia de la persistencia
     * @param id Id del farmacia
     * @return true si la entidad fue eliminada, false de lo contrario
     */
    public boolean deleteFarmacia(Long id)
    {
        boolean deleted = false;
        persistence.deleteById(id);
        FarmaciaEntity entity = persistence.findById(id);
        
        //Se comprueba si se eliminó la entidad
        if(entity == null)
        {
            deleted = true;
        }
        return deleted;
    }
    
    /**
     * Método que retorna la lista de todos los farmacias
     * @return Lista con todas las entidades de los farmacias
     */
        public List<FarmaciaEntity> getFarmacias()
    {
        List<FarmaciaEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna un farmacia por su id
     * @param id id del farmacia
     * @return FarmaciaEntity del farmacia buscado
     */
    public FarmaciaEntity getFarmacia(Long id)
    {
        FarmaciaEntity entity = persistence.findById(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de un farmacia
     * @param entity Farmacia con la nueva información
     * @return FarmaciaEntity con la información del farmacia actualizado
     */
    public FarmaciaEntity updateFarmacia(FarmaciaEntity entity)
    {
        persistence.update(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un farmacia por su nombre
     * @param nombre nombre del farmacia
     * @return  FarmaciaEntity correspondiente al farmacia
     */
    public FarmaciaEntity getHospitalByName(String nombre)
    {
        FarmaciaEntity entity = persistence.findByName(nombre);
        return entity;
    }
}
