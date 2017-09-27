/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.PagoEntity;
import co.edu.uniandes.csw.especialistas.persistence.PagoPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ce.quintero
 */
public class PagoLogic {
    /**
     * Injección de la persistencia de Pago
     */
    @Inject
    private PagoPersistence persistence;

    /**
     * Método encargado de crear un Pago
     *
     * @param entity entidad con la información
     * @return entidad del Pago creado
     */
    public PagoEntity createPago(PagoEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargado de eliminar un Pago por su id
     *
     * @param id id del consultorio
     * @return true si lo eliminó, false de lo contrario
     */
    public boolean deletePago(Long id) {
        boolean deleted = false;
        persistence.delete(id);
        PagoEntity entity = persistence.find(id);

        //Se comprueba si se eliminó la entidad
        if (entity == null) {
            deleted = true;
        }
        return deleted;
    }
    
    /**
     * Método que retorna la lista de todos los Pagos
     *
     * @return Lista con todas las entidades de los Pagos
     */
    public List<PagoEntity> getPagos() {
        List<PagoEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna un consultorio por su id
     *
     * @param id id del Pago
     * @return PagoEntity del Pago buscado
     */
    public PagoEntity getPago(Long id) {
        PagoEntity entity = persistence.find(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de un consultorio
     *
     * @param entity Pago con la nueva información
     * @return Entidad con la información del Pago actualizado
     */
    public PagoEntity updatePago(PagoEntity entity) {
        persistence.update(entity);
        return entity;
    }
    
     /**
     * Busca el Pago con el id dado por parámetro
     * @param id
     * @return
     */
    public PagoEntity getPagoById( Long id ) {
        PagoEntity examen = persistence.find(id);
        return examen;
    }
}
