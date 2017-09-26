/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.persistence.ConsultorioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase de la lógica de los consultorios
 
 * @author jl.patarroyo
 */
@Stateless
public class ConsultorioLogic {

    /**
     * Injección de la persistencia de consultorios
     */
    @Inject
    private ConsultorioPersistence persistence;

    /**
     * Método encargado de crear un consultorio
     *
     * @param entity entidad con la información
     * @return entidad del consultorio creado
     */
    public ConsultorioEntity createConsultorio(ConsultorioEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Método encargado de eliminar un consultorio por su id
     *
     * @param id id del consultorio
     * @return true si lo eliminó, false de lo contrario
     */
    public boolean deleteConsultorioEntity(Long id) {
        boolean deleted = false;
        persistence.delete(id);
        ConsultorioEntity entity = persistence.find(id);

        //Se comprueba si se eliminó la entidad
        if (entity == null) {
            deleted = true;
        }
        return deleted;
    }

    /**
     * Método que retorna la lista de todos los consultorios
     *
     * @return Lista con todas las entidades de los consultorios
     */
    public List<ConsultorioEntity> getConsultorios() {
        List<ConsultorioEntity> lista = persistence.findAll();
        return lista;
    }

    /**
     * Método que retorna un consultorio por su id
     *
     * @param id id del consultorio
     * @return ConsultorioEntity del consultorio buscado
     */
    public ConsultorioEntity getConsultorio(Long id) {
        ConsultorioEntity entity = persistence.find(id);
        return entity;
    }

    /**
     * Método encargado de actualizar la información de un consultorio
     *
     * @param entity Consultorio con la nueva información
     * @return Entidad con la información del consultorio actualizado
     */
    public ConsultorioEntity updateConsultorio(ConsultorioEntity entity) {
        persistence.upadte(entity);
        return entity;
    }

    /**
     * Método encargado de buscar un consultorio por su numero
     *
     * @param number número del consultorio
     * @return ConsultorioEntity correspondiente al consultorio
     */
    public ConsultorioEntity getConsultorioByNumber(String number) {
        ConsultorioEntity entity = persistence.findByReference(number);
        return entity;
    }

    /**
     * Método encargado de listar las horas pertenecientes a un consultorio
     *
     * @param id id del consultorio
     * @return lista de HoraEntity
     */
    public List<HoraEntity> listHoras(Long id) {
        ConsultorioEntity consultorio = getConsultorio(id);
        List<HoraEntity> horas = consultorio.getHoras();
        return horas;
    }

    /**
     * Método encargado de retornar el hospital al que pertenece el consultorio
     *
     * @param id id del consultorio
     * @return HospiatlEntity con la información del hospital
     */
    public HospitalEntity getHospital(Long id) {
        ConsultorioEntity consultorio = getConsultorio(id);
        return consultorio.getHospital();
    }

    /**
     * Método encargado de eliminar una hora de un consultorio
     * @param idConsultorio id del consultorio
     * @param idHora id de la hora
     * @return true si la eliminó, false de lo contrario
     */
    public boolean removeHora(Long idConsultorio, Long idHora) {
        try {
            ConsultorioEntity consultorio = getConsultorio(idConsultorio);
            HoraEntity hora = new HoraEntity();
            hora.setId(idHora);
            consultorio.getHoras().remove(hora);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Método encargado de añadir una hora a un consultorio
     * @param idConsultorio id del consultorio
     * @param hora HoraEntity para añadir
     * @return HoraEntity añadido
     */
    public HoraEntity addHora(Long idConsultorio, HoraEntity hora)
    {
        ConsultorioEntity consultorio = getConsultorio(idConsultorio);
        consultorio.getHoras().add(hora);
        return hora;
    }
}
