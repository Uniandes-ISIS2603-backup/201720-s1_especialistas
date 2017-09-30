/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.PagoDTO;
import co.edu.uniandes.csw.especialistas.dtos.PagoDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.PagoLogic;
import co.edu.uniandes.csw.especialistas.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
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
 * @author ce.quintero
 */
@Path("pagos")
@Produces("application/json")
@Stateless
public class PagoResource {
    @Inject
    PagoLogic logic;
    
    @POST
    public PagoEntity createPago(PagoDTO pago) throws Exception {
        PagoEntity tarjetaEntity = null;
        if(pago.getId()!=null)
        {
            tarjetaEntity = logic.getPago(pago.getId());
        }
        if (tarjetaEntity != null) {
            Exception e = new Exception("Ya existe un usuario con el id " + pago.getId());
            throw e;
        }
        PagoEntity nuevaTarjeta = logic.createPago(pago.toEntity());

        return nuevaTarjeta;
    }
    
    @GET
    public List<PagoDetailDTO> getPagos() throws Exception {
        List<PagoEntity> PagoEntities = logic.getPagos();
        if (PagoEntities.isEmpty()) {
            Exception e = new Exception("no hay Pagos");
            throw e;
        }
        List<PagoDetailDTO> pagoDTOs = new ArrayList<>();

        for (PagoEntity actual : PagoEntities) {
            PagoDetailDTO nuevoPago= new PagoDetailDTO(actual);
            pagoDTOs.add(nuevoPago);
        }
        return pagoDTOs;
    }
    
    @GET
    @Path("{id: \\d+}")
    public PagoDetailDTO getPagoByID(@PathParam("id") Long id) throws Exception {
        PagoEntity entity = logic.getPagoById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un Pago con el id " + id);
            throw e;
        }
        return new PagoDetailDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("id") Long id, PagoDetailDTO pago) throws Exception {
        pago.setId(id);
        PagoEntity entity = logic.getPagoById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un Pago con el id " + id);
            throw e;
        }
        return new PagoDetailDTO(logic.updatePago(pago.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id") Long id) throws Exception {
        PagoEntity entity = logic.getPagoById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un Pago con el id " + id);
            throw e;
        }
        logic.deletePago(id);
    }
}
