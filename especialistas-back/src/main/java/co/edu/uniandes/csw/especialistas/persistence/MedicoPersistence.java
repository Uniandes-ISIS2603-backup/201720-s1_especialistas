/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.Especializacion;
import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author js.diaz
 */
@Stateless
public class MedicoPersistence {
        
    private static final Logger LOGGER = Logger.getLogger(MedicoPersistence.class.getName());

    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;

    /**
     * @param entity objeto medico que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MedicoEntity create(MedicoEntity entity) {
        LOGGER.info("Creando un medico nuevo");
        em.persist(entity);
        LOGGER.info("Creando un medico nuevo");
        return entity;
    }
    
    /**
     * Actualiza un medico.
     *
     * @param entity: el médico que viene con los nuevos cambios.
     * @return un médico con los cambios aplicados.
     */
    public MedicoEntity update(MedicoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando medico con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * Borra un médico de la base de datos recibiendo como argumento su id
     * @param id: id correspondiente al médico a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando médico con id={0}", id);
        MedicoEntity entity = em.find(MedicoEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay algun medico con el id que se envía de argumento
     * @param id: id correspondiente al medico buscado.
     * @return un medico.
     */
    public MedicoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medico con id={0}", id);
        return em.find(MedicoEntity.class, id);
    }

    /**
     * Busca si hay algun médico con el nombre que se envía de argumento
     *
     * @param nombre: Nombre del médico que se está buscando
     * @return null si no existe ningun médico con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public MedicoEntity findByNombre(String nombre) {
        LOGGER.log(Level.INFO, "Consultando medico por nombre ", nombre);

        TypedQuery query = em.createQuery("Select e From MedicoEntity e where e.nombre = :nombre", MedicoEntity.class);
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<MedicoEntity> sameNombre = query.getResultList();
        if (sameNombre.isEmpty()) {
            return null;
        } else {
            return sameNombre.get(0);
        }
    }
    
    /**
     * Busca los médicos con la especializacion que se envía de argumento
     *
     * @param especializacion: Especializacion que se está buscando
     * @return lista con los médicos con la especialización buscada.
     */
    public List<MedicoEntity> findByEspecializacion(Especializacion especializacion) {
        LOGGER.log(Level.INFO, "Consultando medico por especializacion ", especializacion);

        TypedQuery query = em.createQuery("Select e From MedicoEntity e where e.especializacion = :especializacion", MedicoEntity.class);
        query = query.setParameter("especializacion", especializacion);
        return query.getResultList();
    }

    /**
     * Busca todos los médicos.
     * @return Lista con todos los médicos.
     */
    public List<MedicoEntity> findAll() {
        LOGGER.info("Consultando todos los medicos");
        TypedQuery query = em.createQuery("select u from MedicoEntity u", MedicoEntity.class);
        return query.getResultList();
    }
}
