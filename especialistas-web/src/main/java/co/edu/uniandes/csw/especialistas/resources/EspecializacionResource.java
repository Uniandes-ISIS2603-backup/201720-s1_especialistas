/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.entities.Especializacion;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author JuanSebastian
 */
@Path("especializaciones")
@Produces("application/json")
@Stateless
public class EspecializacionResource {
        
    /**
     * Recurso que obtiene los nombres de todas las especializaciones
     * @return Lista con todas las especializaciones
     */
    @GET
    public List<Especializacion> getEspecializaciones()
    {
        return Arrays.asList(Especializacion.values());
    }

}
