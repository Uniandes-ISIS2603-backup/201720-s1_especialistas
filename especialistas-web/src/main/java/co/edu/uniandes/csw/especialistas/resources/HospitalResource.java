/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.HospitalDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.HospitalLogic;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jl.patarroyo
 */
@Path("/hospitales")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class HospitalResource
{
    /**
     * Clase de la lógica
     */
    @Inject
    HospitalLogic logic;
    
    /**
     * Recurso que crea un hospital
     * @param hospital JSON con la información del hospital
     * @return Entidad del hospital creado
     * @throws exceptions.BusinessLogicException
     */
    @POST
    public HospitalEntity createHospital(HospitalDetailDTO hospital) throws BusinessLogicException
    {
        HospitalEntity entity = hospital.toEntity();
        logic.createHospital(entity);
        return entity;
    }
    
    /**
     * Recurso que obtiene un hospital por su id
     * @param id id del hospital
     * @return HospitalEntity del hospital
     * @throws exceptions.BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public HospitalEntity getHospital(@PathParam("id") Long id) throws BusinessLogicException
    {
        HospitalEntity entity = logic.getHospital(id);
        return entity;
    }
    
    /**
     * Recurso que obtiene todos los hospitales
     * @return Lista con todos los hospitales
     */
    @GET
    public List<HospitalEntity> getHospitales()
    {
        List<HospitalEntity> lista = logic.getHospitales();
        return lista;
    }
    
    /**
     * Recurso para actualizar un hospital
     * @param hospital JSON con los detalles del hospital
     * @return Hospital actualizado
     * @throws exceptions.BusinessLogicException
     */
    @PUT
    public HospitalEntity updateHospital(HospitalDetailDTO hospital) throws BusinessLogicException
    {
        HospitalEntity entity = hospital.toEntity();
        return logic.updateHospital(entity);
    }
    
    /**
     * Recurso que elimina un hospital
     * @param id id del hospital
     * @return true si se eliminó el hospital, false de lo contrario
     * @throws exceptions.BusinessLogicException
     */
    @DELETE
    public boolean deleteHospital(@PathParam("id") Long id) throws BusinessLogicException
    {
        return logic.deleteHospital(id);
    }
}
