 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
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
public class MedicamentoPersistence {
        
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
     /**
     *
     * @param entity objeto Medicamento que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MedicamentoEntity create(MedicamentoEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Busca si hay alguna Medicamento con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Medicamento que se está buscando
     * @return null si no existe ninguna Medicamento con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public MedicamentoEntity findByName(String nombre) {

        TypedQuery query = em.createQuery("Select e From MedicamentoEntity e where e.nombre = :nombre", MedicamentoEntity.class);
        query = query.setParameter("nombre", nombre);
        List<MedicamentoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
        public MedicamentoEntity findById(long id) {

        TypedQuery query = em.createQuery("Select e From MedicamentoEntity e where e.id = :id", MedicamentoEntity.class);

        query = query.setParameter("id", id);
        
        List<MedicamentoEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }
    public void deleteById(long id) {
        
        MedicamentoEntity medicamento=null;
                
        TypedQuery query = em.createQuery("Select e From MedicamentoEntity e where e.id = :id", MedicamentoEntity.class);

        query = query.setParameter("id", id);
        
        List<MedicamentoEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            medicamento= sameId.get(0);
        }
        
        if(medicamento!=null)
        {
        em.remove(medicamento);
        }
    }
        
    public void update(MedicamentoEntity medicamento) {
        em.merge(medicamento);
    }

    public List<MedicamentoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from MedicamentoEntity u", MedicamentoEntity  .class);
        return query.getResultList();
    }
    
}