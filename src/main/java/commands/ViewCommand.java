package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ViewCommand extends Command{
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public ViewCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public ViewCommand (CommandType type, String parameters) {
        super(type, parameters);
    }

    /**
     * Executes the command
     * @param TaskList object
     * @param Ui object
     * @param Storage object
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException{
            LocalDate date;

            try{
                this.parameters = this.parameters.trim(); //remove leading and trailing spaces
                date = LocalDate.parse(this.parameters);
                ui.showView(tasks, date);  //prints output to show user the tasks for a specific date
            } catch (DateTimeParseException e){
                throw new DukeException("OOPS! Please input in the format:  view YYYY-MM-DD");
            }

    } //end of execute method

}