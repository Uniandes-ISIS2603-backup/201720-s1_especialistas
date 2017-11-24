/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que modela la persistencia de los hospitales
 * @author jl.patarroyo
 */
@Stateless
public class HospitalPersistence {
private static final String SELECT = "Select e From HospitalEntity e where e.id = :id";
    /**
     * Atributo que modela la unidad de persistencia
     */
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;

    /**
     * Método encargado de persistir un nuevo hospital
     * @param entity entidad del hospital
     * @return entidad persistida
     */
    public HospitalEntity create(HospitalEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Método encargado de buscar un hospital por su nombre
     * @param nombre nombre del hospital
     * @return null si no existe, la entidad de lo contrario
     */
    public HospitalEntity findByName(String nombre) {

        TypedQuery query = em.createQuery("Select e From HospitalEntity e where e.nombre = :nombre", HospitalEntity.class);
        query = query.setParameter("nombre", nombre);
        List<HospitalEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    /**
     * Método encargado de buscar un hospital por su id
     * @param id id del hospital
     * @return entidad del hospital si lo encuentra, null de lo contario
     */
    public HospitalEntity findById(long id) {

        TypedQuery query = em.createQuery(SELECT, HospitalEntity.class);

        query = query.setParameter("id", id);

        List<HospitalEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }

    /**
     * Método encargado de eliminar un hospital
     * @param id id del hospital
     * @return true si fue eliminado, false de lo contrario
     * @throws BusinessLogicException 
     */
    public boolean deleteById(long id) throws BusinessLogicException {

        HospitalEntity hospital = null;

        TypedQuery query = em.createQuery(SELECT, HospitalEntity.class);

        query = query.setParameter("id", id);

        List<HospitalEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            hospital = sameId.get(0);
        }

        if (hospital != null) {
            em.remove(hospital);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método encargado de actualizar la información de un hospital
     * @param hospital entidad con la información actualizada
     */
    public void update(HospitalEntity hospital) {
        em.merge(hospital);
    }

    /**
     * Método encargado de buscar todos los hospitales
     * @return lista con todos los hospitales
     */
    public List<HospitalEntity> findAll() {
        TypedQuery query = em.createQuery("select u from HospitalEntity u", HospitalEntity.class);
        return query.getResultList();
    }

    /**
     * Método encargado de buscar una ubicacion por el id de su hospital
     * @param id id del hospital
     * @return entidad buscada si existe, null de lo contrario
     */
    public UbicacionEntity findUbicacionById(long id) {

        HospitalEntity hospital = null;

        TypedQuery query = em.createQuery(SELECT, HospitalEntity.class);

        query = query.setParameter("id", id);

        List<HospitalEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            hospital = sameId.get(0);
        }

        if (hospital != null) {
            return hospital.getUbicacion();
        }
        return null;
    }

    /**
     * Método encargado de establecer la ubicacion de un hospital
     * @param id id del hospital
     * @param ubicacion nueva ubicación del hospital
     * @return true si se actualizó, false de lo contrario
     */
    public boolean setUbicacionById(long id, UbicacionEntity ubicacion) {

        HospitalEntity hospital = null;

        TypedQuery query = em.createQuery(SELECT, HospitalEntity.class);

        query = query.setParameter("id", id);

        List<HospitalEntity> sameId = query.getResultList();
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
