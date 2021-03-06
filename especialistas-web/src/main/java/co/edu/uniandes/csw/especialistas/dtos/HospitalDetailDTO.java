/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ConsultorioEntity;
import co.edu.uniandes.csw.especialistas.entities.HospitalEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela el DetailDTO de un hospital
 *
 * @author jl.patarroyo
 */
public class HospitalDetailDTO extends HospitalDTO {

    /**
     * Atributo que modela los consultorios del hospital
     */
    private List<ConsultorioDTO> consultorios;

    /**
     * Constructor vacío
     */
    public HospitalDetailDTO() {
        //VACÍO
    }

    /**
     * Constructor del DTO a partir de una entidad
     *
     * @param entity entidad con la información del hospital
     */
    public HospitalDetailDTO(HospitalEntity entity) {
        super(entity);
        List<ConsultorioEntity> listaEntidades = entity.getConsultorios();
        consultorios = new ArrayList<>();
        if (listaEntidades != null) {
            for (ConsultorioEntity entidad : listaEntidades) {
                ConsultorioDTO dto = new ConsultorioDTO(entidad);
                consultorios.add(dto);
            }
        }
    }

    /**
     * Setter del atributo consultorios
     *
     * @return lista de consultorios del hospital
     */
    public List<ConsultorioDTO> getConsultorios() {
        return consultorios;
    }

    /**
     * Setter del atributo consultorios
     *
     * @param consultorios lista de consultorios
     */
    public void setConsultorios(List<ConsultorioDTO> consultorios) {
        this.consultorios = consultorios;
    }

    /**
     * Método encargado de convertir el actual DTO a una entidad
     *
     * @return entidad con la información del hospital
     */
    @Override
    public HospitalEntity toEntity() {
        HospitalEntity entidadHospital = super.toEntity();
        List<ConsultorioEntity> listaConsultorios = new ArrayList<>();
        if (consultorios != null) {
            for (ConsultorioDTO dto : consultorios) {
                ConsultorioEntity entidad = dto.toEntity();
                listaConsultorios.add(entidad);
            }
            entidadHospital.setConsultorios(listaConsultorios);
        }
        return entidadHospital;
    }
}
