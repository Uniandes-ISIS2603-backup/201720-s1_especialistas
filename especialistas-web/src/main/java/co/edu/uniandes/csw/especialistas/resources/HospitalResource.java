/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.HospitalDTO;
import co.edu.uniandes.csw.especialistas.dtos.HospitalDetailDTO;
import co.edu.uniandes.csw.especialistas.dtos.UbicacionDTO;
import co.edu.uniandes.csw.especialistas.ejb.HospitalLogic;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.LinkedList;
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
 * Clase que modela el recurso de hospitales
 * @author jl.patarroyo
 */
@Path("hospitales")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class HospitalResource {
    
    /**
     * Injección de la lógica de hospitales
     */
    @Inject
    HospitalLogic logic;
    
    /**
     * Método encargado de obtener todos los hospitales
     * @return lista de hospitales
     */
    @GET
    public List<HospitalDTO> getHospitales(){
        try{
            List<HospitalEntity> listaEntidades = logic.getHospitales();
            List<HospitalDTO> listaHospitales = new ArrayList<>();
            for(HospitalEntity entity: listaEntidades){
                HospitalDTO dto = new HospitalDTO(entity);
                listaHospitales.add(dto);
            }
            return listaHospitales;
        }catch(BusinessLogicException e){
            throw new WebApplicationException(e.getMessage(), 404);
        }
    }
    
    @GET
    @Path("/{id: \\d+}")
    public HospitalDetailDTO getHospital(@PathParam("id") Long id){
        try{
            HospitalEntity entidad = logic.getHospital(id);
            HospitalDetailDTO hospital = new HospitalDetailDTO(entidad);
            return hospital;
        }catch(BusinessLogicException e){
            throw new WebApplicationException(e.getMessage(), 404);
        }
    }

}
