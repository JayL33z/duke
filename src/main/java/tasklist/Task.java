package tasklist;

public abstract class Task {
    protected String description; //protected can be accessed by child classes
    protected boolean isDone;  //protected can be accessed by child classes
    protected String identity; //use for easily identifying what type of Task is the object. (E, T and D)

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
            this.isDone = false;
    }

    /**
     * Get Status Icon of the task
     * @return String of Status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Get the identify of the task; Todo (T), Event (E) or Deadline (D)
     * @return String of the task
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Get the description of the task
     * @return String of description of the task
     */
    public String getDescription() {
        return description;
    } // return description

    /**
     * Get the date/time of the task
     * @return String of date/time of the task to print
     */
    public String getDateTimeString() {
        return "";
    } //return date/time as a String only applicable for Deadline and Event for in format for printing

    /**
     * Get the date/time of the task to store
     * @return String of date/time of the task to store
     */
    public String getDateTimeStore() {
        return "";
    } // //return date/time as a String only applicable for Deadline and Event in format for storing


    /**
     * Get the fixed duration of the task
     * @return String of fixed duration of the task
     */
    public String getFixedDuration() {
        return "";
    } // //return date/time as a String only applicable for FixedTask
}