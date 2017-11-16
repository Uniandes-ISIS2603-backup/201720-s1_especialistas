/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.HosEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 */
@Stateless
public class HosPersistence {

    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;

    private static final String SELECT = "Select e From HosEntity e where e.id = :id";
    /**
     *
     * @param entity objeto Farmacia que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public HosEntity create(HosEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Busca si hay alguna Farmacia con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Farmacia que se está buscando
     * @return null si no existe ninguna Farmacia con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public HosEntity findByName(String nombre) {

        TypedQuery query = em.createQuery("Select e From HosEntity e where e.nombre = :nombre", HosEntity.class);
        query = query.setParameter("nombre", nombre);
        List<HosEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    public HosEntity findById(long id) {

        TypedQuery query = em.createQuery(SELECT, HosEntity.class);

        query = query.setParameter("id", id);

        List<HosEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }

    public boolean deleteById(long id) throws BusinessLogicException {

        HosEntity hospital = null;

        TypedQuery query = em.createQuery(SELECT, HosEntity.class);

        query = query.setParameter("id", id);

        List<HosEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            hospital = sameId.get(0);
        }

        if (hospital != null) {
            em.remove(hospital);
            return true;
        } else {
            throw new BusinessLogicException("no existe farmacia con el id dado");
        }
    }

    public void update(HosEntity hospital) {
        em.merge(hospital);
    }

    public List<HosEntity> findAll() {
        TypedQuery query = em.createQuery("select u from HosEntity u", HosEntity.class);
        return query.getResultList();
    }

    public UbicacionEntity findUbicacionById(long id) {

        HosEntity hospital = null;

        TypedQuery query = em.createQuery(SELECT, HosEntity.class);

        query = query.setParameter("id", id);

        List<HosEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            hospital = sameId.get(0);
        }

        if (hospital != null) {
            return hospital.getUbicacion();
        }
        return null;
    }

    public boolean setUbicacionById(long id, UbicacionEntity ubicacion) {

        HosEntity hospital = null;

        TypedQuery query = em.createQuery(SELECT, HosEntity.class);

        query = query.setParameter("id", id);

        List<HosEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            hospital = sameId.get(0);
        }

        if (hospital != null) {
            hospital.setUbicacion(ubicacion);
            return true;
        }
        return false;
    }

}
