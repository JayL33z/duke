package ui;

import commands.*;
import storage.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.util.Scanner;


public class Ui {

    private String userInput;

    //get user input in Duke.java
    public String readCommand(){
        Scanner scanner = new Scanner(System.in); //user input scanner
        userInput = scanner.nextLine();
        return userInput;
    }

    //prints divider line
    public void showLine() {
        System.out.print("\t____________________________________________________________\n");
    }

    //prints Welcome message
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
                + "\t todo\n"
                + "\t deadline /by [date] \n"
                + "\t event /at [date and time]\n"
                + "\t delete [index]\n"
                + "\t mark [index]\n"
                + "\t unmark [index]\n"
                + "\t bye\n");

        showLine();
        System.out.println();
    }

    //prints a line of task in the list
    public void showTask(Task task) {

        System.out.print("[" + task.getIdentity() + "]" +"["+ task.getStatusIcon() +"] " + task.getDescription());

        if(task.getIdentity().equals("D")){ //extra info for Deadline
            System.out.println(" (by: " + task.getDateTime() + ")");
        }
        else if (task.getIdentity().equals("E")){ //extra info for Event
            System.out.println(" (at: " + task.getDateTime() + ")");
        }
        else{ //no extra info for Todo
            System.out.println();
        }

    }

    //prints loading error from file in Duke.java
    public void showLoadingError() {
        System.out.print("\t File data/tasks.txt was not successfully loaded.\n");
    }

    //prints other errors from file in Duke.java
    public void showError(String errorMessage) {
        System.out.print("\t "+ errorMessage + "\n");
    }

    //prints to show task is added to the list
    public void showAdded(Task task, int listSize){
        //showLine();
        System.out.print("\t Got it. I've added this task: \n\t ");
        showTask(task);
        System.out.print("\t Now you have " + listSize + " tasks in the list.\n");
        //showLine();
    }

    //prints to show task has been deleted from the list
    public void showDeleted(Task task, int listSize){
        //showLine();
        System.out.print("\t Noted. I've removed this task: \n\t ");
        showTask(task);
        System.out.print("\t Now you have " + listSize + " tasks in the list.\n");
        //showLine();
    }

    //prints to show task has been marked as done
    public void showMark(Task task){
        //showLine();
        System.out.print("\t Nice! I've marked this task as done: \n\t ");
        showTask(task);
        //showLine();
    }

    //prints to show task has been unmarked
    public void showUnmark(Task task){
        //showLine();
        System.out.print("\t OK, I've marked this task as not done yet: \n\t ");
        showTask(task);
        //showLine();
    }

    //prints to list of tasks
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

    //prints to show that Duke does not understand the command
    public void showUnknown(){
        //showLine();
        System.out.print("\t  OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        //showLine();
    }

    //prints to show that Duke has been closed by the user
    public void showBye(){
        //showLine();
        System.out.print("\t Bye. Hope to see you again soon!\n");
        //showLine();
    }

} //end of class