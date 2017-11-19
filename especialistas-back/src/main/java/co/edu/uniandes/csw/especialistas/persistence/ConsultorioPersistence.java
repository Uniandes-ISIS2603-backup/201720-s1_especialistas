/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que modela la persistencia de los consultorios
 * @author jl.patarroyo
 */
@Stateless
public class ConsultorioPersistence {
    private final static String SELECT = "Select e From ConsultorioEntity e where e.id = :id";

    /**
     * Atributo que modela la unidad de persistencia
     */
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;

    /**
     * Método que persiste un nuevo consultorio
     * @param entity consultorio que se quiere persistir
     * @return entidad persistida
     */
    public ConsultorioEntity create(ConsultorioEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Método encargado de buscar un consultorio por su número
     * @param numero número del consultorio
     * @return consultorio buscado, null si no existe
     */
    public ConsultorioEntity findByName(String numero) {

        TypedQuery query = em.createQuery("Select e From ConsultorioEntity e where e.numero = :numero", ConsultorioEntity.class);
        query = query.setParameter("numero", numero);
        List<ConsultorioEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    /**
     * Método encargado de buscar un consultorio por su id
     * @param id id del consultorio
     * @return consultorio buscado, null si no existe
     */
    public ConsultorioEntity findById(long id) {

        TypedQuery query = em.createQuery(SELECT, ConsultorioEntity.class);

        query = query.setParameter("id", id);

        List<ConsultorioEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }

    /**
     * Método encargado de eliminar un consultorio por su id
     * @param id id del consultorio
     */
    public void deleteById(long id){

        ConsultorioEntity consultorio = null;

        TypedQuery query = em.createQuery(SELECT, ConsultorioEntity.class);

        query = query.setParameter("id", id);

        List<ConsultorioEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            consultorio = sameId.get(0);
        }

        if (consultorio != null) {
            em.remove(consultorio);
        }
    }

    /**
     * Método encargado de actualizar la información de un consultorio
     * @param consultorio entidad con la información actualizada
     */
    public void update(ConsultorioEntity consultorio) {
        em.merge(consultorio);
    }

    /**
     * Método encargado de buscar todos los consultorios
     * @return lista de consultorios
     */
    public List<ConsultorioEntity> findAll() {
        TypedQuery query = em.createQuery("select u from ConsultorioEntity u", ConsultorioEntity.class);
        return query.getResultList();
    }

    /**
     * Método encargado de agregar un hospital a un consultorio
     * @param id id del consultorio
     * @return true si se agregó, false de lo contrario
     */
    public boolean agregarHospitalById(long id) {

        ConsultorioEntity consultorio = null;

        TypedQuery query = em.createQuery(SELECT, ConsultorioEntity.class);

        query = query.setParameter("id", id);

        List<ConsultorioEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            consultorio = sameId.get(0);
        }

        if (consultorio != null) {
            return true;
        }
        return false;
    }

}
