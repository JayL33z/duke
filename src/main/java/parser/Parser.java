package parser;

import commands.*;
import ui.*;
import storage.*;
import tasklist.*;
import exception.*;

import java.lang.String;
import java.lang.ArrayIndexOutOfBoundsException;

public class Parser {

    //method to parse user input to recognize which command is selected
    public static Command parse(String fullCommand) throws DukeException {
        
        Command command;
        String in[] = fullCommand.split(" ", 2);
        String option = in[0];

       if(option.equals("bye")){
            command = new ByeCommand(CommandType.BYE);
        }

        else if(option.equals("list")){ //output the list of tasks
            command = new ListCommand(CommandType.LIST);
        }

        else if(option.equals("mark")){ //mark a task as done
            try{
                command = new MarkCommand(CommandType.MARK, in[1]);
                
            }catch (ArrayIndexOutOfBoundsException e){
                throw new DukeException("OOPS!!! You need to provide an index to mark as completed.");
            }

        }

        else if(option.equals("unmark")){ //unmark
           try {
               command = new UnmarkCommand(CommandType.UNMARK, in[1]);
           }catch (ArrayIndexOutOfBoundsException e){
               throw new DukeException("OOPS!!! You need to provide an index to unmark.");
           }
        }

        else if(option.equals("todo")){ //handle todo via AddCommand.java
            command = new AddCommand(CommandType.TODO, in[1]);
        }

        else if(option.equals("deadline")){ //handle deadline via AddCommand.java
            command = new AddCommand(CommandType.DEADLINE, in[1]);
        }

        else if(option.equals("event")){ //handle Event via AddCommand.java
            command = new AddCommand(CommandType.EVENT, in[1]);
        }
        else if(option.equals("delete")){ //handle delete
           try {
               command = new DeleteCommand(CommandType.DELETE, in[1]);
           }catch (ArrayIndexOutOfBoundsException e){
               throw new DukeException("OOPS!!! The task to delete is not found in the list. Please check your index again.");
           }
        }

        else{ // handles unrecognized input (do not need to use exception handling)
            command = new UnknownCommand(CommandType.UNKNOWN);
        }
        return command;
    }
} //end of class