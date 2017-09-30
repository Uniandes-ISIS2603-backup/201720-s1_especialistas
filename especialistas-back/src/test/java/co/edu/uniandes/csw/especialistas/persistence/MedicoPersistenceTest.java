/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.MedicoEntity;
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
public class MedicoPersistenceTest {
    
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicoEntity.class.getPackage())
                .addPackage(MedicoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private MedicoPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<MedicoEntity> data = new ArrayList<>();
    
    public MedicoPersistenceTest() {
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
        em.createQuery("delete from MedicoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 100; i++){
            MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class MedicoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        MedicoEntity result = persistence.create(newEntity);
        
        assertNotNull(result);
        MedicoEntity entity = em.find(MedicoEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Test of update method, of class MedicoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        MedicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        
        newEntity.setId(entity.getId());        
        persistence.update(newEntity);
        
        MedicoEntity result = em.find(MedicoEntity.class, entity.getId());
        
        assertEquals(newEntity.getNombre(), result.getNombre());
    }

    /**
     * Test of delete method, of class MedicoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        MedicoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        MedicoEntity deleted = em.find(MedicoEntity.class, entity.getId());
        assertNull(deleted);
    }

    /**
     * Test of find method, of class MedicoPersistence.
     */
    @Test
    public void testFind() throws Exception {
        MedicoEntity entity = data.get(0);
        MedicoEntity result = persistence.find(entity.getId());
        assertNotNull(result);
        assertEquals(entity.getNombre(), result.getNombre());
    }

    /**
     * Test of findByNombre method, of class MedicoPersistence.
     */
    @Test
    public void testFindByNombre() throws Exception {
        MedicoEntity entity = data.get(0);
        MedicoEntity result = persistence.findByNombre(entity.getNombre());
        assertNotNull(result);
        assertEquals(entity.getNombre(), result.getNombre());
    }

    /**
     * Test of findAll method, of class MedicoPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<MedicoEntity> list = persistence.findAll();
        assertEquals(data.size(), list.size());
        for(MedicoEntity ent : list){
            boolean found = false;
            for(MedicoEntity entity : data){
                if(ent.equals(entity)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
        
    }
    
}
