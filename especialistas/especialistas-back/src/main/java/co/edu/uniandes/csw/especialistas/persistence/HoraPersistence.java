/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author js.diaz
 */
@Stateless
public class HoraPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MedicoPersistence.class.getName());

    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;

    /**
     * @param entity objeto hora que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public HoraEntity create(HoraEntity entity) {
        LOGGER.info("Creando una hora nuevo");
        em.persist(entity);
        LOGGER.info("Creando una hora nuevo");
        return entity;
    }
    
    /**
     * Actualiza una hora.
     *
     * @param entity: la hora que viene con los nuevos cambios.
     * @return la hora con los cambios aplicados.
     */
    public HoraEntity update(HoraEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando hora con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * Borra una hora de la base de datos recibiendo como argumento su id
     * @param id: id correspondiente a la hora a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando hora con id={0}", id);
        HoraEntity entity = em.find(HoraEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay alguna hora con el id que se envía de argumento
     * @param id: id correspondiente a la hora buscada.
     * @return una hora.
     */
    public HoraEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando hora con id={0}", id);
        return em.find(HoraEntity.class, id);
    }

    /**
     * Busca todas las horas.
     * @return lista con todas las horas.
     */
    public List<HoraEntity> findAll() {
        LOGGER.info("Consultando todas las horas");
        TypedQuery query = em.createQuery("select u from HoraEntity u", HoraEntity.class);
        return query.getResultList();
    }
}
