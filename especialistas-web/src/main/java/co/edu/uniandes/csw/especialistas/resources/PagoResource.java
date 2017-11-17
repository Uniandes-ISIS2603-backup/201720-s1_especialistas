/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.PagoDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.PagoLogic;
import co.edu.uniandes.csw.especialistas.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import org.springframework.util.Assert;

/**
 *
 * @author ce.quintero
 */
@Path("pagos")
@Produces("application/json")
@Stateless
public class PagoResource {
    
    
    private final PagoLogic logic;
    
     public PagoResource(){
        logic = null;
    }
    
    @Inject
    public PagoResource(PagoLogic logic){
        Assert.notNull(logic, "MyCollaborator must not be null!");
        this.logic = logic;
    }
    
    @POST
    public PagoEntity createPago(PagoDetailDTO pago) {
        PagoEntity pagoEntity = null;
        if(pago.getId()!=null)
        {
            pagoEntity = logic.getPago(pago.getId());
        }
        if (pagoEntity != null) {
            throw new WebApplicationException("Ya existe un usuario con el id " + pago.getId());
            
        }

        return logic.createPago(pago.toEntity());
    }
    
    @GET
    public List<PagoDetailDTO> getPagos() {
        List<PagoEntity> pagoEntities = logic.getPagos();
        if (pagoEntities.isEmpty()) {
            throw new WebApplicationException("no hay Pagos");
            
        }
        List<PagoDetailDTO> pagoDTOs = new ArrayList<>();

        for (PagoEntity actual : pagoEntities) {
            PagoDetailDTO nuevoPago= new PagoDetailDTO(actual);
            pagoDTOs.add(nuevoPago);
        }
        return pagoDTOs;
    }
    
    @GET
    @Path("{id: \\d+}")
    public PagoDetailDTO getPagoByID(@PathParam("id") Long id) {
        PagoEntity entity = logic.getPagoById(id);
        if (entity == null) {
            throw new WebApplicationException("No existe un Pago con el id " + id);
        }
        return new PagoDetailDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("id") Long id, PagoDetailDTO pago) {
        pago.setId(id);
        PagoEntity entity = logic.getPagoById(id);
        if (entity == null) {
            throw new WebApplicationException("No existe un Pago con el id " + id);
        }
        return new PagoDetailDTO(logic.updatePago(pago.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id") Long id) {
        PagoEntity entity = logic.getPagoById(id);
        if (entity == null) {
            throw new WebApplicationException("No existe un Pago con el id " + id);
        }
        logic.deletePago(id);
    }
}
