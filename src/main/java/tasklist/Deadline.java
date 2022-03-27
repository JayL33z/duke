package tasklist;

public class Deadline extends Task {
    protected String by;
    
    public Deadline(String description, String by) {
        super(description); //call constructor of the Task superclass
        this.by = by;
        identity = "D";
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
    
    @Override
    public String getDateTime() { return this.by; }
}