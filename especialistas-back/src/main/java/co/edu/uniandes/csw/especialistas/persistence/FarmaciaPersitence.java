/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
public class FarmaciaPersitence {
    
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
    private static final String SELEC = "Select e From FarmaciaEntity e where e.id = :id";
    
     /**
     *
     * @param entity objeto Farmacia que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public FarmaciaEntity create(FarmaciaEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Busca si hay alguna Farmacia con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Farmacia que se está buscando
     * @return null si no existe ninguna Farmacia con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public FarmaciaEntity findByName(String nombre) {

        TypedQuery query = em.createQuery("Select e From FarmaciaEntity e where e.nombre = :nombre", FarmaciaEntity.class);
        query = query.setParameter("nombre", nombre);
        List<FarmaciaEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
        public FarmaciaEntity findById(long id) {

        TypedQuery query = em.createQuery(SELEC, FarmaciaEntity.class);

        query = query.setParameter("id", id);
        
        List<FarmaciaEntity> sameId = query.getResultList();
        if (sameId.isEmpty()) {
            return null;
        } else {
            return sameId.get(0);
        }
    }
    public boolean deleteById(long id) throws BusinessLogicException{
               
        FarmaciaEntity farmacia=null;
                
        TypedQuery query = em.createQuery(SELEC, FarmaciaEntity.class);

        query = query.setParameter("id", id);
        
        List<FarmaciaEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            farmacia= sameId.get(0);
        }
        
        if(farmacia!=null)
        {
        em.remove(farmacia);
        return true;
        }else
        {
            throw new BusinessLogicException("no existe farmacia con el id dado");
        }
    }
        
    public void update(FarmaciaEntity farmacia) {
        em.merge(farmacia);
    }

    public List<FarmaciaEntity> findAll() {
        TypedQuery query = em.createQuery("select u from FarmaciaEntity u", FarmaciaEntity.class);
        return query.getResultList();
    }
    
    public List<MedicamentoEntity> findMedicamentosById(long id) {
               
        FarmaciaEntity farmacia=null;
                
        TypedQuery query = em.createQuery(SELEC, FarmaciaEntity.class);

        query = query.setParameter("id", id);
        
        List<FarmaciaEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            farmacia= sameId.get(0);
        }
        
        if(farmacia!=null)
        {
        return farmacia.getMedicamentos();
        }
        return new ArrayList<>();
    }
    
    public UbicacionEntity findUbicacionById(long id) {
               
        FarmaciaEntity farmacia=null;
                
        TypedQuery query = em.createQuery(SELEC, FarmaciaEntity.class);

        query = query.setParameter("id", id);
        
        List<FarmaciaEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            farmacia= sameId.get(0);
        }
        
        if(farmacia!=null)
        {
        return farmacia.getUbicacion();
        }
        return null;
    }
    
    public boolean agregarMedicamentoById(long id, MedicamentoEntity med)
    {
                       
        FarmaciaEntity farmacia=null;
                
        TypedQuery query = em.createQuery(SELEC, FarmaciaEntity.class);

        query = query.setParameter("id", id);
        
        List<FarmaciaEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            farmacia= sameId.get(0);
        }
        
        if(farmacia!=null)
        {
             farmacia.agregarMedicamento(med);
             return true;
        }
        return false;
    }
    
        public boolean setUbicacionById(long id, UbicacionEntity ubicacion)
    {
                       
        FarmaciaEntity farmacia=null;
                
        TypedQuery query = em.createQuery(SELEC, FarmaciaEntity.class);

        query = query.setParameter("id", id);
        
        List<FarmaciaEntity> sameId = query.getResultList();
        if (!sameId.isEmpty()) {
            farmacia= sameId.get(0);
        }
        
        if(farmacia!=null)
        {
             farmacia.setUbicacion(ubicacion);
             return true;
        }
        return false;
    }
    
    
    
    
}
