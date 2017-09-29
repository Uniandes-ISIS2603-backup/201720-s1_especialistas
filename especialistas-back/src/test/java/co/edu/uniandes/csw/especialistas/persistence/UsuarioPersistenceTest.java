/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

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
public class UsuarioPersistenceTest {
    @Inject
    private UsuarioPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<UsuarioEntity> data = new ArrayList<>();
    
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
        UsuarioEntity result = persistence.create(newEntity);
        
        assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getCedula(), entity.getCedula());
        assertEquals(newEntity.getCitas(), entity.getCitas());
        assertEquals(newEntity.getTarjeta(), entity.getTarjeta());
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
        
        assertEquals(newEntity.getNombre(), result.getNombre());
        assertEquals(newEntity.getCedula(), result.getCedula());
        assertEquals(newEntity.getCitas(), result.getCitas());
        assertEquals(newEntity.getTarjeta(), result.getTarjeta());
    }
    
    /**
     * Test of delete method, of class UsuarioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        UsuarioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        assertNull(deleted);
    }
    
    /**
     * Test of find method, of class UsuarioPersistence.
     */
    @Test
    public void testFind() throws Exception {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity result = persistence.find(entity.getId());
        assertNotNull(result);
        assertEquals(entity.getNombre(), result.getNombre());
        assertEquals(entity.getCedula(), result.getCedula());
        assertEquals(entity.getCitas(), result.getCitas());
        assertEquals(entity.getTarjeta(), result.getTarjeta());
    }
    
    /**
     * Test of findAll method, of class UsuarioPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<UsuarioEntity> list = persistence.findAll();
        assertEquals(data.size(), list.size());
        for(UsuarioEntity ent : list){
            boolean found = false;
            for(UsuarioEntity entity : data){
                if(ent.equals(entity)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
        
    }
}
