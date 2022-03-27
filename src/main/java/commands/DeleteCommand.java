package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.lang.ArrayIndexOutOfBoundsException;
import java.io.IOException;


public class DeleteCommand extends Command{
    
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public DeleteCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public DeleteCommand (CommandType type, String parameters)
    {
        super(type, parameters);
    }




    //executes the command accordingly from Duke.java (this adds an item to the list then updates the list)
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException{

            int taskToDelete = Integer.parseInt(this.parameters); //get the task number (index) to delete
            //prints output to show user that the task has been added
            ui.showDeleted(tasks.getList().get(taskToDelete - 1), tasks.getSize());
            tasks.deleteTask(taskToDelete - 1); //deletes a task

        //this part writes file
        try{
            storage.store(tasks); //store fileContent
        } catch (DukeException e) {
            throw e;
        }

    } //end of execute method

}