package Results;

import Model.Person;
import java.util.ArrayList;

/**
 * This is the class for Results of ALL Person Service
 */
public class PersonAllFamilyMemberResult extends ParentResult {
    /**
     * This is an array of person objects in the Person Result class
     */
    private ArrayList<Person> data;

    /**
     * This is the constructor for the Person Result class
     */
    public PersonAllFamilyMemberResult(String message, boolean success, ArrayList<Person> data) {
        super(message, success);
        this.data = data;
    }

    // getter and setter
    public ArrayList<Person> getData() {
        return data;
    }
    public void setData(ArrayList<Person> data) {
        this.data = data;
    }
}
