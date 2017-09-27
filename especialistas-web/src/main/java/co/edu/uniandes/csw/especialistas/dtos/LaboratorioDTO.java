/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.dtos;

import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;

/**
 *
 * @author dm.gutierrez11
 */
public class LaboratorioDTO {

    private Long id;
    private String nombre;
    private double radio;

    public LaboratorioDTO() {

    }

    /**
     * Convierte un Entity en un DTO
     *
     * @param laboratorio
     */
    public LaboratorioDTO(LaboratorioEntity laboratorio) {
        this.id = laboratorio.getId();
        this.nombre = laboratorio.getNombre();
        this.radio = laboratorio.getRadio();
    }

    /**
     * getters y setters
     **/
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

    public void setRadio(double radio){
        this.radio = radio;
    }
    
    public double getRadio(){
        return radio;
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
        laboratorio.setRadio(radio);
        return laboratorio;
    }

}
