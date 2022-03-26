public class Task {
    protected String description; //protected can be accessed by child classes
    protected boolean isDone;  //protected can be accessed by child classes
    protected String identity; //use for easily identifying what type of Task is the object. (E, T and D)

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;} // modifier to mark

    public void unmarkDone() {
            this.isDone = false;} //modifier to unmark

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    } // mark done task with X

    public String getIdentity() {
        return identity;
    } // return description

    public String getDescription() {
        return description;
    } // return description

    public String getDateTime() {return ""; } //return date only applicable for Deadline and Todo

    public String getDescriptionLite() {return description;}

}