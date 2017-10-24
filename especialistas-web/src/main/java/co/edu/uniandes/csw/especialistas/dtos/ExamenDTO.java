/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dm.gutierrez11
 */
public class ExamenDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private String recomendacion;
    private List<LaboratorioDetailDTO> labs;

    public ExamenDTO() {

    }

    /**
     * Convierte un Entity en un DTO
     *
     * @param examen
     */
    public ExamenDTO(ExamenEntity examen) {
        this.id = examen.getId();
        this.nombre = examen.getnombre();
        this.precio = examen.getPrecio();
        this.recomendacion = examen.getRecomedacion();
        if(examen.getLaboratorios() == null){
            examen.setLaboratorios(new ArrayList<LaboratorioEntity>());
        }
        this.labs = labToDTO(examen.getLaboratorios());
    }

    //getters y setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getRecomendacion() {
        return this.recomendacion;
    }

    /**
     * Convierte un DTO a un Entity
     *
     * @return
     */
    public ExamenEntity toEntity() {
        ExamenEntity examen = new ExamenEntity();
        examen.setId(id);
        examen.setNombre(nombre);
        examen.setPrecio(precio);
        examen.setRecomedacion(recomendacion);
        return examen;
    }

    private List<LaboratorioDetailDTO> labToDTO(List<LaboratorioEntity> lista){
        List<LaboratorioDetailDTO> labs = new ArrayList<LaboratorioDetailDTO>();
        for( LaboratorioEntity a : lista)
            labs.add(new LaboratorioDetailDTO(a));
        return labs;
    }
}
