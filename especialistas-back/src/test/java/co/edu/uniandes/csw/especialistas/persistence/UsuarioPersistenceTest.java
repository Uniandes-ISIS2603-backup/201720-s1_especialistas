/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.CitaEntity;
import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;
import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ce.quintero
 */
@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {
    @Inject
    private UsuarioPersistence persistence;
    
    @Inject
    private TarjetaPersistence persistence2;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();
    
    public UsuarioPersistenceTest() {
    }
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
     private void clearData() {
        em.createQuery("delete from TarjetaEntity").executeUpdate();
        em.createQuery("delete from CitaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    
    
   
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 100; i++){
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try{
                utx.rollback();
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class UsuarioPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        
        CitaEntity newCita = new CitaEntity();
        newCita.setUsuario(newEntity);
        ArrayList citasarr = new ArrayList<CitaEntity>();
        
        newEntity.setCitas(citasarr);
        newEntity.addCita(newCita);
        
        TarjetaEntity newTarjeta = new TarjetaEntity();
        newTarjeta.setNumero(0001);
        newTarjeta.setUsuario(newEntity);
        
        newEntity.setTarjeta(newTarjeta);
        
        UsuarioEntity result = persistence.create(newEntity);
        
        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getCedula(), entity.getCedula());
        Assert.assertEquals(newEntity.getCitas(), entity.getCitas());
        Assert.assertEquals(newEntity.getPagos(), entity.getPagos());
        Assert.assertEquals(newEntity.getTarjeta(), entity.getTarjeta());
        Assert.assertEquals(newEntity.getTarjeta().getNumero(), entity.getTarjeta().getNumero());
        Assert.assertEquals(false, entity.equals(null));
        Assert.assertEquals(false, entity.equals(newTarjeta));
        Assert.assertEquals(true, entity.equals(entity));
        Assert.assertEquals(true, entity.getTarjeta().equals(newTarjeta));
        Assert.assertEquals(newEntity.hashCode(), entity.hashCode());
        Assert.assertEquals(newEntity.getNick(), entity.getNick());
        Assert.assertEquals(newEntity.getPass(), entity.getPass());
        Assert.assertEquals(newEntity.getRol(), entity.getRol());
        entity.setId(null);
        Assert.assertEquals(false, entity.equals(entity));
        Assert.assertEquals(false, entity.equals(null));
        Assert.assertEquals(false, newEntity.equals(entity));
        Assert.assertEquals(entity.hashCode(), entity.hashCode());
    }
    
    /**
     * Test of update method, of class UsuarioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        
        newEntity.setId(entity.getId());        
        persistence.update(newEntity);
        
        UsuarioEntity result = em.find(UsuarioEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(), result.getNombre());
        Assert.assertEquals(newEntity.getCedula(), result.getCedula());
        Assert.assertEquals(newEntity.getCitas(), result.getCitas());
        Assert.assertEquals(newEntity.getTarjeta(), result.getTarjeta());
    }
    
    /**
     * Test of delete method, of class UsuarioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        UsuarioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Test of find method, of class UsuarioPersistence.
     */
    @Test
    public void testFind() throws Exception {
        UsuarioEntity entity = data.get(0);
        
        UsuarioEntity result = persistence.find(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        Assert.assertEquals(entity.getCedula(), result.getCedula());
        Assert.assertEquals(entity.getCitas(), result.getCitas());
        Assert.assertEquals(entity.getTarjeta(), result.getTarjeta());
        Assert.assertEquals(entity, persistence.findByCedula(entity.getCedula()));
        Assert.assertEquals(null, persistence.findByCedula(314159265));
    }
    
    /**
     * Test of findAll method, of class UsuarioPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<UsuarioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(UsuarioEntity ent : list){
            boolean found = false;
            for(UsuarioEntity entity : data){
                if(ent.equals(entity)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
        
    }
}
