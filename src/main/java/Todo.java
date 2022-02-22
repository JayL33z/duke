public class Todo extends Task {
    
    public Todo(String description) {
        super(description); //call constructor of the Task superclass
    }

    @Override
    public String getDescription() {
        return "[T]" +"["+ super.getStatusIcon() +"] "+ super.getDescription();
    }

}