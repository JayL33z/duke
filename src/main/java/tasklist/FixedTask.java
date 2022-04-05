package tasklist;

public class FixedTask extends Task {

    private String duration;

    public FixedTask(String description, String duration) {
        super(description); //call constructor of the Task superclass
        identity = "F";
        this.duration = duration;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String getFixedDuration() {
        return this.duration;
    }  //return time as a String only applicable for FixedTask in format for printing and for storing
    
}