package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.lang.ArrayIndexOutOfBoundsException;
import java.io.IOException;


public class AddCommand extends Command{
    
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public AddCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public AddCommand (CommandType type, String parameters)
    {
        super(type, parameters);
    }




    //executes the command accordingly from Duke.java (this adds an item to the list then updates the list)
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {

        //find out what is the Task Type from the CommandType and add it to the list
        switch(this.type) {

            case TODO: //add todo
                try {
                    //remove leading and trailing spaces in description
                    this.parameters = this.parameters.trim();
                    tasks.addTask(new Todo(this.parameters)); //creates new todo into the arrayList in TaskList

                    //prints output to show user that the task has been added
                    ui.showAdded(tasks.getList().get(tasks.getSize() - 1), tasks.getSize());

                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t OOPS!!! The description of a todo cannot be empty.\n");
                }
                break;

            case DEADLINE:
                try {
                    String arr[] = this.parameters.split("/by", 2); //to get description and by

                    //remove leading and trailing spaces in description and by
                    arr[0] = arr[0].trim();
                    arr[1] = arr[1].trim();

                    tasks.addTask(new Deadline(arr[0], arr[1])); //creates new Deadline

                    //prints output to show user that the task has been added
                    ui.showAdded(tasks.getList().get(tasks.getSize() - 1), tasks.getSize());

                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t OOPS!!! The description of a deadline cannot be empty.\n");
                }
                break;

            case EVENT:
                try {
                    String arr[] = this.parameters.split("/at", 2); //to get description and by

                    //remove leading and trailing spaces in description and at
                    arr[0] = arr[0].trim();
                    arr[1] = arr[1].trim();

                    tasks.addTask(new Event(arr[0], arr[1])); //creates new Event

                    //prints output to show user that the task has been added
                    ui.showAdded(tasks.getList().get(tasks.getSize() - 1), tasks.getSize());

                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t OOPS!!! The description of an event cannot be empty.\n");
                }
                break;

            default:
                break;
        } //end of switch

        //this part writes file
        try{
            storage.store(tasks); //store fileContent
        } catch (DukeException e) {
            throw e;
        }

    } //end of execute method

}