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

    // get Status Icon of the task
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // get the identify of the task; Todo (T), Event (E) or Deadline (D)
    public String getIdentity() {
        return identity;
    }

    public String getDescription() {
        return description;
    } // return description

    public String getDateTime() {return ""; } //return date/time only applicable for Deadline and Event
}