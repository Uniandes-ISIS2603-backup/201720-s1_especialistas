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

/**
 *
 * @author ce.quintero
 */
@Path("usuarios")
@Produces("application/json")
@Stateless
public class UsuarioResource {
    @Inject
    UsuarioLogic logic;
    
    @POST
    public UsuarioEntity createUsuario(UsuarioDetailDTO usuario) {
        UsuarioEntity usuarioEntity = null;
        if(usuario.getId()!=null)
        {
            usuarioEntity = logic.getUsuario(usuario.getId());
        }
        if (usuarioEntity != null) {
            WebApplicationException e = new WebApplicationException("Ya existe un usuario con el id " + usuario.getId());
            throw e;
        }
        UsuarioEntity nuevoUsuario = logic.createUsuario(usuario.toEntity());

        return nuevoUsuario;
    }
    
    @GET
    public List<UsuarioDetailDTO> getUsuarios(){
        List<UsuarioEntity> usuarioEntities = logic.getUsuarios();
        if (usuarioEntities.isEmpty()) {
            WebApplicationException e = new WebApplicationException("no hay usuarios");
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
    public UsuarioDetailDTO getUsuarioByID(@PathParam("id") Long id){
        UsuarioEntity entity = logic.getUsuarioById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un usuario con el id " + id);
            throw e;
        }
        return new UsuarioDetailDTO(entity);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario){
        usuario.setId(id);
        UsuarioEntity entity = logic.getUsuarioById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un usuario con el id " + id);
            throw e;
        }
        return new UsuarioDetailDTO(logic.updateUsuario(usuario.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id){
        UsuarioEntity entity = logic.getUsuarioById(id);
        if (entity == null) {
            WebApplicationException e = new WebApplicationException("No existe un usuario con el id " + id);
            throw e;
        }
        logic.deleteUsuario(id);
    }
}
