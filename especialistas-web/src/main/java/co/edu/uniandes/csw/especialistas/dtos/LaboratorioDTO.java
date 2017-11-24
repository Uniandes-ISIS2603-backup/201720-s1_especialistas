/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dm.gutierrez11
 */
public class LaboratorioDTO {

    private Long id;
    private String nombre;
    private UbicacionDTO ubicacion;
    private List<ExamenDetailDTO> examenes;

    public LaboratorioDTO() {
        //inicialmente vacio
    }

    /**
     * Convierte un Entity en un DTO
     *
     * @param laboratorio
     */
    public LaboratorioDTO(LaboratorioEntity laboratorio) {
        this.id = laboratorio.getId();
        this.nombre = laboratorio.getNombre();
        UbicacionEntity lugar =laboratorio.getUbicacion();
        if(lugar != null){
            ubicacion = new UbicacionDTO(lugar);
        }
        
        if(laboratorio.getExamenes() == null){
            laboratorio.setExamenes(new ArrayList<>());
        }
        else{
         this.examenes = examsToDTO(laboratorio.getExamenes());}
        
    }

    /**
     * getters y setters
     *
     */
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

    public List<ExamenDetailDTO> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<ExamenDetailDTO> examenes) {
        this.examenes = examenes;
    }
    
    public UbicacionDTO getUbicacion(){
        return ubicacion;
    }
    
    public void setUbicacion(UbicacionDTO ubicacion){
        this.ubicacion = ubicacion;
    }

    /**
     * Convierte un DTO a un Entity
     *
     * @return
     */
    public LaboratorioEntity toEntity() {
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setId(id);
        laboratorio.setNombre(nombre);
        laboratorio.setUbicacion(this.ubicacion.toEntity());
        if(examenes == null ){
            examenes = new ArrayList<>();
        }
        else {
        laboratorio.setExamenes(examsToEntity(examenes));
    }
        
        
        
        return laboratorio;
    }

    public List<ExamenEntity> examsToEntity(List<ExamenDetailDTO> lista) {
        List<ExamenEntity> exams = new ArrayList<>();
        for (ExamenDetailDTO a : lista) {
            exams.add(a.toEntity());
        }
        return exams;
    }

    public List<ExamenDetailDTO> examsToDTO(List<ExamenEntity> lista) {
        List<ExamenDetailDTO> exams= new ArrayList<>();
        for (ExamenEntity a : lista) {
            ExamenDetailDTO detail = new ExamenDetailDTO();
            detail.setId(a.getId());
            detail.setNombre(a.getnombre());
            detail.setPrecio(a.getPrecio());
            detail.setRecomendacion(a.getRecomedacion());
            //detail.setLaboratorios(laboratorios);
            exams.add(detail);
        }
        return exams;
    }

}
