package tasklist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private String at;
    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description); //call constructor of the Task superclass
        this.dateTime = dateTime;
        identity = "E";

        //formats the datetime into a string
        at = dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
    
    @Override
    public String getDateTimeString() {
        return this.at;
    }

    @Override
    public String getDateTimeStore() {
        return dateTime.toString();
    } // //return date/time as a String only applicable for Deadline and Event in format for storing

}