package ui;

import commands.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;


public class Ui {

    private String userInput;

    /**
     * Returns a String
     * <p>
     * This method returns a string for the user's input command
     * @return string of user's input command
     */
    public String readCommand(){
        Scanner scanner = new Scanner(System.in); //user input scanner
        userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Prints a line
     */
    public void showLine() {
        System.out.print("\t____________________________________________________________\n");
    }

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        System.out.println();
        showLine();
        System.out.print("\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n\n"
                + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n\n");

        System.out.print("\t AVAILABLE COMMANDS:\n"
                + "\t list\n"
                + "\t todo [description]\n"
                + "\t deadline [description] /by [YYYY-MM-DD]\n"
                + "\t event [description] /at [YYYY-MM-DDTHH:MM]\n"
                + "\t fixed [description] /for [duration]\n"
                + "\t delete [index]\n"
                + "\t mark [index]\n"
                + "\t unmark [index]\n"
                + "\t view [YYYY-MM-DD]\n"
                + "\t find [keyword]\n"
                + "\t bye\n");

        showLine();
        System.out.println();
    }

    /**
     * Prints a task (formatted) in the tasklist
     * @param task the task to print
     */
    public void showTask(Task task) {

        System.out.print("[" + task.getIdentity() + "]" +"["+ task.getStatusIcon() +"] " + task.getDescription());

        if(task.getIdentity().equals("D")){ //extra info for Deadline
            System.out.println(" (by: " + task.getDateTimeString() + ")");
        }
        else if (task.getIdentity().equals("E")){ //extra info for Event
            System.out.println(" (at: " + task.getDateTimeString() + ")");
        }
        else if (task.getIdentity().equals("F")){ //extra info for Event
            System.out.println(" (needs " + task.getFixedDuration() + ")");
        }
        else{ //no extra info for Todo
            System.out.println();
        }

    }

    /**
     * Prints message indicating that the file is not successfully loaded
     */
    public void showLoadingError() {
        System.out.print("\t File data/tasks.txt was not successfully loaded.\n");
    }

    /**
     * Prints any error message
     * @param errorMessage the error message to print
     */
    public void showError(String errorMessage) {
        System.out.print("\t "+ errorMessage + "\n");
    }

    /**
     * Prints message indicating that the a task is added to the list
     *  @param task the task added to the list
     *  @param listSize the size of the list
     */
    public void showAdded(Task task, int listSize){
        //showLine();
        System.out.print("\t Got it. I've added this task: \n\t ");
        showTask(task);
        System.out.print("\t Now you have " + listSize + " tasks in the list.\n");
        //showLine();
    }

    /**
     * Prints message indicating that the a task is deleted from the list
     * @param task the task deleted from the list
     * @param listSize the size of the list
     */
    public void showDeleted(Task task, int listSize){
        //showLine();
        System.out.print("\t Noted. I've removed this task: \n\t ");
        showTask(task);
        System.out.print("\t Now you have " + listSize + " tasks in the list.\n");
        //showLine();
    }

    /**
     * Prints message indicating that the a task in the list has been marked as completed
     * @param task the task marked as completed
     */
    public void showMark(Task task){
        //showLine();
        System.out.print("\t Nice! I've marked this task as done: \n\t ");
        showTask(task);
        //showLine();
    }

    /**
     * Prints message indicating that the a task in the list has been marked as incompleted
     * @param task the task marked as incompleted
     */
    public void showUnmark(Task task){
        //showLine();
        System.out.print("\t OK, I've marked this task as not done yet: \n\t ");
        showTask(task);
        //showLine();
    }

    /**
     * Prints full list of the tasks
     * @param list the task list to show
     */
    public void showList(TaskList list){
        //showLine();

        if(list.getSize() == 0){
            System.out.print("\t Your list is empty!\n");
        }
        else{
            System.out.print("\t Here are the tasks in your list:\n");
            //loops through the TaskList
            for (int i = 0; i < list.getSize(); i++) {
                Task task = list.getList().get(i);
                System.out.print("\t " + (i + 1) + ".");
                showTask(task);
            }
        }

    }

    /**
     * Prints list of tasks for a specific date
     * @param list the task list to search for a date
     * @param date the date to search
     */
    public void showView(TaskList list,  LocalDate date) {

        LocalDate taskDate = LocalDate.now(); //intitialization only
        boolean emptyList = true; //tracks whether it returns any tasks.

        System.out.print("\t Your schedule for " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ": \n");
            //loops through the TaskList
        for (int i = 0; i < list.getSize(); i++) {
                Task task = list.getList().get(i);
                String identity = task.getIdentity();

                if (identity.equals("E") || identity.equals("D")) {
                    //extracts the date from Event
                    if (identity.equals("E")) {
                        String arr[] = task.getDateTimeStore().split("T", 2); //to seperate date from event
                        taskDate = LocalDate.parse(arr[0]); //get the date only for event

                    } else if (identity.equals("D")) {
                        taskDate = LocalDate.parse(task.getDateTimeStore()); //get date variable from String
                    }

                    //prints only if the condition is true
                    if (taskDate.compareTo(date) == 0) {
                        System.out.print("\t " + (i + 1) + ".");
                        showTask(task);
                        emptyList = false; //found something on this date
                    }
                }
            } //end of for loop

        if(emptyList == true){
            System.out.print("\t No events or deadlines found on " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + "\n");
        }
    } //end of showView


    /**
     * Prints list of tasks which has a description matching the keyword
     * @param list the task list to search for a keyword
     * @param keyword the keyword to search
     */
    public void showFind(TaskList list,  String keyword) {
        
        boolean emptyList = true; //tracks whether it returns any tasks.

        System.out.print("\t Here are the matching tasks in your list: \n");
        //loops through the TaskList
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getList().get(i);
            String taskDescription = task.getDescription();

            if (taskDescription.contains(keyword)) {
                System.out.print("\t " + (i + 1) + ".");
                showTask(task);
                emptyList = false; //found something on this date
            }
        } //end of for loop

        if(emptyList == true){
            System.out.print("\t No tasks found matching the keyword \"" + keyword + "\" \n");
        }
    } //end of showView


    /**
     * Prints message to indicate that Duke does not understand the user command
     */
    public void showUnknown(){
        //showLine();
        System.out.print("\t  OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        //showLine();
    }

    /**
     * Prints message to indicate that Duke has been closed by the user
     */
    public void showBye(){
        //showLine();
        System.out.print("\t Bye. Hope to see you again soon!\n");
        //showLine();
    }

} //end of class