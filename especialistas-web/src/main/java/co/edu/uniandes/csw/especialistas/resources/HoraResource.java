/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.HoraDTO;
import co.edu.uniandes.csw.especialistas.dtos.HoraDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.HoraLogic;
import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
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

/**
 * @author JuanSebastian
 */
@Path("horas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class HoraResource {

   /**
     * Clase de la lógica
     */
    @Inject
    HoraLogic logic;
    
    /**
     * Recurso que crea una hora
     * @param hora JSON con la información de la hora
     * @return JSON con la hora creada
     */
    @POST
    public HoraDetailDTO createHora(HoraDetailDTO hora)
    {
        HoraEntity entity = hora.toEntity();
        logic.createHora(entity);
        return new HoraDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene una hora por su id
     * @param id id de la hora
     * @return hora creada en formato JSON
     */
    @GET
    @Path("{id: \\d+}")
    public HoraDetailDTO getHora(@PathParam("id") Long id)
    {
        HoraEntity entity = logic.getHora(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /horas/" + id + " no existe.", 404);
        }
        return new HoraDetailDTO(entity);
    }
    
    /**
     * Recurso que obtiene todas las horas
     * @return Lista con todas las horas
     */
    @GET
    public List<HoraDetailDTO> getHoras()
    {
        List<HoraDetailDTO> lista = new ArrayList<>();
        logic.getHoras().forEach((hora) -> {
            lista.add(new HoraDetailDTO(hora));
        });
        return lista;
    }
    
    /**
     * Recurso para actualizar una hora
     * @param id Id de la hora a actualizar
     * @param hora JSON con los detalles de la hora
     * @return Hora actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public HoraDetailDTO updateHora(@PathParam("id") Long id, HoraDetailDTO hora)
    {
        HoraEntity newEntity = hora.toEntity();
        HoraEntity entity = logic.getHora(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /horas/" + id + " no existe.", 404);
        }
        return new HoraDetailDTO(logic.updateHora(id, newEntity));
    }
    
    /**
     * Recurso que elimina una hora
     * @param id id de la hora
     */
    @DELETE
    public void deleteHora(@PathParam("id") Long id)
    {
        HoraEntity entity = logic.getHora(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /horas/" + id + " no existe.", 404);
        }
        logic.deleteHora(id);
    }
}
