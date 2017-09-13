/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

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
public class TarjetaPersistenceTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaEntity.class.getPackage())
                .addPackage(TarjetaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private TarjetaPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<TarjetaEntity> data = new ArrayList<>();
    
    public TarjetaPersistenceTest() {
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
        em.createQuery("delete from TarjetaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 100; i++){
            TarjetaEntity entity = factory.manufacturePojo(TarjetaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class TarjetaPersistence.
     */
    @Test
    public void createTarjetaTest() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);
        TarjetaEntity result = persistence.create(newEntity);
        
        assertNotNull(result);
        TarjetaEntity entity = em.find(TarjetaEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getNumero(), entity.getNumero());
    }
    
    /**
     * Test of update method, of class TarjetaPersistence.
     */
    @Test
    public void updateTarjetaTest() throws Exception {
        TarjetaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);
        
        newEntity.setId(entity.getId());        
        persistence.update(newEntity);
        
        TarjetaEntity result = em.find(TarjetaEntity.class, entity.getId());
        
        assertEquals(newEntity.getNumero(), result.getNumero());
    }
    
    /**
     * Test of delete method, of class TarjetaPersistence.
     */
    @Test
    public void deleteTarjetaTest() throws Exception {
        TarjetaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        TarjetaEntity deleted = em.find(TarjetaEntity.class, entity.getId());
        assertNull(deleted);
    }
    
    /**
     * Test of find method, of class TarjetaPersistence.
     */
    @Test
    public void getTarjetaTest() throws Exception {
        TarjetaEntity entity = data.get(0);
        TarjetaEntity result = persistence.find(entity.getId());
        assertNotNull(result);
        assertEquals(entity.getNumero(), result.getNumero());
    }
    
    /**
     * Test of findAll method, of class TarjetaPersistence.
     */
    @Test
    public void getTarjetasTest() throws Exception {
        List<TarjetaEntity> list = persistence.findAll();
        assertEquals(data.size(), list.size());
        for(TarjetaEntity ent : list){
            boolean found = false;
            for(TarjetaEntity entity : data){
                if(ent.equals(entity)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
        
    }
}
