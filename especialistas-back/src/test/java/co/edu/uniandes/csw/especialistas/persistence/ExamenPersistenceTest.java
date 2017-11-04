/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
import java.util.ArrayList;
import java.util.List;
import javassist.Modifier;
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
 * @author dm.gutierrez11
 */
@RunWith(Arquillian.class)
public class ExamenPersistenceTest {

    @Inject
    private ExamenPersistence persistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ExamenEntity> data = new ArrayList<ExamenEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ExamenEntity.class.getPackage())
                .addPackage(ExamenPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public ExamenPersistenceTest() {
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
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from ExamenEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ExamenEntity entity = factory.manufacturePojo(ExamenEntity.class);
            
            LaboratorioEntity laboratorio = factory.manufacturePojo(LaboratorioEntity.class);
            LaboratorioEntity laboratorio1 = factory.manufacturePojo(LaboratorioEntity.class);
            LaboratorioEntity laboratorio2 = factory.manufacturePojo(LaboratorioEntity.class);
            List<LaboratorioEntity> labs = new ArrayList<>();
            labs.add(laboratorio);
            labs.add(laboratorio1);
            
            em.persist(entity);
            em.persist(laboratorio);
            em.persist(laboratorio1);
            em.persist(laboratorio2);
            
            data.add(entity);
        }
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ExamenEntity newEntity = factory.manufacturePojo(ExamenEntity.class);
        ExamenEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ExamenEntity entity = em.find(ExamenEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getnombre(), entity.getnombre());
        Assert.assertEquals(newEntity.getLaboratorios(), entity.getLaboratorios());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getRecomedacion(), entity.getRecomedacion());
        
        
        Assert.assertEquals(false, entity.equals(null));
        Assert.assertEquals(false, entity.equals("objet"));
        Assert.assertEquals(true, entity.equals(entity)); 
        
        Assert.assertEquals(newEntity.hashCode(), entity.hashCode());
        entity.setId(null);
        Assert.assertEquals(false, entity.equals(entity));
        Assert.assertEquals(false, entity.equals(null));
        Assert.assertEquals(false, newEntity.equals(entity));
        Assert.assertEquals(entity.hashCode(), entity.hashCode());
    }

    @Test
    public void findAllTest() {
        List<ExamenEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ExamenEntity ent : list) {
            boolean found = false;
            for (ExamenEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void findTest() {
        ExamenEntity entity = data.get(0);
        ExamenEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getnombre(), newEntity.getnombre());
    }

    @Test
    public void findByNameTest() {
        ExamenEntity entity = data.get(0);
        ExamenEntity newEntity = persistence.findByName(entity.getnombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getnombre(), newEntity.getnombre());
    }

    @Test
    public void updateTest() {
        ExamenEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ExamenEntity newEntity = factory.manufacturePojo(ExamenEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        ExamenEntity resp = em.find(ExamenEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getnombre(), resp.getnombre());
    }

    @Test
    public void deleteTest() {
        ExamenEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ExamenEntity deleted = em.find(ExamenEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @After
    public void tearDown() {
    }

}
