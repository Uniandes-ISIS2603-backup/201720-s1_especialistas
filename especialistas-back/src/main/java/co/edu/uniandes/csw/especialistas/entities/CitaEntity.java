/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jr.restom10
 */
@Entity
public class CitaEntity implements Serializable{
        
     /**
     * Id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
         private String comentarios;
         
         @PodamExclude
         @OneToOne()
         
         private HoraEntity hora;
         
         @PodamExclude
         @OneToMany(mappedBy="cita")
         
         private List <OrdenMedicaEntity> ordenesMedicas;
        
         @PodamExclude
         @ManyToOne(fetch=FetchType.LAZY)
         
         private UsuarioEntity usuario;
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
         
         public void setHora(HoraEntity s)
        {
            hora=s;
        }
        
         public HoraEntity getHora()
        {
            return hora;
        }
         
         public void setOrdenesMedicas(List <OrdenMedicaEntity> s)
        {
            ordenesMedicas=s;
        }
        
         public List <OrdenMedicaEntity> getOrdenesMedicas()
        {
            return ordenesMedicas;
        }
         
        public void setUsuario(UsuarioEntity s)
        {
            usuario=s;
        }
        
         public UsuarioEntity getUsuario()
        {
            return usuario;
        }
         
        public void setComentarios(String s)
        {
            comentarios=s;
        }
        
         public String getComentarios()
        {
            return comentarios;
        }

         @Override
    public boolean equals(Object obj) 
    {
        if(obj != null)
        {
            if(obj.getClass()!=this.getClass()){
                return false;
            }
            if (this.getId() != null && ((CitaEntity)obj).getId() != null) {
                return this.getId().equals(((CitaEntity)obj).getId());
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
