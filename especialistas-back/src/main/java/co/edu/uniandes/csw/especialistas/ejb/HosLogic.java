/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.HosEntity;
import co.edu.uniandes.csw.especialistas.persistence.UbicacionPersistence;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.especialistas.persistence.HosPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author rc.tejon
 */
@Stateless
public class HosLogic {

    
    private final HosPersistence persistence;
    

    private final UbicacionPersistence up;
    
    @Inject
    public HosLogic(HosPersistence persistence, UbicacionPersistence up){
        Assert.notNull(persistence, "Persistence must not be null!");
        Assert.notNull(up, "Persistence must not be null!");
        this.persistence = persistence;
        this.up = up;
    }
    
        /**
     * Método encargado de persistir un farmacia nuevo
     * @param entity Entidad del farmacia
     * @return Farmacia persistido
     */
    public HosEntity createHospital(HosEntity entity)
    {
        up.create(entity.getUbicacion());
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargadod de eliminar un farmacia de la persistencia
     * @param id Id del farmacia
     * @return true si la entidad fue eliminada, false de lo contrario
     */
    public HosEntity deleteHospital(Long id)throws BusinessLogicException
    {
        persistence.deleteById(id);
        return persistence.findById(id);
    }
    
    /**
     * Método que retorna la lista de todos los farmacias
     * @return Lista con todas las entidades de los farmacias
     */
        public List<HosEntity> getHospitales()
    {
        return persistence.findAll();
        
    }
    
    /**
     * Método que retorna un farmacia por su id
     * @param id id del farmacia
     * @return FarmaciaEntity del farmacia buscado
     */
    public HosEntity getHospital(Long id)
    {
        return persistence.findById(id);
        
    }
    
    /**
     * Método encargado de actualizar la información de un farmacia
     * @param entity Farmacia con la nueva información
     * @return FarmaciaEntity con la información del farmacia actualizado
     */
    public HosEntity updateHospital(HosEntity entity)
    {
        persistence.update(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un farmacia por su nombre
     * @param nombre nombre del farmacia
     * @return  FarmaciaEntity correspondiente al farmacia
     */
    public HosEntity getHospitalByName(String nombre)
    {
        return persistence.findByName(nombre);
        
    }
}
