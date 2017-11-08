
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.ConsultorioDTO;
import co.edu.uniandes.csw.especialistas.dtos.HospitalDTO;
import co.edu.uniandes.csw.especialistas.dtos.HospitalDetailDTO;
import co.edu.uniandes.csw.especialistas.ejb.ConsultorioLogic;
import co.edu.uniandes.csw.especialistas.ejb.HospitalLogic;
import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jl.patarroyo
 */
@Path("/hospitales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class HospitalResource {

    /**
     * Clase de la lógica
     */
    @Inject
    HospitalLogic logic;

    @Inject
    ConsultorioLogic consultorioLogic;

    private List<HospitalDTO> listEntity2DTO(List<HospitalEntity> listaEntidades) {
        List<HospitalDTO> list = new ArrayList<>();
        for (HospitalEntity entity : listaEntidades) {
            HospitalDetailDTO dto = new HospitalDetailDTO(entity);
            list.add(dto);
        }
        return list;
    }

    /**
     * Recurso que crea un hospital
     *
     * @param hospital JSON con la información del hospital
     * @return Entidad del hospital creado
     * @throws
     * co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException
     */
    @POST
    public HospitalDetailDTO createHospital(HospitalDetailDTO hospital) throws BusinessLogicException {
        HospitalEntity entity = hospital.toEntity();
        System.out.println("nombre: " + entity.getNombre() + " consultorios vacíos: " + entity.getConsultorios().isEmpty());
        HospitalEntity creado = logic.createHospital(entity);
        return new HospitalDetailDTO(creado);
    }

    /**
     * Recurso que obtiene un hospital por su id
     *
     * @param id id del hospital
     * @return HospitalDetailDTO del hospital
     */
    @GET
    @Path("{id: \\d+}")
    public HospitalDetailDTO getHospital(@PathParam("id") Long id) {
        HospitalEntity entity = logic.getHospital(id);
        if (entity == null) {
            throw new WebApplicationException("El elemento no existe", 404);
        }
        return new HospitalDetailDTO(entity);
    }

    /**
     * Recurso que obtiene todos los hospitales
     *
     * @return Lista con todos los hospitales
     */
    @GET
    public List<HospitalDTO> getHospitales() {
        List<HospitalEntity> lista = logic.getHospitales();
        return listEntity2DTO(lista);
    }

    /**
     * Recurso para actualizar un hospital
     *
     * @param hospital JSON con los detalles del hospital
     * @return Hospital actualizado
     */
    @PUT
    public HospitalDTO updateHospital(HospitalDTO hospital) {
        HospitalEntity newEntity = hospital.toEntity();
        HospitalEntity entity = logic.getHospital(newEntity.getId());
        if (entity == null) {
            throw new WebApplicationException("El elemento no existe", 404);
        }
        return new HospitalDTO(logic.updateHospital(newEntity));
    }

    /**
     * Recurso que elimina un hospital
     *
     * @param id id del hospital
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteHospital(@PathParam("id") Long id) {
        HospitalEntity hospital = logic.getHospital(id);
        if (hospital == null) {
            throw new WebApplicationException("El elemento no existe", 404);
        }
        logic.deleteHospital(id);
    }

    @GET
    @Path("{id: \\d+}/consultorios")
    public List<ConsultorioDTO> getConsultorios(@PathParam("id") Long idHospital) {
        ArrayList<ConsultorioDTO> respuesta = new ArrayList<>();
        try {
            List<ConsultorioEntity> consultorios = logic.listConsultorios(idHospital);
            for (ConsultorioEntity consultorio : consultorios) {
                ConsultorioDTO nuevo = new ConsultorioDTO(consultorio);
                respuesta.add(nuevo);
            }
        } catch (BusinessLogicException b) {
            throw new WebApplicationException("El hospital solicitado no existe", 404);
        }
        return respuesta;
    }

    @POST
    @Path("{id: \\d+}/consultorios")
    public HospitalDetailDTO addConsultorio(@PathParam("id") Long idHospital, ConsultorioDTO pConsultorio) {
        ConsultorioEntity buscado = consultorioLogic.getConsultorio(pConsultorio.getId());
        if (buscado == null) {
            try {
                ConsultorioEntity consultorio = pConsultorio.toEntity();
                consultorioLogic.createConsultorio(consultorio);
                logic.addConsultorio(idHospital, consultorio);

            } catch (BusinessLogicException b) {
                throw new WebApplicationException("No se pudo agregar el consultorio", 500);
            }
        } else {
            try {
                logic.addConsultorio(idHospital, buscado);
            } catch (BusinessLogicException b) {
                throw new WebApplicationException("No se pudo agregar el consultorio", 500);
            }
        }
        return new HospitalDetailDTO(logic.getHospital(idHospital));
    }
}
