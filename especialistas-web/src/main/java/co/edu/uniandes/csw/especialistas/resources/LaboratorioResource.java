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

/**
 *
 * @author dm.gutierrez11
 */
@Path("laboratorios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LaboratorioResource {

    @Inject
    LaboratorioLogic logic;

    @POST
    public LaboratorioDetailDTO createLaboratorio(LaboratorioDetailDTO laboratorio) throws WebApplicationException {
        LaboratorioEntity laboratorioEntity = logic.getLaboratorio(laboratorio.getNombre());
        if (laboratorioEntity != null) {
            WebApplicationException e = new WebApplicationException("Ya existe un laboratorio con el nombre " + laboratorio.getNombre());
            throw e;
        }
        LaboratorioEntity nuevoLab = logic.createLaboratorio( laboratorio.toEntity());

        return new LaboratorioDetailDTO(nuevoLab);
    }

    @GET
    public List<LaboratorioDetailDTO> getLaboratorios() throws WebApplicationException {
        List<LaboratorioEntity> labEntities = logic.getLaboratorios();
        if (labEntities.isEmpty()) {
            WebApplicationException e = new WebApplicationException("no hay laboratorios");
            throw e;
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
    public LaboratorioDetailDTO getLaboratorioByName(@PathParam("nombre") String nombre) throws WebApplicationException {
        LaboratorioEntity entity = logic.getLaboratorio(nombre);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un laboratorio con el nombre " + nombre);
            throw e;
        }
        return new LaboratorioDetailDTO(entity);
    }

    @GET
    @Path("{id: \\d+}")
    public LaboratorioDetailDTO getLaboratorioByID(@PathParam("id") Long id) throws WebApplicationException {
        LaboratorioEntity entity = logic.getLaboratorioById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un laboratorio con el id " + id);
            throw e;
        }
        return new LaboratorioDetailDTO(entity);
    }

    @PUT
    @Path("{id: \\d+}")
    public LaboratorioDetailDTO updateLaboratorio(@PathParam("id") Long id, LaboratorioDetailDTO laboratorio) throws WebApplicationException {
        laboratorio.setId(id);
        LaboratorioEntity entity = logic.getLaboratorioById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un laboratorio con el id " + id);
            throw e;
        }
        return new LaboratorioDetailDTO(logic.updateLaboratorio(laboratorio.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteLaboratorio(@PathParam("id") Long id) throws WebApplicationException {
        LaboratorioEntity entity = logic.getLaboratorioById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un laboratorio con el id " + id);
            throw e;
        }
        logic.deleteLaboratorio(id);
    }
}
