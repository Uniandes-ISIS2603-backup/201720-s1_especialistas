/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.persistence.HoraPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author JuanSebastian
 */
@Stateless
public class HoraLogic {
               
    @Inject
    private HoraPersistence persistence;
    
    @Inject
    private ConsultorioLogic consultorioLogic;
    
    /**
     * Método encargado de persistir una hora nueva
     * @param entity Entidad de la hora
     * @return Hora persistida
     */
    public HoraEntity createHora(HoraEntity entity)
    {
        ConsultorioEntity c = entity.getConsultorio();
        entity.setConsultorio(c);
        persistence.create(entity);
        if(c != null){
            c = consultorioLogic.getConsultorio(c.getId());
            entity.setConsultorio(c);
        }
        return entity;
    }
    
    /**
     * Método encargado de eliminar una hora de la persistencia
     * @param id Id de la hora
     */
    public void deleteHora(Long id)
    {
        persistence.delete(id);
    }
    
    /**
     * Método que retorna la lista de todas las horas
     * @return Lista con todas las entidades de las horas
     */
    public List<HoraEntity> getHoras()
    {
        List<HoraEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna una hora por su id
     * @param id id de la hora
     * @return HoraEntity de la hora buscado
     */
    public HoraEntity getHora(Long id)
    {
        HoraEntity entity = persistence.find(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de una hora
     * @param id Id de la hora a modificarse
     * @param entity Hora con la nueva información
     * @return Entidad con la información de la hora actualizada
     */
    public HoraEntity updateHora(Long id, HoraEntity entity)
    {
        entity.setId(id);
        persistence.update(entity);
        return entity;
    }

}
