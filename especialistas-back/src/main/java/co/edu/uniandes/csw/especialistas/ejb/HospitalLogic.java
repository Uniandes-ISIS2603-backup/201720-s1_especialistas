/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.ejb;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.persistence.UbicacionPersistence;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.especialistas.persistence.ConsultorioPersistence;
import co.edu.uniandes.csw.especialistas.persistence.HospitalPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que modela la lógica de los hospitales
 *
 * @author jl.patarroyo
 */
@Stateless
public class HospitalLogic {

    /**
     * Injección de la persistencia de hospitales
     */
    @Inject
    private HospitalPersistence persistence;

    /**
     * Inyección de la lógica de consultorios
     */
    @Inject
    ConsultorioLogic consultorioLogic;

    /**
     * Injección de la persistencia de ubicaciones
     */
    @Inject
    UbicacionPersistence up;

    /**
     * Injección de la persistencia de consultorios
     */
    @Inject
    ConsultorioPersistence consultorioPersistence;

    /**
     * Método encargado de crear un hospital
     *
     * @param entity entidad del hospital que se va a crear
     * @return entidad creada
     * @throws BusinessLogicException si ya existe un hospital con el nombre que
     * tiene la entidad
     */
    public HospitalEntity createHospital(HospitalEntity entity) throws BusinessLogicException {
        up.create(entity.getUbicacion());
        HospitalEntity hospital = persistence.findByName(entity.getNombre());
        if (hospital == null) {
            persistence.create(entity);
            return entity;
        } else {
            throw new BusinessLogicException("Ya existe un hospital con el nombre '" + hospital.getNombre() + "'");
        }
    }

    /**
     * Método encargado de eliminar un hospital
     *
     * @param id id del hospital que se quiere eliminar
     * @return null si fue eliminado, el objeto de lo contrario
     * @throws BusinessLogicException si no existe un hospital con el id
     * proporcionado
     */
    public HospitalEntity deleteHospital(Long id) throws BusinessLogicException {
        HospitalEntity hospital = getHospital(id);
        if (hospital == null) {
            throw new BusinessLogicException("No se puede eliminar el hospital con id " + id + " porque no existe");
        } else {
            persistence.deleteById(id);
            return persistence.findById(id);
        }
    }

    /**
     * Método que obtiene todos los hospitales
     *
     * @return lista con los hospitales
     * @throws BusinessLogicException si no hay hospitales
     */
    public List<HospitalEntity> getHospitales() throws BusinessLogicException {
        List<HospitalEntity> lista = persistence.findAll();
        if (lista == null || lista.isEmpty()) {
            throw new BusinessLogicException("No hay hospitales registrados en el sistema");
        } else {
            return persistence.findAll();
        }
    }

    /**
     * Método que obtiene un hospital por su id
     *
     * @param id id del hospital buscado
     * @return hospital buscado
     * @throws BusinessLogicException si no existe un hospital con el id
     * proporcionado
     */
    public HospitalEntity getHospital(Long id) throws BusinessLogicException {
        HospitalEntity hospital = persistence.findById(id);
        if (hospital == null) {
            throw new BusinessLogicException("1 No existe un hospital con el id " + id);
        } else {
            return hospital;
        }

    }

    /**
     * Método encargado de actualizar la información de un hospital
     *
     * @param entity entidad actualizada
     * @return
     */
    public HospitalEntity updateHospital(HospitalEntity entity) {
        persistence.update(entity);
        return entity;
    }

    /**
     * Método encargado de buscar un hospital por su nombre
     *
     * @param nombre nombre del hospital
     * @return hospital buscado
     * @throws BusinessLogicException si no existe un hospital con el nombre
     * proporcionado
     */
    public HospitalEntity getHospitalByName(String nombre) throws BusinessLogicException {
        HospitalEntity hospital = persistence.findByName(nombre);
        if (hospital == null) {
            throw new BusinessLogicException("2 No existe un hospital con el nombre '" + nombre + "'");
        } else {
            return hospital;
        }
    }

    /**
     * Método encargado de añadir un consultorio a un hospital
     *
     * @param idHospital id del hospital
     * @param consultorio entidad del consultorio
     * @return entidad del hospital actualizada
     * @throws BusinessLogicException si no existe un hospital con el id
     * proporcionado
     */
    public HospitalEntity addConsultorio(Long idHospital, ConsultorioEntity consultorio) throws BusinessLogicException {
        HospitalEntity hospital = persistence.findById(idHospital);
        if (hospital == null) {
            throw new BusinessLogicException("3 No existe un hospital con el id " + idHospital);
        } else {
            consultorio.setHospital(hospital);
            hospital.getConsultorios().add(consultorio);
            consultorioLogic.createConsultorio(consultorio);
            persistence.update(hospital);
            return hospital;
        }
    }

    public HospitalEntity deleteConsultorio(Long idHospital, Long idConsultorio) throws BusinessLogicException{
        HospitalEntity hospital = persistence.findById(idHospital);
        if (hospital == null) {
            throw new BusinessLogicException("4 No existe un hospital con el id " + idHospital);
        } else {
            ConsultorioEntity consultorio = consultorioLogic.getConsultorio(idConsultorio);
            hospital.getConsultorios().remove(consultorio);
            consultorioLogic.deleteConsultorio(idConsultorio);
            persistence.update(hospital);
            return hospital;
        }
    }
}
