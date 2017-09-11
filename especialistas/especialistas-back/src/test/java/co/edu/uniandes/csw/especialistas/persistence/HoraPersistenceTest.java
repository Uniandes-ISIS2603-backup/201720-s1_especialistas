/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.HoraEntity;
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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author js.diaz
 */
@RunWith(Arquillian.class)
public class HoraPersistenceTest {
    
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HoraEntity.class.getPackage())
                .addPackage(HoraPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private HoraPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<HoraEntity> data = new ArrayList<>();
    
    public HoraPersistenceTest() {
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
        em.createQuery("delete from HoraEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 100; i++){
            HoraEntity entity = factory.manufacturePojo(HoraEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class HoraPersistence.
     */
    @Test
    public void createHoraTest() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        HoraEntity newEntity = factory.manufacturePojo(HoraEntity.class);
        HoraEntity result = persistence.create(newEntity);
        
        assertNotNull(result);
        HoraEntity entity = em.find(HoraEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getHoraInicio(), entity.getHoraInicio());
    }

    /**
     * Test of update method, of class HoraPersistence.
     */
    @Test
    public void updateHoraTest() throws Exception {
        HoraEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HoraEntity newEntity = factory.manufacturePojo(HoraEntity.class);
        
        newEntity.setId(entity.getId());        
        persistence.update(newEntity);
        
        HoraEntity result = em.find(HoraEntity.class, entity.getId());
        
        assertEquals(newEntity.getHoraInicio(), result.getHoraInicio());
    }

    /**
     * Test of delete method, of class HoraPersistence.
     */
    @Test
    public void deleteHoraTest() throws Exception {
        HoraEntity entity = data.get(0);
        persistence.delete(entity.getId());
        HoraEntity deleted = em.find(HoraEntity.class, entity.getId());
        assertNull(deleted);
    }

    /**
     * Test of find method, of class HoraPersistence.
     */
    @Test
    public void getHoraTest() throws Exception {
        HoraEntity entity = data.get(0);
        HoraEntity result = persistence.find(entity.getId());
        assertNotNull(result);
        assertEquals(entity.getHoraInicio(), result.getHoraInicio());
    }

    /**
     * Test of findAll method, of class HoraPersistence.
     */
    @Test
    public void getHorasTest() throws Exception {
        List<HoraEntity> list = persistence.findAll();
        assertEquals(data.size(), list.size());
        for(HoraEntity ent : list){
            boolean found = false;
            for(HoraEntity entity : data){
                if(ent.equals(entity)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
        
    }
    
}
