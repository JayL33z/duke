package parser;

import commands.*;
import ui.*;
import storage.*;
import tasklist.*;
import exception.*;

import java.lang.String;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.IndexOutOfBoundsException;

public class Parser {

    /**
     * Returns a command object instance from the user's input
     *  @param String of the full command input by the user
     * @return the Command object
     */
    public static Command parse(String fullCommand) throws DukeException {
        
        Command command;
        String in[] = fullCommand.split(" ", 2);
        String option = in[0];

       if(option.equalsIgnoreCase("bye")){
            command = new ByeCommand(CommandType.BYE);
        }

        else if(option.equalsIgnoreCase("list")){ //output the list of tasks
            command = new ListCommand(CommandType.LIST);
        }
        
       else if(option.equalsIgnoreCase("view")){ //output the list of tasks
           try {
               command = new ViewCommand(CommandType.VIEW, in[1]);
           }catch (ArrayIndexOutOfBoundsException e){
               throw new DukeException("OOPS! Please input in the format:  view [YYYY-MM-DD]");
           }
       }

        else if(option.equalsIgnoreCase("mark")){ //mark a task as done
            try{
                command = new MarkCommand(CommandType.MARK, in[1]);
                
            }catch (ArrayIndexOutOfBoundsException e){
                throw new DukeException("OOPS! Please input in the format:  mark [index]");
            }

        }

        else if(option.equalsIgnoreCase("unmark")){ //unmark
           try {
               command = new UnmarkCommand(CommandType.UNMARK, in[1]);
           }catch (ArrayIndexOutOfBoundsException e){
               throw new DukeException("OOPS! Please input in the format:  unmark [index]");
           }
        }

        else if(option.equalsIgnoreCase("todo")){ //handle todo via AddCommand.java
           try {
               command = new AddCommand(CommandType.TODO, in[1]);
           } catch (ArrayIndexOutOfBoundsException e){
               throw new DukeException("OOPS! Please input in the format:  todo [description]");
           }
        }

        else if(option.equalsIgnoreCase("deadline")){ //handle deadline via AddCommand.java
            try {
                command = new AddCommand(CommandType.DEADLINE, in[1]);
            } catch (ArrayIndexOutOfBoundsException e){
                throw new DukeException("OOPS! Please input in the format:  deadline [description] /by [YYYY-MM-DD]");
            }
        }

        else if(option.equalsIgnoreCase("event")){ //handle Event via AddCommand.java
           try {
               command = new AddCommand(CommandType.EVENT, in[1]);
           } catch (ArrayIndexOutOfBoundsException e){
               throw new DukeException("OOPS! Please input in the format:  event [description] /at [YYYY-MM-DDTHH:MM]");
           }
        }
       else if(option.equalsIgnoreCase("fixed")){ //handle Event via AddCommand.java
           try {
           command = new AddCommand(CommandType.FIXED, in[1]);
           } catch (ArrayIndexOutOfBoundsException e){
               throw new DukeException("OOPS! Please input in the format:  fixed [description] /for [duration]");
           }
       }
       else if(option.equalsIgnoreCase("find")){ //handle Event via AddCommand.java
           try {
           command = new FindCommand(CommandType.FIND, in[1]);
           } catch (ArrayIndexOutOfBoundsException e){
               throw new DukeException("OOPS! Please input in the format:  find [keyword]");
           }
       }
        else if(option.equalsIgnoreCase("delete")){ //handle delete
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