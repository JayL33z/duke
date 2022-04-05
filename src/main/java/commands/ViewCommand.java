package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.time.LocalDate;

public class ViewCommand extends Command{
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public ViewCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public ViewCommand (CommandType type, String parameters) {
        super(type, parameters);
    }

    //executes the command accordingly from Duke.java
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage){
            LocalDate date;
            //remove leading and trailing spaces
            this.parameters = this.parameters.trim();
            date = LocalDate.parse(this.parameters);
            //prints output to show user the tasks for a specific date
            ui.showView(tasks, date);
    } //end of execute method

}