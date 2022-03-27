package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

public abstract class Command {
    protected CommandType type; //enum type
    protected String parameters;
    protected boolean exit = false; //only when it is bye

    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public Command (CommandType type)
    {
        this.type = type;
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public Command (CommandType type, String parameters)
    {
        this.type = type;
        this.parameters = parameters;
    }

    //executes the command accordingly from Duke.java
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException;

    //executes the command accordingly from Duke.java
    public boolean isExit (){
        return exit;
    }

}