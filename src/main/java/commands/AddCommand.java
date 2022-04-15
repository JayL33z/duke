package commands;

import ui.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.lang.ArrayIndexOutOfBoundsException;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class AddCommand extends Command{
    
    //instantiate a command object without parameters from Parser.parse() e.g. bye, list
    public AddCommand (CommandType type) {
        super(type);
    }

    //instantiate a command object with parameters from Parser.parse() e.g. mark, unmark, todo, deadline, event, delete
    public AddCommand (CommandType type, String parameters) {
        super(type, parameters);
    }
    
    /**
     * Executes the command
     * @param TaskList object
     * @param Ui object
     * @param Storage object
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {

        //find out what is the Task Type from the CommandType and add it to the list
        switch(this.type) {

            case TODO: //add todo
                try {
                    this.parameters = this.parameters.trim(); //remove leading and trailing spaces in description
                    tasks.addTask(new Todo(this.parameters)); //creates new todo into the arrayList in TaskList
                    assert tasks.getSize() != 0 : "The tasklist should not be empty since a task is just added";
                    ui.showAdded(tasks.getList().get(tasks.getSize() - 1), tasks.getSize()); //prints output to show user that the task has been added
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS! Please input in the format:  todo [description]\n");
                }
                break;

            case DEADLINE:
                try {
                    String arr[] = this.parameters.split("/by", 2); //to get description and by
                    arr[0] = arr[0].trim(); //remove leading and trailing spaces in description and by
                    arr[1] = arr[1].trim();
                    tasks.addTask(new Deadline(arr[0], LocalDate.parse(arr[1]))); //creates new Deadline
                    assert tasks.getSize() != 0 : "The tasklist should not be empty since a task is just added";
                    ui.showAdded(tasks.getList().get(tasks.getSize() - 1), tasks.getSize()); //prints output to show user that the task has been added
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    throw new DukeException("OOPS! Please input in the format:  deadline [description] /by [YYYY-MM-DD]");
                }
                break;

            case EVENT:
                try {
                    String arr[] = this.parameters.split("/at", 2); //to get description and by
                    arr[0] = arr[0].trim(); //remove leading and trailing spaces in description and at
                    arr[1] = arr[1].trim();
                    tasks.addTask(new Event(arr[0], LocalDateTime.parse(arr[1]))); //creates new Event
                    assert tasks.getSize() != 0 : "The tasklist should not be empty since a task is just added";
                    ui.showAdded(tasks.getList().get(tasks.getSize() - 1), tasks.getSize()); //prints output to show user that the task has been added
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    throw new DukeException("OOPS! Please input in the format:  event [description] /at [YYYY-MM-DDTHH:MM]");
                }
                break;

            case FIXED:
                try {
                    String arr[] = this.parameters.split("/for", 2); //to get description and by
                    arr[0] = arr[0].trim();  //remove leading and trailing spaces in description and for
                    arr[1] = arr[1].trim();
                    tasks.addTask(new FixedTask(arr[0], arr[1])); //creates new FixedTask
                    assert tasks.getSize() != 0 : "The tasklist should not be empty since a task is just added";
                    ui.showAdded(tasks.getList().get(tasks.getSize() - 1), tasks.getSize()); //prints output to show user that the task has been added
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    throw new DukeException("OOPS! Please input in the format:  fixed [description] /for [duration]");
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