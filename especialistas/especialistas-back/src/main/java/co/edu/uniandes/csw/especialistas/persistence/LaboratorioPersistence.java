/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
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
public class LaboratorioPersistence {

    private static final Logger LOGGER = Logger.getLogger(LaboratorioPersistence.class.getName());

    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;

    /**
     * Crea un laboratorio
     *
     * @param entity objeto Laboratorio que se creará en la base de datos
     * @return el laboratorio creado
     */
    public LaboratorioEntity create(LaboratorioEntity entity) {
        LOGGER.info("Creando un laboratorio nuevo");
        em.persist(entity);
        LOGGER.info("Creando un laboratorio nuevo");
        return entity;
    }

    /**
     * Actualiza un laboratorio
     *
     * @param entity objeto Laboratorio con los nuevos cambios
     * @return objeto Laboratorio con los cambios realizados
     */
    public LaboratorioEntity update(LaboratorioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando laboratorio con id = ", entity.getId());
        return em.merge(entity);
    }

    /**
     * Elimina el laboratorio con el id dado
     *
     * @param id del Laboratorio a eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando el laboratorio con id = ", id);
        LaboratorioEntity eliminar = em.find(LaboratorioEntity.class, id);
        em.remove(eliminar);
    }

    /**
     * Busca un laboratorio con el id dado
     *
     * @param id del Laboratorio a encontrar
     * @return Laboratorio buscado
     */
    public LaboratorioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el laboratorio con id = ", id);
        return em.find(LaboratorioEntity.class, id);
    }

    /**
     * Busca todos los laboratorios
     *
     * @return una lista con todos los laboratorios
     */
    public List<LaboratorioEntity> findAll() {
        LOGGER.info("Consultando todos los laboratorios");
        TypedQuery query = em.createQuery("select u from LaboratorioEntity u", LaboratorioEntity.class);
        return query.getResultList();
    }

    /**
     * Busca los laboratorios con el nombre dado
     * @param nombre del laboratorio a buscar
     * @return null si no hay laboratorios con el nombre
     * si lo hay, retorna el primer elemento de la lista
     */
    public LaboratorioEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando laboratorio por nombre ", nombre);
        TypedQuery query = em.createQuery("select e from LaboratorioEntity e where e.nombre = :nombre", LaboratorioEntity.class);
        List<LaboratorioEntity> lista = query.getResultList();
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }

    }
}
