/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import javax.persistence.EntityManager;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import java.net.URI;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rc.tejon
 */
@Path("medicamentos")
public class MedicamentoResourse {


    public MedicamentoResourse() {
    }

    @POST

    public boolean create(MedicamentoEntity entity) {
         return false;
    }

    @PUT
    public void edit(MedicamentoEntity entity) {
        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
    
    }

    @GET
    @Path("{id}")
    public MedicamentoEntity find(@PathParam("id") Long id) {
        return null;
    }

    @GET
    public List<MedicamentoEntity> findAll() {
        return null;
    }
}
