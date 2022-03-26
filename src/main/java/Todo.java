public class Todo extends Task {
    
    public Todo(String description) {

        super(description); //call constructor of the Task superclass
        identity = "T";

    }

    @Override
    public String getDescription() {
        return "[T]" +"["+ super.getStatusIcon() +"] "+ super.getDescription();
    }

    @Override
    public String getDescriptionLite() { return super.getDescription();}
}