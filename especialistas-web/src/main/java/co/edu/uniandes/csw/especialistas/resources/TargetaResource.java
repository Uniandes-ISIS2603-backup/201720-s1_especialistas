/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.TarjetaDTO;
import co.edu.uniandes.csw.especialistas.dtos.TarjetaDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.TarjetaLogic;
import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;
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
@Path("tarjetas")
@Produces("application/json")
@Stateless
public class TargetaResource {
    @Inject
    TarjetaLogic logic;
    
    @POST
    public TarjetaEntity createTarjeta(TarjetaDTO tarjeta) throws Exception {
        TarjetaEntity tarjetaEntity = null;
        if(tarjeta.getId()!=null)
        {
            tarjetaEntity = logic.getTarjeta(tarjeta.getId());
        }
        if (tarjetaEntity != null) {
            Exception e = new Exception("Ya existe un usuario con el id " + tarjeta.getId());
            throw e;
        }
        TarjetaEntity nuevaTarjeta = logic.createTarjeta(tarjeta.toEntity());

        return nuevaTarjeta;
    }
    
    @GET
    public List<TarjetaDetailDTO> getTarjetas() throws Exception {
        List<TarjetaEntity> TarjetaEntities = logic.getTarjetas();
        if (TarjetaEntities.isEmpty()) {
            Exception e = new Exception("no hay Tarjetas");
            throw e;
        }
        List<TarjetaDetailDTO> tarjetaDTOs = new ArrayList<>();

        for (TarjetaEntity actual : TarjetaEntities) {
            TarjetaDetailDTO nuevoTarjeta= new TarjetaDetailDTO(actual);
            tarjetaDTOs.add(nuevoTarjeta);
        }
        return tarjetaDTOs;
    }
    
    @GET
    @Path("{id: \\d+}")
    public TarjetaDetailDTO getTarjetaByID(@PathParam("id") Long id) throws Exception {
        TarjetaEntity entity = logic.getTarjetaById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un Tarjeta con el id " + id);
            throw e;
        }
        return new TarjetaDetailDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public TarjetaDetailDTO updateTarjeta(@PathParam("id") Long id, TarjetaDetailDTO tarjeta) throws Exception {
        tarjeta.setId(id);
        TarjetaEntity entity = logic.getTarjetaById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un Tarjeta con el id " + id);
            throw e;
        }
        return new TarjetaDetailDTO(logic.updateTarjeta(tarjeta.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTarjeta(@PathParam("id") Long id) throws Exception {
        TarjetaEntity entity = logic.getTarjetaById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un Tarjeta con el id " + id);
            throw e;
        }
        logic.deleteTarjeta(id);
    }
}
