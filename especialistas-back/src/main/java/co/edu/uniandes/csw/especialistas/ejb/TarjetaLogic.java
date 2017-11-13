/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;
import co.edu.uniandes.csw.especialistas.persistence.TarjetaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ce.quintero
 */
@Stateless
public class TarjetaLogic {
    /**
     * Injección de la persistencia de Tarjeta
     */
    @Inject
    private TarjetaPersistence persistence;

    /**
     * Método encargado de crear un Tarjeta
     *
     * @param entity entidad con la información
     * @return entidad del Tarjeta creado
     */
    public TarjetaEntity createTarjeta(TarjetaEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargado de eliminar un Tarjeta por su id
     *
     * @param id id del consultorio
     * @return true si lo eliminó, false de lo contrario
     */
    public boolean deleteTarjeta(Long id) {
        boolean deleted = false;
        persistence.delete(id);
        TarjetaEntity entity = persistence.find(id);

        //Se comprueba si se eliminó la entidad
        if (entity == null) {
            deleted = true;
        }
        return deleted;
    }
    
    /**
     * Método que retorna la lista de todos los Tarjetas
     *
     * @return Lista con todas las entidades de los Tarjetas
     */
    public List<TarjetaEntity> getTarjetas() {
        return persistence.findAll();
        
    }
    
    /**
     * Método que retorna un consultorio por su id
     *
     * @param id id del Tarjeta
     * @return TarjetaEntity del Tarjeta buscado
     */
    public TarjetaEntity getTarjeta(Long id) {
        return persistence.find(id);
        
    }
    
    /**
     * Método encargado de actualizar la información de un consultorio
     *
     * @param entity Tarjeta con la nueva información
     * @return Entidad con la información del Tarjeta actualizado
     */
    public TarjetaEntity updateTarjeta(TarjetaEntity entity) {
        persistence.update(entity);
        return entity;
    }
    
     /**
     * Busca el Tarjeta con el id dado por parámetro
     * @param id
     * @return
     */
    public TarjetaEntity getTarjetaById( Long id ) {
        return persistence.find(id);
        
    }
}
