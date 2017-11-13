/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.logic;

import co.edu.uniandes.csw.especialistas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.especialistas.entities.UsuarioEntity;
import co.edu.uniandes.csw.especialistas.persistence.UsuarioPersistence;
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
public class UsuarioLogicTest {
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private UsuarioPersistence persistence;
    
    @Inject
    private UsuarioLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<UsuarioEntity> data = new ArrayList<>();
    
    public UsuarioLogicTest() {
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 10; i++){
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    private UsuarioEntity entity = new UsuarioEntity();

    /**
     * Test of create method, of class UsuarioPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        entity = new UsuarioEntity();
        logic.createUsuario(entity);
        
        assertEquals(logic.getUsuario(entity.getId()), entity);
        assertEquals(logic.getUsuarioById(entity.getId()), entity);
    }
    
    /**
     * Test of update method, of class UsuarioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        entity = new UsuarioEntity();
        logic.createUsuario(entity);
        entity.setNombre("nombre");
        assertEquals(logic.updateUsuario(entity), entity);
    }
    
    /**
     * Test of findAll method, of class UsuarioPersistence.
     */
    @Test
    public void testGet() throws Exception {
        entity = new UsuarioEntity();
        logic.createUsuario(entity);
        assertEquals(logic.getUsuarios().contains(entity), true);
    }
    
    /**
     * Test of delete method, of class UsuarioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        entity = new UsuarioEntity();
        logic.createUsuario(entity);
        logic.deleteUsuario(entity.getId());
        assertEquals(logic.getUsuario(entity.getId()), null);
    }
    
}
