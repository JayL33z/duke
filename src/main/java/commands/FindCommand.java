package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

public class FindCommand extends Command{
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public FindCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public FindCommand (CommandType type, String parameters) {
        super(type, parameters);
    }

    /**
     * Executes the command
     * @param TaskList object
     * @param Ui object
     * @param Storage object
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage){
        //remove leading and trailing spaces
        this.parameters = this.parameters.trim();
        //prints output to show user the tasks for a match in description
        ui.showFind(tasks, this.parameters);
    } //end of execute method

}