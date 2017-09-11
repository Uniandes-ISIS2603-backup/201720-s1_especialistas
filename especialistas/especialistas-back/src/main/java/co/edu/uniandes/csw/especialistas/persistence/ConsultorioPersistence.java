/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
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
public class ConsultorioPersistence 
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
     * Método encargado de persistir un nuevo consultorio
     * @param entity ConsultorioEntity nuevo
     * @return ConsultorioEntity persistido
     */
    public ConsultorioEntity create(ConsultorioEntity entity)
    {
        LOGGER.info("Creando nuevo consultorio");
        em.persist(entity);
        LOGGER.info("Consultorio persistido");
        return entity;
    }
    
    /**
     * Método encargado de eliminar un consultorio
     * @param id id del consultorio
     * @return ConsultorioEntity eliminado
     */
    public ConsultorioEntity delete(Long id)
    {
        LOGGER.log(Level.INFO, "Elminando consultorio con id: {0}", id);
        ConsultorioEntity entity = em.find(ConsultorioEntity.class, id);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Se elminó el consultorio con id: {0}", id);
        return entity;
    }
    
    /**
     * Metodo encargado de actualizar la información de un consultorio
     * @param entity ConsultorioEntity con la nueva información
     * @return ConsultorioEntity actualizado
     */
    public ConsultorioEntity upadte(ConsultorioEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando la información del consultorio con id: {0}", entity.getId());
        em.merge(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un consultorio por su id
     * @param id id del consultorio
     * @return ConsultorioEntity buscado
     */
    public ConsultorioEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Buscando consultorio con id: {0}", id);
        ConsultorioEntity entity = em.find(ConsultorioEntity.class, id);
        return entity;
    }
    
    /**
     * Método encargado de buscar todos los consultorios
     * @return Lista con todos los consultorios
     */
    public List<ConsultorioEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los consultorios");
        TypedQuery query = em.createQuery("select u from ConsultorioEntity u", ConsultorioEntity.class);
        return query.getResultList();
    }
    
    /**
     * Método encargado de buscar un consultorio por su referencia
     * @param referencia Referencia del consultorio
     * @return ConsultorioEntity buscado
     */
    public ConsultorioEntity findByReference(String referencia)
    {
        LOGGER.log(Level.INFO, "Consultando consultorio con referencia: {0}", referencia);
        TypedQuery query = em.createQuery("select c from ConsultorioEntity c where c.referenciaConsultorio = :referencia", ConsultorioEntity.class);
        List<ConsultorioEntity> list = query.getResultList();
        if(list.isEmpty())
        {
            LOGGER.log(Level.INFO, "No se encontró el consultorio");
            return null;
        }
        return list.get(0);
    }
}
