package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

public class UnknownCommand extends Command{
    
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public UnknownCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public UnknownCommand (CommandType type, String parameters) {
        super(type, parameters);
    }

    /**
     * Executes the command
     * @param TaskList object
     * @param Ui object
     * @param Storage object
     */
    public void execute (TaskList tasks, Ui ui, Storage storage){
        ui.showUnknown();
    } //end of execute method

}