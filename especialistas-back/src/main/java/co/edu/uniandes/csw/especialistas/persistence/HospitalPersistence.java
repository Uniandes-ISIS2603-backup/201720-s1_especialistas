/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jl.patarroyo
 */
@Stateless
public class HospitalPersistence 
{
     /**
     * Logger de la clase
     */
    private static final Logger LOGGER = Logger.getLogger(ConsultorioPersistence.class.getName());
    
    /**
     * Manejador de las entidades
     */
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
    /**
     * Método encargado de persistir un nuevo hospital
     * @param entity HospitalEntity nuevo
     * @return HospitalEntity persistido
     */
    public HospitalEntity create(HospitalEntity entity)
    {
        LOGGER.info("Creando nuevo hospital");
        em.persist(entity);
        LOGGER.info("Hospital persistido");
        return find(entity.getId());
    }
    
    /**
     * Método encargado de eliminar un hospital
     * @param id id del hospital
     * @return HospitalEntity eliminado
     */
    public HospitalEntity delete(Long id)
    {
        LOGGER.log(Level.INFO, "Elminando hospital con id: {0}", id);
        HospitalEntity entity = em.find(HospitalEntity.class, id);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Se elminó el hospital con id: {0}", id);
        return entity;
    }
    
    /**
     * Metodo encargado de actualizar la información de un hospital
     * @param entity HospitalEntity con la nueva información
     * @return HospitalEntity actualizado
     */
    public HospitalEntity upadte(HospitalEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando la información del hospital con id: {0}", entity.getId());
        em.merge(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un hospital por su id
     * @param id id del hospital
     * @return HospitalEntity buscado
     */
    public HospitalEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Buscando hospital con id: {0}", id);
        HospitalEntity entity = em.find(HospitalEntity.class, id);
        return entity;
    }
    
    /**
     * Método encargado de buscar todos los hospital
     * @return Lista con todos los hospitales
     */
    public List<HospitalEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los hospitales");
        TypedQuery query = em.createQuery("select u from HospitalEntity u", HospitalEntity.class);
        return query.getResultList();
    }
    
    /**
     * Método encargado de buscar un hospital por su nombre
     * @param nombre Nombre del hospital
     * @return ConsultorioEntity buscado
     */
    public HospitalEntity findByReference(String nombre)
    {
        TypedQuery query = em.createQuery("select c from HospitalEntity c where c.nombre = :nombre", HospitalEntity.class);
        query = query.setParameter("nombre", nombre);
        List<HospitalEntity> list = query.getResultList();
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }
}
