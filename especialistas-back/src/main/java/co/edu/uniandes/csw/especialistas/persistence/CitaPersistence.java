/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



/**
 *
 * @author jr.restom10
 */

@Stateless
public class CitaPersistence {
    
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
     /**
     *
     * @param s objeto Cita que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CitaEntity create(CitaEntity s) {
        em.persist(s);
        return s;
    }
    
    
    
        public CitaEntity findById(long id) {

        TypedQuery query = em.createQuery("Select e From CitaEntity e where e.id = :id", CitaEntity.class);

        query = query.setParameter("id", id);
        
        List<CitaEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }
    public boolean deleteById(long id) {
               
        CitaEntity cita=null;
                
        TypedQuery query = em.createQuery("Select e From CitaEntity e where e.id = :id", CitaEntity.class);

        query = query.setParameter("id", id);
        
        List<CitaEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            cita= sameId.get(0);
        }
        
        if(cita!=null)
        {
        em.remove(cita);
        return true;
        }
        return false;
    }
        
    public CitaEntity update(CitaEntity cita) {
        return em.merge(cita);
    }

    public List<CitaEntity> findAll() {
        TypedQuery query = em.createQuery("select u from CitaEntity u", CitaEntity.class);
        return query.getResultList();
    }
    
    
}
