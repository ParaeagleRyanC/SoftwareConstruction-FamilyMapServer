package Results;

import Model.Event;
import java.util.ArrayList;

/**
 * This is the class for Results of ALL Event Service
 */
public class EventAllFromAllMemberResult extends ParentResult {
    /**
     * This is an array of event objects in the Event Result class
     */
    private ArrayList<Event> data;

    /**
     * This is the constructor for the Event Result class
     */
    public EventAllFromAllMemberResult(String message, boolean success, ArrayList<Event> data) {
        super(message, success);
        this.data = data;
    }

    // getter and setter
    public ArrayList<Event> getAllEventData() { return data; }
    public void setAllEventData(ArrayList<Event> allEventData) { this.data = allEventData; }
}
