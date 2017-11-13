/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ConsEntity;
import co.edu.uniandes.csw.especialistas.entities.HosEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rc.tejon
 */
@Stateless
public class ConsPersistence {

    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Medicamento que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ConsEntity create(ConsEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Busca si hay alguna Medicamento con el nombre que se envía de argumento
     *
     * @param numero: Nombre de la Medicamento que se está buscando
     * @return null si no existe ninguna Medicamento con el nombre del
     * argumento. Si existe alguna devuelve la primera.
     */
    public ConsEntity findByName(String numero) {

        TypedQuery query = em.createQuery("Select e From ConsEntity e where e.numero = :numero", ConsEntity.class);
        query = query.setParameter("nombre", numero);
        List<ConsEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    public ConsEntity findById(long id) {

        TypedQuery query = em.createQuery("Select e From ConsEntity e where e.id = :id", ConsEntity.class);

        query = query.setParameter("id", id);

        List<ConsEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }

    public void deleteById(long id) throws BusinessLogicException {

        ConsEntity consultorio = null;

        TypedQuery query = em.createQuery("Select e From ConsEntity e where e.id = :id", ConsEntity.class);

        query = query.setParameter("id", id);

        List<ConsEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            consultorio = sameId.get(0);
        }

        if (consultorio != null) {
            em.remove(consultorio);
        } else {
            throw new BusinessLogicException("no existe consultorio con el id dado");
        }
    }

    public void update(ConsEntity consultorio) {
        em.merge(consultorio);
    }

    public List<ConsEntity> findAll() {
        TypedQuery query = em.createQuery("select u from ConsEntity u", ConsEntity.class);
        return query.getResultList();
    }

    public boolean agregarHospitalById(long id) {

        ConsEntity consultorio = null;

        TypedQuery query = em.createQuery("Select e From ConsEntity e where e.id = :id", ConsEntity.class);

        query = query.setParameter("id", id);

        List<ConsEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            consultorio = sameId.get(0);
        }

        if (consultorio != null) {
            return true;
        }
        return false;
    }

}
