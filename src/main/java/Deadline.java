public class Deadline extends Task {

    protected String by;
    
    public Deadline(String description, String by) {
        super(description); //call constructor of the Task superclass
        this.by = by;
    }

    @Override
    public String getDescription() {
        return "[D]" +"["+ super.getStatusIcon() +"] "+ super.getDescription() + " (by: " + by + ")";
    }
}