/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.PagoEntity;
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
public class PagoPersistence {
    private final static Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());
    
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
    /**
     * Crea una Pago
     *
     * @param entity objeto Pago que se creará en la base de datos
     * @return el Pago creado
     */
    public PagoEntity create(PagoEntity entity) {
        LOGGER.info("Creando una nueva Pago");
        em.persist(entity);
        LOGGER.info("Creada Examen nueva Pago");
        return entity;
    }
    
    /**
     * Actualiza un Pago
     * 
     * @param entity objeto Pago con los nuevos cambios
     * @return Pago con los cambios realizados
     */
    public PagoEntity update (PagoEntity entity){
        LOGGER.log(Level.INFO, "Actualizando el Pago con id = ", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Elimina la Pago con el id dado
     * @param id de la Pago a eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando el pago con id = ", id);
        PagoEntity eliminar = em.find(PagoEntity.class, id);
        em.remove(eliminar);
    }
    
    /**
     * Busca una Pago con el id dado
     *
     * @param id de la Pago a encontrar
     * @return Pago buscada
     */
    public PagoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el pago con id = ", id);
        return em.find(PagoEntity.class, id);
    }
    
    
    /**
     * Busca todos las Pagos
     *
     * @return una lista con todos las Pagos
     */
    public List<PagoEntity> findAll() {
        LOGGER.info("Consultando todos los pagos");
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        return query.getResultList();
    }
}
