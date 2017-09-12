/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
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
public class UbicacionPersistence {
    
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
     /**
     *
     * @param entity objeto Ubicacion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public UbicacionEntity create(UbicacionEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Busca si hay alguna Ubicacion con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Farmacia que se está buscando
     * @return null si no existe ninguna Farmacia con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public UbicacionEntity findByName(String nombre) {

        TypedQuery query = em.createQuery("Select e From UbicacionEntity e where e.nombre = :nombre", UbicacionEntity.class);
        query = query.setParameter("nombre", nombre);
        List<UbicacionEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
        public UbicacionEntity findById(long id) {

        TypedQuery query = em.createQuery("Select e From UbicacionEntity e where e.id = :id", UbicacionEntity.class);

        query = query.setParameter("id", id);
        
        List<UbicacionEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }
    public boolean deleteById(long id) {
               
        UbicacionEntity ubicacion=null;
                
        TypedQuery query = em.createQuery("Select e From UbicacionEntity e where e.id = :id", UbicacionEntity.class);

        query = query.setParameter("id", id);
        
        List<UbicacionEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            ubicacion= sameId.get(0);
        }
        
        if(ubicacion!=null)
        {
        em.remove(ubicacion);
        return true;
        }
        return false;
    }
        
    public void update(UbicacionEntity Farmacia) {
        em.merge(Farmacia);
    }
    
}
