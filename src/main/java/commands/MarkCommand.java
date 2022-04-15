package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.io.IOException;


public class MarkCommand extends Command{
    
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public MarkCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public MarkCommand (CommandType type, String parameters) {
        super(type, parameters);
    }


    /**
     * Executes the command
     * @param TaskList object
     * @param Ui object
     * @param Storage object
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage)throws DukeException{

            int taskToUpdate = Integer.parseInt(this.parameters); //get the task number to update
            tasks.markTask(taskToUpdate - 1);  //mark the task as done
            ui.showMark(tasks.getList().get(taskToUpdate - 1)); //prints output

        //this part writes file
        try{
            storage.store(tasks); //store fileContent
        } catch (DukeException e) {
            throw e;
        }

    } //end of execute method

}