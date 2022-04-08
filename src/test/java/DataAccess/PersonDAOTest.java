package DataAccess;

import Model.Event;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {

    private ConnectionManager db;
    private Person bestPerson;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();
        bestPerson = new Person("test_personID_1", "test_associatedUsername_1",
                "test_first_name", "test_last_name", "test_fatherID",
                "test_motherID", "test_spouseID", "test_gender");
        Connection conn = db.getConnection();
        pDao = new PersonDAO(conn);
        pDao.clear();
    }

    @AfterEach
    public void tearDown() { db.closeConnection(false); }

    @Test
    public void personInsertPass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void personInsertFail() throws DataAccessException {
        pDao.insert(bestPerson);
        assertThrows(DataAccessException.class, () -> pDao.insert(bestPerson));
    }

    @Test
    public void personFindPass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void personFindFail() throws DataAccessException {
        Person compareTest = pDao.find("no_such_personID");
        assertNull(compareTest);
    }

    @Test
    public void personClearPass() throws DataAccessException {
        pDao.insert(bestPerson);
        assertNotNull(pDao.find(bestPerson.getPersonID()));
        pDao.clear();
        assertNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void personClearAnotherPass() throws DataAccessException {
        pDao.clear();
        assertNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void updateNamePass() throws DataAccessException {
        pDao.insert(bestPerson);
        bestPerson.setFirstName("new_first");
        bestPerson.setLastName("new_last");
        pDao.updateName(bestPerson);
        assertSame(bestPerson.getFirstName(), "new_first");
        assertSame(bestPerson.getLastName(), "new_last");
    }

    @Test
    public void updateNameAnotherPass() throws DataAccessException {
        pDao.insert(bestPerson);
        bestPerson.setFirstName("another_first");
        bestPerson.setLastName("another_last");
        pDao.updateName(bestPerson);
        assertSame(bestPerson.getFirstName(), "another_first");
        assertSame(bestPerson.getLastName(), "another_last");
    }

    @Test
    public void updateSpouseIDPass() throws DataAccessException {
        Person goodPerson = new Person("test_personID_2", "test_associatedUsername_1",
                "first_name", "last_name", "fatherID",
                "motherID", "noID", "f");
        pDao.insert(bestPerson);
        pDao.insert(goodPerson);
        pDao.updateSpouseID(bestPerson,goodPerson);
        pDao.updateSpouseID(goodPerson,bestPerson);
        assertEquals(pDao.find(goodPerson.getPersonID()).getSpouseID(), bestPerson.getPersonID());
        assertEquals(pDao.find(bestPerson.getPersonID()).getSpouseID(), goodPerson.getPersonID());
    }

    @Test
    public void updateSpouseIDAnotherPass() throws DataAccessException {
        Person goodPerson = new Person("test_personID_2", "test_associatedUsername_1",
                "first_name", "last_name", "fatherID",
                "motherID", "ID", "f");
        pDao.insert(bestPerson);
        pDao.insert(goodPerson);
        pDao.updateSpouseID(bestPerson,goodPerson);
        pDao.updateSpouseID(goodPerson,bestPerson);
        assertEquals(pDao.find(goodPerson.getPersonID()).getSpouseID(), bestPerson.getPersonID());
        assertEquals(pDao.find(bestPerson.getPersonID()).getSpouseID(), goodPerson.getPersonID());
    }

    @Test
    public void removeDataAssociatedWithUserPass() throws DataAccessException {
        pDao.insert(bestPerson);
        pDao.removeDataAssociatedWithUser("test_associatedUsername_1");
        assertNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void removeDataAssociatedWithUserFail() throws DataAccessException {
        pDao.insert(bestPerson);
        pDao.removeDataAssociatedWithUser("wrongName");
        assertNotNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void findAllPass() throws DataAccessException {
        Person goodPerson = new Person("test_personID_2", "test_associatedUsername_1",
                "first_name", "last_name", "fatherID",
                "motherID", "spouseID", "f");
        pDao.insert(bestPerson);
        pDao.insert(goodPerson);
        ArrayList<Person> personList = pDao.findAll("test_associatedUsername_1");
        assertEquals(personList.size(), 2);
    }

    @Test
    public void findAllFail() throws DataAccessException {
        pDao.insert(bestPerson);
        ArrayList<Person> personList = pDao.findAll("wrongName");
        assertFalse(personList.size() != 0);
    }
}
