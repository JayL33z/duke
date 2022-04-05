package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.lang.ArrayIndexOutOfBoundsException;
import java.io.IOException;


public class UnmarkCommand extends Command{
    
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public UnmarkCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public UnmarkCommand (CommandType type, String parameters) {
        super(type, parameters);
    }

    

    //executes the command accordingly from Duke.java (this adds an item to the list then updates the list)
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        
            int taskToUpdate = Integer.parseInt(this.parameters); //get the task number to update
            tasks.unmarkTask(taskToUpdate - 1);  //mark the task as done
            ui.showUnmark(tasks.getList().get(taskToUpdate - 1)); //prints output

        //this part writes file
        try{
            storage.store(tasks); //store fileContent
        } catch (DukeException e) {
            throw e;
        }

    } //end of execute method

}