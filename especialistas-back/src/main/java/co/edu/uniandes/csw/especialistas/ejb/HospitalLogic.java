/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.persistence.HospitalPersistence;
import exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que contiene la lógica de los hospitales
 *
 * @author jl.patarroyo
 */
@Stateless
public class HospitalLogic {

    /**
     * Injección de la persistencia
     */
    @Inject
    private HospitalPersistence persistence;

    /**
     * Método encargado de persistir un hospital nuevo
     *
     * @param entity Entidad del hospital
     * @return Hospital persistido
     * @throws BusinessLogicException
     */
    public HospitalEntity createHospital(HospitalEntity entity) throws BusinessLogicException {
        HospitalEntity hospital = getHospital(entity.getId());
        if (hospital == null) {
            persistence.create(entity);
            return entity;
        } else {
            throw new BusinessLogicException("Ya existe un hospital con el id");
        }

    }

    /**
     * Método encargadod de eliminar un hospital de la persistencia
     *
     * @param id Id del hospital
     */
    public void deleteHospital(Long id) {
        persistence.delete(id);
    }

    /**
     * Método que retorna la lista de todos los hospitales
     *
     * @return Lista con todas las entidades de los hospitales
     */
    public List<HospitalEntity> getHospitales() {
        List<HospitalEntity> lista = persistence.findAll();
        return lista;
    }

    /**
     * Método que retorna un hospital por su id
     *
     * @param id id del hospital
     * @return HospitalEntity del hospital buscado
     */
    public HospitalEntity getHospital(Long id) {
        HospitalEntity entity = persistence.find(id);
        return entity;
    }

    /**
     * Método encargado de actualizar la información de un hospital
     *
     * @param entity Hospital con la nueva información
     * @return Entidad con la información del hospital actualizado
     */
    public HospitalEntity updateHospital(HospitalEntity entity) {

        return persistence.upadte(entity);

    }

    /**
     * Método encargado de buscar un hospital por su nombre
     *
     * @param nombre nombre del hospital
     * @return HospitalEntity correspondiente al hospital
     * @throws BusinessLogicException
     */
    public HospitalEntity getHospitalByName(String nombre) throws BusinessLogicException {
        HospitalEntity entity = persistence.findByReference(nombre);
        if (entity != null) {
            return entity;
        } else {
            throw new BusinessLogicException("No existe un hospital con el nombre especificado");
        }
    }

    /**
     * Método encargado de listar los consultorios pertenecientes a un hospital
     *
     * @param id id del hospital
     * @return Lista de ConsultorioEntity
     * @throws BusinessLogicException
     */
    public List<ConsultorioEntity> listConsultorios(Long id) throws BusinessLogicException {
        HospitalEntity hospital = getHospital(id);
        if (hospital != null) {
            return hospital.getConsultorios();
        } else {
            throw new BusinessLogicException("No existe un hospital con el id especificado");
        }
    }

    /**
     * Método encargado de eliminar un consultorio que pertenece a un hospital
     *
     * @param idHospital id del hospital
     * @param idConsultorio id del consultorio
     * @throws BusinessLogicException
     */
    public void removeConsultorio(Long idHospital, Long idConsultorio) throws BusinessLogicException {

        HospitalEntity hospital = getHospital(idHospital);
        ConsultorioEntity consultorio = new ConsultorioEntity();
        consultorio.setId(idConsultorio);
        hospital.getConsultorios().remove(consultorio);
    }

    /**
     * Método encargado de retornar la ubicación de un hospital
     *
     * @param idHospital id del hospital
     * @return UbicaciónEntity con la información
     * @throws BusinessLogicException
     */
    public UbicacionEntity getUbicacionHospital(Long idHospital) throws BusinessLogicException {
        HospitalEntity hospital = getHospital(idHospital);
        return hospital.getUbicacion();
    }

    /**
     * Metodo encargado de agregar un consultorio a un hospital
     *
     * @param idHospital id del hospital
     * @param consultorio ConsultorioEntity con la información
     * @return ConsultorioEntity agregado
     * @throws BusinessLogicException
     */
    public ConsultorioEntity addConsultorio(Long idHospital, ConsultorioEntity consultorio) throws BusinessLogicException {
        HospitalEntity hospital = getHospital(idHospital);
        hospital.getConsultorios().add(consultorio);
        return consultorio;
    }

}
