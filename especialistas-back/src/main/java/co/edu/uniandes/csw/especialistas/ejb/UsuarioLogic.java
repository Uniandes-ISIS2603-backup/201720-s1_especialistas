/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
import co.edu.uniandes.csw.especialistas.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author ce.quintero
 */
@Stateless
public class UsuarioLogic {
    /**
     * Injección de la persistencia de usuario
     */
    
    private final UsuarioPersistence persistence;
    
    @Inject
    public UsuarioLogic(UsuarioPersistence persistence){
        Assert.notNull(persistence, "Persistence must not be null!");
        this.persistence = persistence;
    }

    /**
     * Método encargado de crear un usuario
     *
     * @param entity entidad con la información
     * @return entidad del usuario creado
     */
    public UsuarioEntity createUsuario(UsuarioEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargado de eliminar un usuario por su id
     *
     * @param id id del consultorio
     * @return true si lo eliminó, false de lo contrario
     */
    public boolean deleteUsuario(Long id) {
        boolean deleted = false;
        persistence.delete(id);
        UsuarioEntity entity = persistence.find(id);

        //Se comprueba si se eliminó la entidad
        if (entity == null) {
            deleted = true;
        }
        return deleted;
    }
    
    /**
     * Método que retorna la lista de todos los usuarios
     *
     * @return Lista con todas las entidades de los usuarios
     */
    public List<UsuarioEntity> getUsuarios() {
        return persistence.findAll();
        
    }
    
    /**
     * Método que retorna un consultorio por su id
     *
     * @param id id del usuario
     * @return UsuarioEntity del usuario buscado
     */
    public UsuarioEntity getUsuario(Long id) {
        return persistence.find(id);
        
    }
    
    /**
     * Método encargado de actualizar la información de un consultorio
     *
     * @param entity Usuario con la nueva información
     * @return Entidad con la información del usuario actualizado
     */
    public UsuarioEntity updateUsuario(UsuarioEntity entity) {
        persistence.update(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un consultorio por su numero
     *
     * @param number número de cedula del usuario
     * @return UsuarioEntity correspondiente al usuario
     */
    public UsuarioEntity getUsuarioByCedula(int number) {
        return persistence.findByCedula(number);
    }
    
     /**
     * Busca el usuario con el id dado por parámetro
     * @param id
     * @return
     */
    public UsuarioEntity getUsuarioById( Long id ) {
        return persistence.find(id);
    }
}
