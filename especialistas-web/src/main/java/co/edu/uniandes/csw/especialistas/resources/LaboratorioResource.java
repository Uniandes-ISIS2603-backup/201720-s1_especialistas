/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.LaboratorioDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.LaboratorioLogic;
import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.WebApplicationException;
import org.springframework.util.Assert;

/**
 *
 * @author dm.gutierrez11
 */
@Path("laboratorios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LaboratorioResource {

    
    private final LaboratorioLogic logic;

    public LaboratorioResource(){
        logic = null;
    }
    
    @Inject
    public LaboratorioResource(LaboratorioLogic logic){
        Assert.notNull(logic, "logic must not be null!");
        this.logic = logic;
    }
    
    @POST
    public LaboratorioDetailDTO createLaboratorio(LaboratorioDetailDTO laboratorio){
        LaboratorioEntity laboratorioEntity = logic.getLaboratorio(laboratorio.getNombre());
        if (laboratorioEntity != null) {
            throw new WebApplicationException("Ya existe un laboratorio con el nombre " + laboratorio.getNombre());
            
        }
        LaboratorioEntity nuevoLab = logic.createLaboratorio( laboratorio.toEntity());

        return new LaboratorioDetailDTO(nuevoLab);
    }
    @GET
    public List<LaboratorioDetailDTO> getLaboratorios(){
        List<LaboratorioEntity> labEntities = logic.getLaboratorios();
        if (labEntities.isEmpty()) {
            throw new WebApplicationException("no hay laboratorios");
            
        }
        List<LaboratorioDetailDTO> labDTOs = new ArrayList<>();

        for (LaboratorioEntity actual : labEntities) {
            LaboratorioDetailDTO nuevoLaboratorio = new LaboratorioDetailDTO(actual);
            labDTOs.add(nuevoLaboratorio);
        }
        return labDTOs;
    }

    @GET
    @Path("{nombre}")
    public LaboratorioDetailDTO getLaboratorioByName(@PathParam("nombre") String nombre){
        LaboratorioEntity entity = logic.getLaboratorio(nombre);
        if (entity == null) {
            throw new WebApplicationException("1 No existe un laboratorio con el nombre " + nombre);
            
        }
        return new LaboratorioDetailDTO(entity);
    }

    @GET
    @Path("{id: \\d+}")
    public LaboratorioDetailDTO getLaboratorioByID(@PathParam("id") Long id){
        LaboratorioEntity entity = logic.getLaboratorioById(id);
        if (entity == null) {
            throw new WebApplicationException("2 No existe un laboratorio con el id " + id);
        }
        return new LaboratorioDetailDTO(entity);
    }

    @PUT
    @Path("{id: \\d+}")
    public LaboratorioDetailDTO updateLaboratorio(@PathParam("id") Long id, LaboratorioDetailDTO laboratorio){
        laboratorio.setId(id);
        LaboratorioEntity entity = logic.getLaboratorioById(id);
        if (entity == null) {
            throw new WebApplicationException("3 No existe un laboratorio con el id " + id);
            
        }
        return new LaboratorioDetailDTO(logic.updateLaboratorio(laboratorio.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteLaboratorio(@PathParam("id") Long id){
        LaboratorioEntity entity = logic.getLaboratorioById(id);
        if (entity == null) {
            throw new WebApplicationException("4 No existe un laboratorio con el id " + id);
            
        }
        logic.deleteLaboratorio(id);
    }
}
