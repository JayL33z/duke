package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

public class ByeCommand extends Command{
    
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public ByeCommand (CommandType type) {

        super(type);
        exit = true;

    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public ByeCommand (CommandType type, String parameters)
    {
        super(type, parameters);
        exit = true;

    }

    /**
     * Executes the command
     * @param TaskList object
     * @param Ui object
     * @param Storage object
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage){
        ui.showBye();
    } //end of execute method

}