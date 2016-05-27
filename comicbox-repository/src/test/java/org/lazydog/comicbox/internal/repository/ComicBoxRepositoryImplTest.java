package org.lazydog.comicbox.internal.repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.weld.environment.se.Weld;
import org.junit.AfterClass;
import org.lazydog.comicbox.ComicBoxRepository;
import org.lazydog.comicbox.model.Actor;
import org.lazydog.comicbox.model.Category;
import org.lazydog.comicbox.model.Comic;
import org.lazydog.comicbox.model.ComicActor;
import org.lazydog.comicbox.model.Country;
import org.lazydog.comicbox.model.Creator;
import org.lazydog.comicbox.model.Distribution;
import org.lazydog.comicbox.model.Entity;
import org.lazydog.comicbox.model.Format;
import org.lazydog.comicbox.model.Grade;
import org.lazydog.comicbox.model.Inventory;
import org.lazydog.comicbox.model.Image;
import org.lazydog.comicbox.model.Imprint;
import org.lazydog.comicbox.model.Location;
import org.lazydog.comicbox.model.Person;
import org.lazydog.comicbox.model.Preference;
import org.lazydog.comicbox.model.Profession;
import org.lazydog.comicbox.model.Publisher;
import org.lazydog.comicbox.model.Title;
import org.lazydog.comicbox.model.Type;
import org.lazydog.comicbox.model.WantList;
import org.lazydog.repository.Criteria;
import org.lazydog.repository.criterion.Join;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.lazydog.repository.criterion.Comparison;
import org.lazydog.repository.jpa.bootstrap.Configuration;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_ORDER;


/**
 * Unit tests for ComicBoxRepositoryImpl class.
 *
 * @author  Ron Rickard
 */
@RunWith(Parameterized.class)
public class ComicBoxRepositoryImplTest {

    private static final String PERSISTENT_UNIT_NAME = "ComicBoxTest";
    private static final String SCHEMA = "comicbox";
    private static final String TEST_FILE = "META-INF/dataset.xml";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATE_STRING = "2001-01-01 00:00:00";
    private static ComicBoxRepository repository;
    private static Weld weld;
    private static Date testTime;
    private Class entityClass;
    private Entity entity1;
    private Entity entity2;
    private Criteria criteria;

    public ComicBoxRepositoryImplTest(Class newEntityClass) throws Exception {
        this.entityClass = newEntityClass;
        
        Image image1 = new Image();
        image1.setCreateTime(testTime);
        image1.setId(1);
        image1.setFileName("image1");
        image1.setUpdateTime(testTime);
        Image image2 = new Image();
        image2.setCreateTime(testTime);
        image2.setId(2);
        image2.setFileName("image2");
        image2.setUpdateTime(testTime);
                
        switch (entityClass.getSimpleName()) {
            case "Actor":
                Actor actor1 = new Actor();
                actor1.setCreateTime(testTime);
                actor1.setId(1);
                actor1.setImage(image1);
                actor1.setName("actor1");
                actor1.setUpdateTime(testTime);
                actor1.setVersion(1);
                this.entity1 = actor1;
                Actor actor2 = new Actor();
                actor2.setCreateTime(testTime);
                actor2.setId(2);
                actor2.setImage(image2);
                actor2.setName("actor2");
                actor2.setUpdateTime(testTime);
                actor2.setVersion(2);
                this.entity2 = actor2;
                Criteria<Actor> actorCriteria = repository.getCriteria(Actor.class);
                actorCriteria.add(Comparison.eq("id", 1));
                this.criteria = actorCriteria;
                break;
            case "Country":
                Country country1 = new Country();
                country1.setCreateTime(testTime);
                country1.setId(1);
                country1.setName("country1");
                country1.setUpdateTime(testTime);
                this.entity1 = country1;
                Country country2 = new Country();
                country2.setCreateTime(testTime);
                country2.setId(2);
                country2.setName("country2");
                country2.setUpdateTime(testTime);
                this.entity2 = country2;
                Criteria<Country> countryCriteria = repository.getCriteria(Country.class);
                countryCriteria.add(Comparison.eq("id", 1));
                this.criteria = countryCriteria;
                break;
            case "Image":
                this.entity1 = image1;
                this.entity2 = image2;
                Criteria<Image> imageCriteria = repository.getCriteria(Image.class);
                imageCriteria.add(Comparison.eq("id", 1));
                this.criteria = imageCriteria;
                break;
        }
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {{Actor.class}, {Country.class}, {Image.class}});
    }
    
    @AfterClass
    public static void afterClass() throws Exception {

        // Drop the database.
        try {
            DriverManager.getConnection("jdbc:derby:memory:./target/comicbox;drop=true");
        } catch (SQLNonTransientConnectionException e) {
            // Ignore.
        }
    }
    
    @BeforeClass
    public static void beforeClass() throws Exception {

        // Ensure the derby.log file is in the target directory.
        System.setProperty("derby.system.home", "./target");

        // Create the database.
        DriverManager.getConnection("jdbc:derby:memory:./target/comicbox;create=true");

        // Startup weld for processing CDI annotations.
        weld = new Weld();

        // Initialize the entity manager.
        Configuration.createEntityManager(PERSISTENT_UNIT_NAME);

        // Get the repository.
        repository = weld.initialize().instance().select(ComicBoxRepositoryImpl.class).get();
        
        testTime = DATE_FORMAT.parse(DATE_STRING);
    }

    @Before
    public void beforeTest() throws Exception {

        // Refresh the database.
        this.refreshDatabase();
        
        // Since the database was modified outside of the entity manager control, clear the cache.
        ((ComicBoxRepositoryImpl)repository).getEntityManager().clear();
        ((ComicBoxRepositoryImpl)repository).getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    private static double duration(Date startTime, Date endTime) {
        return (endTime.getTime() - startTime.getTime()) / 1000d;
    }

    private static <T> void findList(Class<T> entityClass) {

        List<T> entities;
        Date endTime;
        Date startTime;

        startTime = new Date();
        entities = repository.findList(entityClass);
        endTime = new Date();
        System.out.println(entities.size() + " " + entityClass.getSimpleName()
                + "s retrieved in " + duration(startTime, endTime)
                + " seconds");
    }

    @Test @Ignore
    public void findListComicEager() throws Exception {

        Criteria<Comic> criteria;
        List<Comic> entities;
        Date endTime;
        Date startTime;

        startTime = new Date();
        criteria = repository.getCriteria(Comic.class);
        criteria.add(Join.leftJoinFetch("comicCharacters"));
        criteria.add(Join.leftJoinFetch("creators"));
        criteria.add(Join.leftJoinFetch("traits"));
        entities = repository.findList(Comic.class, criteria);
        endTime = new Date();
        System.out.println(entities.size() + " " + Comic.class.getSimpleName()
                + "s retrieved in " + duration(startTime, endTime)
                + " seconds");
    }

    @Test
    public void findByCriteria() throws Exception {
        Entity expected = this.entity1;
        Entity actual = (Entity)repository.find(this.entityClass, this.criteria);
        
        assertReflectionEquals(expected, actual);
    }
    
    @Test
    public void findById() throws Exception {
        Entity expected = this.entity1;
        Entity actual = (Entity)repository.find(this.entityClass, 1);
        
        assertReflectionEquals(expected, actual);
    }
    
    @Test
    public void findList() throws Exception {
        List expected = new ArrayList();
        expected.add(this.entity1);
        expected.add(this.entity2);
        List actual = repository.findList(this.entityClass);
        
        assertReflectionEquals(expected, actual, LENIENT_ORDER);
    }
    
    @Test
    public void persist() throws Exception {
        //repository.persist(entity1);
    }
    
    @Test
    public void persistList() throws Exception {
        //repository.persistList(entity1);
    }
    
    @Test
    public void remove() throws Exception {
        //repository.remove(entity1);
    }
    
    @Test
    public void removeList() throws Exception {
        //repository.removeList(entity1);
    }

    @Test @Ignore
    public void findListTitleEager() throws Exception {

        Criteria<Title> criteria;
        List<Title> entities;
        Date endTime;
        Date startTime;

        startTime = new Date();
        criteria = repository.getCriteria(Title.class);
        criteria.add(Join.leftJoinFetch("categories"));
        criteria.add(Join.leftJoinFetch("publishers"));
        entities = repository.findList(Title.class, criteria);
        endTime = new Date();
        System.out.println(entities.size() + " " + Title.class.getSimpleName()
                + "s retrieved in " + duration(startTime, endTime)
                + " seconds");
    }

    private IDatabaseConnection getDatabaseConnection() throws Exception {
        return new DatabaseConnection(((ComicBoxRepositoryImpl)repository).getConnection(), SCHEMA);
    }
    
    private static IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream(TEST_FILE));
    }
    
    private void incrementId(IDatabaseConnection databaseConnection, String table) throws Exception {
        // Get the maximum ID from the table.
        Statement statement = databaseConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) as max_id FROM " + SCHEMA + "." + table);
        resultSet.next();
        int max = resultSet.getInt("max_id");
        resultSet.close();
        statement.close();
        
        // Set the table next generated Id value to the maximum ID plus 1.
        statement =  databaseConnection.getConnection().createStatement();
        statement.executeUpdate("ALTER TABLE " + SCHEMA + "." + table + " ALTER COLUMN id RESTART WITH " + (max + 1));
        statement.close();
    }
    
    private void refreshDatabase() throws Exception {
        
        // Get the database connection.
        IDatabaseConnection databaseConnection = this.getDatabaseConnection();
        
        // Refresh the dataset in the database.
        DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, getDataSet());

        this.incrementId(databaseConnection, "actor");
        this.incrementId(databaseConnection, "category");
        this.incrementId(databaseConnection, "comic");
        this.incrementId(databaseConnection, "comic_actor");
        this.incrementId(databaseConnection, "country");
        this.incrementId(databaseConnection, "creator");
        this.incrementId(databaseConnection, "distribution");
        this.incrementId(databaseConnection, "format");
        this.incrementId(databaseConnection, "grade");
        this.incrementId(databaseConnection, "image");
        this.incrementId(databaseConnection, "imprint");
        this.incrementId(databaseConnection, "inventory");
        this.incrementId(databaseConnection, "location");
        this.incrementId(databaseConnection, "person");
        this.incrementId(databaseConnection, "preference");
        this.incrementId(databaseConnection, "profession");
        this.incrementId(databaseConnection, "publisher");
        this.incrementId(databaseConnection, "title");
        this.incrementId(databaseConnection, "type");
        this.incrementId(databaseConnection, "want_list");
    }
}
