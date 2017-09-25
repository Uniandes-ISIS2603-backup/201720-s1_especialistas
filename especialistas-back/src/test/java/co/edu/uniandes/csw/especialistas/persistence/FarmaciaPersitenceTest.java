/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.FarmaciaEntity;
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
 * @author rc.tejon
 */
@RunWith(Arquillian.class)
public class FarmaciaPersitenceTest {
    
    @PersistenceContext
    private EntityManager em;


    @Inject
    UserTransaction utx;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FarmaciaEntity.class.getPackage())
                .addPackage(FarmaciaPersitence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public FarmaciaPersitenceTest() {
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
    
    @After
    public void tearDown() {
    }

   
    @Inject
    private FarmaciaPersitence medicamentoPersistence;

    private void clearData() {
        em.createQuery("delete from FarmaciaEntity").executeUpdate();
    }

    private List<FarmaciaEntity> data = new ArrayList<FarmaciaEntity>();

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 100; i++) {
            FarmaciaEntity entity = factory.manufacturePojo(FarmaciaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createFarmaciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FarmaciaEntity newEntity = factory.manufacturePojo(FarmaciaEntity.class);
        FarmaciaEntity result = medicamentoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FarmaciaEntity entity = em.find(FarmaciaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
     
    }

    /**
     * Prueba para consultar la lista de Companys.
     *
     *
     */
    @Test
    public void getFarmaciasTest() {
        List<FarmaciaEntity> list = medicamentoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FarmaciaEntity ent : list) {
            boolean found = false;
            for (FarmaciaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Company.
     *
     *
     */
    @Test
    public void getFarmaciaTest() {
        FarmaciaEntity entity = data.get(0);
        FarmaciaEntity newEntity = medicamentoPersistence.findById(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
      
    }


    @Test
    public void deleteFarmaciaTest() {
        FarmaciaEntity entity = data.get(0);
        boolean b=medicamentoPersistence.deleteById(entity.getId());
        FarmaciaEntity deleted = em.find(FarmaciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateFarmaciaTest() {
        FarmaciaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FarmaciaEntity newEntity = factory.manufacturePojo(FarmaciaEntity.class);

        newEntity.setId(entity.getId());

        medicamentoPersistence.update(newEntity);

        FarmaciaEntity resp = em.find(FarmaciaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
      
    }
    
}
