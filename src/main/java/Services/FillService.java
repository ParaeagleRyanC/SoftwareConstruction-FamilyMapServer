package Services;


import DataAccess.*;
import DataGenertor.GenerationsGenerator;
import Model.Person;
import Model.User;
import Results.FillResult;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

/**
 * This is the class that deals with Fill Service
 */
public class FillService {

    /**
     * This is the method that passes in username and generations and returns the result
     */
    public FillResult fill(String username, String generations) {

        int numGeneration;
        try {
            numGeneration = Integer.parseInt(generations);
            if (numGeneration < 0) {
                return new FillResult("Error: Invalid number of generations", false);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new FillResult("Error: Invalid number of generations", false);
        }


        ConnectionManager cm = new ConnectionManager();
        try {
            cm.openConnection();
            Connection conn = cm.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            PersonDAO personDAO = new PersonDAO(conn);
            EventDAO eventDAO = new EventDAO(conn);
            User user = userDAO.find(username);

            // check if the username is in the database
            if(user == null) {
                cm.closeConnection(false);
                return new FillResult("Error: username does not exist in database", false);
            }

            // clear all data related to the username
            personDAO.removeDataAssociatedWithUser(username);
            eventDAO.removeDataAssociatedWithUser(username);

            // populate generations
            Random random = new Random();
            int year = 2045 + random.nextInt(15);
            GenerationsGenerator gg = new GenerationsGenerator(conn);
            Person person = gg.generatePerson(user.getGender(),
                    numGeneration, user.getUsername(), year);

            person.setFirstName(user.getFirstName());
            person.setLastName(user.getLastName());
            personDAO.updateName(person);
            personDAO.updatePersonID(person, user.getPersonID());
            eventDAO.removeDeathEventFromUser(person.getPersonID());
            eventDAO.updatePersonID(person,user.getPersonID());

            cm.closeConnection(true);
            int numPerson = (int) Math.pow(2,numGeneration + 1) - 1;
            int numEvent = 3 * numPerson - 2;
            String resultMsg = "Successfully added " + numPerson + " persons and " +
                                numEvent + " events to the database.";
            return new FillResult(resultMsg, true);

        } catch (DataAccessException | IOException e) {
            e.printStackTrace();
            cm.closeConnection(false);
            return new FillResult(e.toString(), false);
        }
    }
}
