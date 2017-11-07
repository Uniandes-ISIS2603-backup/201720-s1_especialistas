/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.resources;

import co.edu.uniandes.csw.especialistas.dtos.ConsDTO;
import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDTO;
import co.edu.uniandes.csw.especialistas.dtos.FarmaciaDetailDTO;
import co.edu.uniandes.csw.especialistas.dtos.HosDetailDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDTO;
import co.edu.uniandes.csw.especialistas.ejb.FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.ejb.MedicamentoLogic;
import javax.persistence.EntityManager;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import java.net.URI;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDTO;
import co.edu.uniandes.csw.especialistas.dtos.MedicamentoDetailDTO;
import co.edu.uniandes.csw.especialistas.dtos.UbicacionDTO;
import co.edu.uniandes.csw.especialistas.ejb.ConsLogic;
import co.edu.uniandes.csw.especialistas.ejb.Cons_HosLogic;
import co.edu.uniandes.csw.especialistas.ejb.FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.ejb.HosLogic;
import co.edu.uniandes.csw.especialistas.ejb.Medicamento_FarmaciaLogic;
import co.edu.uniandes.csw.especialistas.entities.ConsEntity;
import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
import co.edu.uniandes.csw.especialistas.entities.HosEntity;
import co.edu.uniandes.csw.especialistas.entities.MedicamentoEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rc.tejon
 */
@Path("/hos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class HosResource {

    /**
     * Clase de la lógica de farmacia
     */
    @Inject
    HosLogic logic;

    @Inject
    ConsLogic logicConsultorio;

    /**
     * Clase de la logica de los metodos que comparten medicamento y faracia
     */
    @Inject
    Cons_HosLogic logicCH;

    /**
     * Recurso que crea una farmacia
     *
     * @param hospital JSON con la información de la farmacia
     * @return Entidad de la farmacia creada
     */
    @POST
    public HosDetailDTO createHospital(HosDetailDTO hospital) {
        HosEntity entity = hospital.toEntity();
        return new HosDetailDTO(logic.createHospital(entity));
    }

    /**
     * Recurso que obtiene una farmacia por su id
     *
     * @param id id del farmacia buscada
     * @return FarmaciaDetailDTO con la informacion de la farmacia
     * @throws BusinessLogicException si no existe la farmacia con el id dado
     */
    @GET
    @Path("{id: \\d+}")
    public HosDetailDTO getHospital(@PathParam("id") Long id) throws BusinessLogicException {
        HosEntity entity = logic.getHospital(id);

        if (entity == null) {
            throw new BusinessLogicException("no existe la entidad");
        }
        return new HosDetailDTO(entity);
    }

    /**
     * Recurso que obtiene la ubicación de una farmacia por su id
     *
     * @param id id de la farmacia
     * @return UbicacionEntity de la farmacia
     */
    @GET
    @Path("{id: \\d+}/ubicacion")
    public UbicacionDTO getUbicacionHospital(@PathParam("id") Long id) {
        UbicacionEntity entity = logic.getHospital(id).getUbicacion();
        return new UbicacionDTO(entity);
    }

    /**
     * Recurso para agregar un medicamento a una farmacia y viceversa
     *
     * @param id id de la farmacia
     * @param idCons id del medicamento
     * @return farmacia actualizado
     * @throws BusinessLogicException si no exite farmacia o medicamento con los
     * id dados
     */
    @PUT
    @Path("{id: \\d+}/consultorios/{idCons: \\d+}")
    public HosDetailDTO addConsultorioHospital(@PathParam("id") Long id, @PathParam("idCons") Long idCons) throws BusinessLogicException {
        HosEntity entity = logic.getHospital(id);
        if (!logicCH.agregarRelacion(id, idCons)) {
            throw new BusinessLogicException("no existe la entidad");
        }
        return new HosDetailDTO(logic.updateHospital(entity));
    }

    /**
     * Recurso para eliminar el medicamento de una farmacia y viceversa
     *
     * @param id id de la farmacia
     * @param idCons id del medicamento
     * @return farmacia actualizado
     * @throws BusinessLogicException si no existe la relación
     */
    @DELETE
    @Path("/{id: \\d+}/consultorios/{idCons: \\d+}")
    public HosDetailDTO deleteConsultorioHospital(@PathParam("id") Long id, @PathParam("idCons") Long idCons) throws BusinessLogicException {
        HosEntity entity = logic.getHospital(id);
        if (!logicCH.eliminarRelacion(id, idCons)) {
            throw new BusinessLogicException("no existe la entidad");
        }
        return new HosDetailDTO(logic.updateHospital(entity));
    }

    /**
     * retorna los medicamentos que vende una farmacia
     *
     * @param id id de la farmacia
     * @return la lista de medicamentos de la farmacia
     * @throws BusinessLogicException si no existe la farmacia con el id dado
     */
    @GET
    @Path("{id: \\d+}/consultorios")
    public List<ConsDTO> getConsultorioHospital(@PathParam("id") Long id) throws BusinessLogicException {
        HosEntity entity = logic.getHospital(id);
        if (entity == null) {
            throw new BusinessLogicException("no existe la entidad");
        }
        List<ConsDTO> list = new ArrayList<>();
        Iterator<ConsEntity> iter = entity.getConsultorios().iterator();
        while (iter.hasNext()) {
            list.add(new ConsDTO(iter.next()));
        }
        return list;
    }

    /**
     * Recurso que obtiene todos los hospitales
     *
     * @return Lista con todos los hospitales
     */
    @GET
    public List<HosDetailDTO> getHospitales() {
        return listToList(logic.getHospitales());

    }

    private List<HosDetailDTO> listToList(List<HosEntity> entityList) {
        List<HosDetailDTO> listDTO = new LinkedList<>();
        for (HosEntity entity : entityList) {
            listDTO.add(new HosDetailDTO(entity));
        }
        return listDTO;
    }

    /**
     * Recurso para actualizar un farmacia
     *
     * @param hospital JSON con los detalles del framacia
     * @return farmacia actualizado
     */
    @PUT
    public HosDetailDTO updateHospital(HosDetailDTO hospital) throws BusinessLogicException {
        HosEntity entity = hospital.toEntity();
        if (logic.getHospital(entity.getId()) == null) {
            throw new BusinessLogicException("no existe farmacia con el id dado");
        }
        return new HosDetailDTO(logic.updateHospital(entity));
    }

    /**
     * Recurso que elimina un farmacia
     *
     * @param id id del farmacia
     */
    @Path("{id:\\d+}")
    @DELETE
    public void deleteHospital(@PathParam("id") Long id) throws BusinessLogicException {
        logic.deleteHospital(id);
    }

}
