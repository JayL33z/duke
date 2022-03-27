package tasklist;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description); //call constructor of the Task superclass
        this.at = at;
        identity = "E";
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
    
    @Override
    public String getDateTime() { return this.at; }

}