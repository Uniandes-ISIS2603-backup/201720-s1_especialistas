/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import co.edu.uniandes.csw.especialistas.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 */
@Entity
public class HosEntity implements Serializable {

    /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicadionID")
    private UbicacionEntity ubicacion;

    @PodamExclude
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ConsEntity> consultorios = new ArrayList<ConsEntity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ConsEntity> getConsultorios() {
        if (consultorios == null) {
            consultorios = new ArrayList<>();
        }
        return consultorios;
    }

    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void agregarConsultorio(ConsEntity con) {
        if (consultorios == null) {
            consultorios = new ArrayList<>();
        }
        consultorios.add(con);
    }

    public void eliminarConsultorio(ConsEntity con) throws BusinessLogicException {

        if (consultorios == null || !consultorios.contains(con)) {
            throw new BusinessLogicException("no existe el consultorio");
        }
        consultorios.remove(con);
    }

    public void setConsultorios(List<ConsEntity> consultorios) {
        this.consultorios = consultorios;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass() != this.getClass()) {
                return false;
            }
            if (this.getId() != null && ((HosEntity) obj).getId() != null) {
                return this.getId().equals(((HosEntity) obj).getId());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
