/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
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

/**
 *
 * @author ce.quintero
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    @Inject
    UsuarioLogic logic;
    
    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) throws Exception {
        UsuarioEntity usuarioEntity = logic.getUsuario(usuario.getId());
        if (usuarioEntity != null) {
            Exception e = new Exception("Ya existe un usuario con el nombre " + usuario.getNombre());
            throw e;
        }
        UsuarioEntity nuevoUsuario = logic.createUsuario(usuarioEntity);

        return new UsuarioDetailDTO(nuevoUsuario);
    }
    
    @GET
    public List<UsuarioDetailDTO> getUsuarios() throws Exception {
        List<UsuarioEntity> usuarioEntities = logic.getUsuarios();
        if (usuarioEntities.isEmpty()) {
            Exception e = new Exception("no hay usuarios");
            throw e;
        }
        List<UsuarioDetailDTO> usuarioDTOs = new ArrayList<>();

        for (UsuarioEntity actual : usuarioEntities) {
            UsuarioDetailDTO nuevoUsuario= new UsuarioDetailDTO(actual);
            usuarioDTOs.add(nuevoUsuario);
        }
        return usuarioDTOs;
    }
    
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuarioByID(@PathParam("id") Long id) throws Exception {
        UsuarioEntity entity = logic.getUsuarioById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un usuario con el id " + id);
            throw e;
        }
        return new UsuarioDetailDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario) throws Exception {
        usuario.setId(id);
        UsuarioEntity entity = logic.getUsuarioById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un usuario con el id " + id);
            throw e;
        }
        return new UsuarioDetailDTO(logic.updateUsuario(usuario.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws Exception {
        UsuarioEntity entity = logic.getUsuarioById(id);
        if (entity == null) {
            Exception e = new Exception("No existe un usuario con el id " + id);
            throw e;
        }
        logic.deleteUsuario(id);
    }
}
