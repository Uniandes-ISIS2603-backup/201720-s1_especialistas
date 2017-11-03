/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.PagoEntity;
import co.edu.uniandes.csw.especialistas.entities.TarjetaEntity;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
public class PagoPersistenceTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private PagoPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PagoEntity> data = new ArrayList<>();
    
    public PagoPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
    
    
    private void clearData() {
        em.createQuery("delete from PagoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 100; i++){
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class PagoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        TarjetaEntity tarjeta = new TarjetaEntity();
        
        newEntity.setTarjeta(tarjeta);

        PagoEntity result = persistence.create(newEntity);
        
        assertNotNull(result);
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getRef(), entity.getRef());
        assertEquals(newEntity.getPai(), entity.getPai());
        assertEquals(newEntity.getPrecio(), entity.getPrecio());
        assertEquals(newEntity.getTarjeta(), entity.getTarjeta());
        
        Assert.assertEquals(false, entity.equals(null));
        Assert.assertEquals(false, entity.equals(tarjeta));
        Assert.assertEquals(true, entity.equals(entity));
        Assert.assertEquals(true, entity.getTarjeta().equals(tarjeta));
        
        Assert.assertEquals(newEntity.hashCode(), entity.hashCode());
        entity.setId(null);
        Assert.assertEquals(false, entity.equals(null));
        Assert.assertEquals(false, newEntity.equals(entity));
        Assert.assertEquals(entity.hashCode(), entity.hashCode());
    }
    
    /**
     * Test of update method, of class PagoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        PagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        
        newEntity.setId(entity.getId());        
        persistence.update(newEntity);
        
        PagoEntity result = em.find(PagoEntity.class, entity.getId());
        
        assertEquals(newEntity.getRef(), result.getRef());
    }
    
    /**
     * Test of delete method, of class PagoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        PagoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        assertNull(deleted);
    }
    
    /**
     * Test of find method, of class PagoPersistence.
     */
    @Test
    public void testFind() throws Exception {
        PagoEntity entity = data.get(0);
        PagoEntity result = persistence.find(entity.getId());
        assertNotNull(result);
        assertEquals(entity.getRef(), result.getRef());
    }
    
    /**
     * Test of findAll method, of class PagoPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<PagoEntity> list = persistence.findAll();
        assertEquals(data.size(), list.size());
        for(PagoEntity ent : list){
            boolean found = false;
            for(PagoEntity entity : data){
                if(ent.equals(entity)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
        
    }
}
