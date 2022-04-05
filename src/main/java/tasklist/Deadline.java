package tasklist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private String by;
    private LocalDate date;
    //protected DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
    
    public Deadline(String description, LocalDate date) {
        super(description); //call constructor of the Task superclass
        this.date = date;
        identity = "D";
        //formats the date into a string
        by = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
    
    @Override
    public String getDateTimeString() {
        return this.by;
    }

    @Override
    public String getDateTimeStore() {
        return date.toString();
    } // //return date/time as a String only applicable for Deadline and Event in format for storing
}