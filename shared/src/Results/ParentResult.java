package Results;

/**
 * This is the ParentResult class which is the parent to other result classes
 */
public class ParentResult {

    /**
     * This is message to display for the result either failed or succeeded
     */
    private String message;

    /**
     * This is success boolean indicating true for successful or otherwise
     */
    private boolean success;

    /**
     * This is the constructor for the ParentResult class
     */
    public ParentResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}