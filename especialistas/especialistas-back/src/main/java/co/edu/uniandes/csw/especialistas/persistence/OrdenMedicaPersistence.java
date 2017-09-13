/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.OrdenMedicaEntity;
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
public class OrdenMedicaPersistence {
    
 
    
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
     /**
     *
     * @param s objeto OrdenMedica que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public OrdenMedicaEntity create(OrdenMedicaEntity s) {
        em.persist(s);
        return s;
    }
    
    
    
        public OrdenMedicaEntity findById(long id) {

        TypedQuery query = em.createQuery("Select e From OrdenMedicaEntity e where e.id = :id", OrdenMedicaEntity.class);

        query = query.setParameter("id", id);
        
        List<OrdenMedicaEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }
    public boolean deleteById(long id) {
               
        OrdenMedicaEntity OrdenMedica=null;
                
        TypedQuery query = em.createQuery("Select e From OrdenMedicaEntity e where e.id = :id", OrdenMedicaEntity.class);

        query = query.setParameter("id", id);
        
        List<OrdenMedicaEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            OrdenMedica= sameId.get(0);
        }
        
        if(OrdenMedica!=null)
        {
        em.remove(OrdenMedica);
        return true;
        }
        return false;
    }
        
    public void update(OrdenMedicaEntity OrdenMedica) {
        em.merge(OrdenMedica);
    }

    public List<OrdenMedicaEntity> findAll() {
        TypedQuery query = em.createQuery("select u from OrdenMedicaEntity u", OrdenMedicaEntity.class);
        return query.getResultList();
    }
    
}
