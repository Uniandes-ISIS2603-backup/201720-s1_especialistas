/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.Especializacion;
import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;
import co.edu.uniandes.csw.especialistas.persistence.MedicoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author JuanSebastian
 */
@Stateless
public class MedicoLogic {
           
    @Inject
    private MedicoPersistence persistence;
    
    /**
     * Método encargado de persistir un medico nuevo
     * @param entity Entidad del medico
     * @return Medico persistido
     */
    public MedicoEntity createMedico(MedicoEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Método encargado de eliminar un medico de la persistencia
     * @param id Id del medico
     */
    public void deleteMedico(Long id)
    {
        persistence.delete(id);
        MedicoEntity entity = persistence.find(id);
    }
    
    /**
     * Método que retorna la lista de todos los medicos
     * @return Lista con todas las entidades de los medicos
     */
    public List<MedicoEntity> getMedicos()
    {
        List<MedicoEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Método que retorna un medico por su id
     * @param id id del medico
     * @return MedicoEntity del medico buscado
     */
    public MedicoEntity getMedico(Long id)
    {
        MedicoEntity entity = persistence.find(id);
        return entity;
    }
    
    /**
     * Método encargado de actualizar la información de un medico
     * @param id Id del medico a actualizar
     * @param entity Medico con la nueva información
     * @return Entidad con la información del medico actualizado
     */
    public MedicoEntity updateMedico(Long id, MedicoEntity entity)
    {
        entity.setId(id);
        persistence.update(entity);
        return entity;
    }
    
    /**
     * Método encargado de buscar un medico por su nombre
     * @param nombre nombre del medico
     * @return  MedicoEntity correspondiente al medico
     */
    public MedicoEntity getMedicoByNombre(String nombre)
    {
        return persistence.findByNombre(nombre);
    }
    
    /**
     * Método encargado de buscar los medicos de una especializacion
     * @param especializacion especializacion del medico
     * @return  MedicoEntity correspondiente al medico
     */
    public List<MedicoEntity> getMedicosByEspecializacion(String especializacion)
    {
        return persistence.findByEspecializacion(Enum.valueOf(Especializacion.class, especializacion));
    }
    
    
}
