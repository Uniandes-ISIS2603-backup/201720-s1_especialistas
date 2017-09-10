/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author dm.gutierrez11
 */
@Stateless
public class ExamenPersistence {
    private static final Logger LOGGER = Logger.getLogger(ExamenPersistence.class.getName());

    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;

    /**
     * Crea un examen
     *
     * @param entity objeto Examen que se creará en la base de datos
     * @return el examen creado
     */
    public ExamenEntity create(ExamenEntity entity) {
        LOGGER.info("Creando un examen nuevo");
        em.persist(entity);
        LOGGER.info("Creando un examen nuevo");
        return entity;
    }

    /**
     * Actualiza un examen
     *
     * @param entity objeto Examen con los nuevos cambios
     * @return objeto Examen con los cambios realizados
     */
    public ExamenEntity update(ExamenEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando examen con id = ", entity.getId());
        return em.merge(entity);
    }

    /**
     * Elimina el examen con el id dado
     *
     * @param id del Examen a eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando el examen con id = ", id);
        ExamenEntity eliminar = em.find(ExamenEntity.class, id);
        em.remove(eliminar);
    }

    /**
     * Busca un examen con el id dado
     *
     * @param id del examen a encontrar
     * @return Examen buscado
     */
    public ExamenEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el examen con id = ", id);
        return em.find(ExamenEntity.class, id);
    }

    /**
     * Busca todos los examenes
     *
     * @return una lista con todos los examenes
     */
    public List<ExamenEntity> findAll() {
        LOGGER.info("Consultando todos los examenes");
        TypedQuery query = em.createQuery("select u from ExamenEntity u", ExamenEntity.class);
        return query.getResultList();
    }

    /**
     * Busca los examenes con el nombre dado
     * @param nombre del examen a buscar
     * @return null si no hay examenes con el nombre
     * si lo hay, retorna el primer elemento de la lista
     */
    public ExamenEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando examen por nombre ", nombre);
        TypedQuery query = em.createQuery("select e from ExamenEntity e where e.nombre = :nombre", ExamenEntity.class);
        List<ExamenEntity> lista = query.getResultList();
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }

    }
}
