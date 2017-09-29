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
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jl.patarroyo
 */
@Path("/hospitales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class HospitalResource
{
    /**
     * Clase de la lógica
     */
    @Inject
    HospitalLogic logic;
    
    private List<HospitalDetailDTO> listEntity2DTO(List<HospitalEntity> listaEntidades){
        List<HospitalDetailDTO> list =  new ArrayList<>();
        for(HospitalEntity entity: listaEntidades){
            HospitalDetailDTO dto = new HospitalDetailDTO(entity);
            list.add(dto);
        }
        return list;
    }
    
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
     * @return HospitalDetailDTO del hospital
     */
    @GET
    @Path("{id: \\d+}")
    public HospitalDetailDTO getHospital(@PathParam("id") Long id)
    {
        HospitalEntity entity = logic.getHospital(id);
        if(entity == null){
            throw new WebApplicationException("El elemento no existe",404);
        }
        return new HospitalDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene todos los hospitales
     * @return Lista con todos los hospitales
     */
    @GET
    public List<HospitalDetailDTO> getHospitales()
    {
        List<HospitalEntity> lista = logic.getHospitales();
        return listEntity2DTO(lista);
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
