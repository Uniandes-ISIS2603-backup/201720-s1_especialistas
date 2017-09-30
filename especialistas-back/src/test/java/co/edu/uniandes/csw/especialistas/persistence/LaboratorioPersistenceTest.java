/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.especialistas.persistence;

import co.edu.uniandes.csw.especialistas.entities.ExamenEntity;
import co.edu.uniandes.csw.especialistas.entities.LaboratorioEntity;
import co.edu.uniandes.csw.especialistas.entities.UbicacionEntity;
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
public class LaboratorioPersistenceTest {

    @Inject
    private LaboratorioPersistence persistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<LaboratorioEntity> data = new ArrayList<LaboratorioEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LaboratorioEntity.class.getPackage())
                .addPackage(LaboratorioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public LaboratorioPersistenceTest() {
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
        em.createQuery("delete from LaboratorioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            LaboratorioEntity entity = factory.manufacturePojo(LaboratorioEntity.class);
            UbicacionEntity ubicacion = factory.manufacturePojo(UbicacionEntity.class);
            
            ExamenEntity examen = factory.manufacturePojo(ExamenEntity.class);
            ExamenEntity examen1 = factory.manufacturePojo(ExamenEntity.class);
            ExamenEntity examen2 = factory.manufacturePojo(ExamenEntity.class);
            List<ExamenEntity> examenes = new ArrayList<>();
            examenes.add(examen);
            examenes.add(examen1);
            examenes.add(examen2);
            
            entity.setExamenes(examenes);
            entity.setUbicacion(ubicacion);
            em.persist(entity);
            em.persist(ubicacion);
            em.persist(examen);
            em.persist(examen1);
            em.persist(examen2);
            
            data.add(entity);
        }
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LaboratorioEntity newEntity = factory.manufacturePojo(LaboratorioEntity.class);
        LaboratorioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        LaboratorioEntity entity = em.find(LaboratorioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test
    public void findAllTest() {
        List<LaboratorioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (LaboratorioEntity ent : list) {
            boolean found = false;
            for (LaboratorioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void findTest() {
        LaboratorioEntity entity = data.get(0);
        LaboratorioEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    @Test
    public void findByNameTest() {
        LaboratorioEntity entity = data.get(0);
        LaboratorioEntity newEntity = persistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    @Test
    public void updateTest() {
        LaboratorioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LaboratorioEntity newEntity = factory.manufacturePojo(LaboratorioEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        LaboratorioEntity resp = em.find(LaboratorioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(),resp.getNombre());
    }
    
    @Test
    public void deleteTest(){
        LaboratorioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        LaboratorioEntity deleted = em.find(LaboratorioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @After
    public void tearDown() {
    }

}
