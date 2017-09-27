/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import co.edu.uniandes.csw.especialistas.ejb.CitaLogic;
import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.dtos.CitaDTO;


/**
 *
 * @author jr.restom10
 */

@Path("citas")
@Produces("application/json")

public class CitaResource {
    
    
    @Inject 
    CitaLogic logic;
    
    /**
     * Recurso que crea un cita
     * @param cita JSON con la información del Cita
     * @return Entidad del Cita creado
     */
    @POST
    public CitaEntity createCita(CitaDTO cita)
    {
        CitaEntity entity = cita.toEntity();
        logic.createCita(entity);
        return entity;
    }
    
    /**
     * Recurso que obtiene un Cita por su id
     * @param id id del Cita
     * @return CitaEntity del Cita
     */
    @GET
    @Path("{id: \\d+}")
    public CitaEntity getCita(@PathParam("id") Long id)
    {
        CitaEntity entity = logic.getCita(id);
        return entity;
    }
    
    /**
     * Recurso que obtiene todos los Citas
     * @return Lista con todos los Citas
     */
    @GET
    public List<CitaEntity> getCitas()
    {
        List<CitaEntity> lista = logic.getCitas();
        return lista;
    }
    
    /**
     * Recurso para actualizar un Cita
     * @param cita JSON con los detalles del Cita
     * @return Cita actualizado
     */
    @PUT
    public CitaEntity updateCita(CitaDTO cita)
    {
        CitaEntity entity = cita.toEntity();
        return logic.updateCita(entity);
    }
    
    /**
     * Recurso que elimina un Cita
     * @param id id del Cita
     * @return true si se eliminó el Cita, false de lo contrario
     */
    @DELETE
    public boolean deleteCita(@PathParam("id") Long id)
    {
        return logic.deleteCita(id);
    }
    
    
    
}
