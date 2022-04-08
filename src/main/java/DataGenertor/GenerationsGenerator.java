package DataGenertor;

import DataAccess.DataAccessException;
import DataAccess.PersonDAO;
import Model.Person;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

public class GenerationsGenerator {

    private final Connection conn;
    EventGenerator eventGenerator;
    public GenerationsGenerator(Connection conn) throws DataAccessException {
        this.conn = conn;
        eventGenerator= new EventGenerator(conn);
    }

    public Person generatePerson(String gender, int generations, String username, int currentYear)
            throws IOException, DataAccessException {

        Person mother = null;
        Person father = null;

        Random random = new Random();
        currentYear = currentYear - 25;

        if (generations > 0) {
            mother = generatePerson("f", generations - 1, username, currentYear);
            father = generatePerson("m", generations - 1, username, currentYear);

            // set mother's and father's spouseID
            try {
                PersonDAO personDAO = new PersonDAO(conn);
                personDAO.updateSpouseID(father, mother);
                personDAO.updateSpouseID(mother, father);
                personDAO.updateWifeLastname(father, mother);
            } catch (DataAccessException e) {
                e.printStackTrace();
                throw new DataAccessException("Error updating spouseID");
            }

            // Add marriage events to mother and father and must be the same
            eventGenerator.marriageGenerator(mother, father, currentYear - 36 - random.nextInt(3));
        }

        // Set person's properties
        try {
            Person person = new Person();
            String lastName = SurnameGenerator.surnameGenerator();
            person.setAssociatedUsername(username);
            if (gender.equalsIgnoreCase("f")) {
                person.setFirstName(FemaleNameGenerator.femaleNameGenerator());
            }
            else {
                person.setFirstName(MaleNameGenerator.maleNameGenerator());
            }
            person.setLastName(lastName);
            person.setGender(gender);
            if (father != null) {
                person.setFatherID(father.getPersonID());
                person.setMotherID(mother.getPersonID());
            }

            // Generate events for person (except marriage)
            eventGenerator.birthGenerator(person,currentYear + random.nextInt(5));
            eventGenerator.deathGenerator(person,currentYear + 20 + random.nextInt(10));

            // Save person in database
            try {
                PersonDAO personDAO = new PersonDAO(conn);
                personDAO.insert(person);
            } catch (DataAccessException e) {
                e.printStackTrace();
                throw new DataAccessException("Error adding person into database");
            }

            return person;
        } catch (IOException | DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
