public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description); //call constructor of the Task superclass
        this.at = at;
    }

    @Override
    public String getDescription() {
        return "[E]" +"["+ super.getStatusIcon() +"] "+ super.getDescription() + " (at: " + at + ")";
    }
}