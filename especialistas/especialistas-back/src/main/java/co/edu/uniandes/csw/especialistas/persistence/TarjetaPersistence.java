/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ce.quintero
 */
@Stateless
public class TarjetaPersistence {
    private final static Logger LOGGER = Logger.getLogger(TarjetaPersistence.class.getName());
    
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
    /**
     * Crea una tarjeta
     *
     * @param entity objeto Tarjeta que se creará en la base de datos
     * @return el Tarjeta creado
     */
    public TarjetaEntity create(TarjetaEntity entity) {
        LOGGER.info("Creando una nueva tarjeta");
        em.persist(entity);
        LOGGER.info("Creada Examen nueva tarjeta");
        return entity;
    }
    
    /**
     * Actualiza un tarjeta
     * 
     * @param entity objeto Tarjeta con los nuevos cambios
     * @return Tarjeta con los cambios realizados
     */
    public TarjetaEntity update (TarjetaEntity entity){
        LOGGER.log(Level.INFO, "Actualizando la tarjeta con id = ", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Elimina la tarjeta con el id dado
     * @param id de la Tarjeta a eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando el targeta con id = ", id);
        TarjetaEntity eliminar = em.find(TarjetaEntity.class, id);
        em.remove(eliminar);
    }
    
    /**
     * Busca una Tarjeta con el id dado
     *
     * @param id de la Tarjeta a encontrar
     * @return Tarjeta buscada
     */
    public TarjetaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el usuario con id = ", id);
        return em.find(TarjetaEntity.class, id);
    }
    
    
    /**
     * Busca todos las Tarjetas
     *
     * @return una lista con todos las Tarjetas
     */
    public List<TarjetaEntity> findAll() {
        LOGGER.info("Consultando todos los usuarios");
        TypedQuery query = em.createQuery("select u from TarjetaEntity u", TarjetaEntity.class);
        return query.getResultList();
    }
}
