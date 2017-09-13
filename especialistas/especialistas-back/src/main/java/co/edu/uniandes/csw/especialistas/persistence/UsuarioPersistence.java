/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ce.quintero
 */
@Stateless
public class UsuarioPersistence {
    private final static Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    
    @PersistenceContext(unitName = "especialistasPU")
    protected EntityManager em;
    
    /**
     * Crea un usuario
     *
     * @param entity objeto Usuario que se creará en la base de datos
     * @return el usuario creado
     */
    public UsuarioEntity create(UsuarioEntity entity) {
        LOGGER.info("Creando un nuevo usuario");
        em.persist(entity);
        LOGGER.info("Creado Examen nuevo");
        return entity;
    }
    
    /**
     * Actualiza un usuario
     * 
     * @param entity objeto Ususario con los nuevos cambios
     * @return Usuario con los cambios realizados
     */
    public UsuarioEntity update (UsuarioEntity entity){
        LOGGER.log(Level.INFO, "Actualizando el usuario con id = ", entity.getId());
        return em.merge(entity);
    }
    
     /**
     * Elimina el usuario con el id dado
     * @param id del Usuario a eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando el usuario con id = ", id);
        UsuarioEntity eliminar = em.find(UsuarioEntity.class, id);
        em.remove(eliminar);
    }
    
    
    /**
     * Busca un usuario con el id dado
     *
     * @param id del usuario a encontrar
     * @return Usuario buscado
     */
    public UsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el usuario con id = ", id);
        return em.find(UsuarioEntity.class, id);
    }

    /**
     * Busca todos los examenes
     *
     * @return una lista con todos los examenes
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.info("Consultando todos los usuarios");
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }
    
    /**
     * Método encargado de buscar un usuario por su numero de cedula
     * @param cedula Referencia del consultorio
     * @return ConsultorioEntity buscado
     */
    public UsuarioEntity findByCedula(int cedula)
    {
        LOGGER.log(Level.INFO, "Consultando consultorio con referencia: {0}", cedula);
        TypedQuery query = em.createQuery("select u from UsuarioEntity u where u.cedula = :cedula", UsuarioEntity.class);
        List<UsuarioEntity> list = query.getResultList();
        if(list.isEmpty())
        {
            LOGGER.log(Level.INFO, "No se encontró el consultorio");
            return null;
        }
        return list.get(0);
    }
}
