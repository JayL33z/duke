package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

public class ListCommand extends Command{
    
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public ListCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public ListCommand (CommandType type, String parameters) {
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
        ui.showList(tasks);
    } //end of execute method

}