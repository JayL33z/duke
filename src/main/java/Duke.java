import java.util.Scanner;
import java.lang.String;
import java.lang.Exception;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    //this method preloads the data from the stored file
    private static ArrayList<Task> readFileContents(String filePath) throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<Task>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        //Assume that the format and syntax of duke.txt is correct
        while (s.hasNext()) {
            String input = s.nextLine(); //read line
            String in[] = input.split("\\s\\|\\s"); //escape whitespace and escape |
            String option = in[0]; //get option
            String done = in[1]; //get done (1 means mark and 0 unmark as done)
            String description = in[2]; //get description

            /* for debugging
            System.out.println("option is "+ option );
            System.out.println("done is "+ done );
            System.out.println("description is "+ description);
             */


            if (option.equals("T")) { //handle todo
                list.add(new Todo(in[2])); //creates new todo

                if (done.equals("1")) {
                    list.get(list.size() - 1).markDone(); //mark the task
                } else if (done.equals("0")) {
                    list.get(list.size() - 1).unmarkDone(); //unmark the task
                }
            } else if (option.equals("D")) { //handle deadline
                list.add(new Deadline(in[2], in[3])); //creates new Deadline/Event where in[3] is the date/time

                if (done.equals("1")) {
                    list.get(list.size() - 1).markDone(); //mark the task
                } else if (done.equals("0")) {
                    list.get(list.size() - 1).unmarkDone(); //unmark the task
                }
            } else if (option.equals("E")) { //handle event
                list.add(new Event(in[2], in[3])); //creates new Deadline/Event where in[3] is the date/time

                if (done.equals("1")) list.get(list.size() - 1).markDone(); //mark the task
                else if (done.equals("0")) list.get(list.size() - 1).unmarkDone(); //unmark the task
            }
            //System.out.println(s.nextLine());
        }
        return list;
    }

    //this method writes the data to the stored file (it rewrites the whole file from the ArrayList)
    private static void storeFileContents(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        String output = ""; //output to overwrite the text file

        for (int i = 0; i < list.size(); i++) {
            String description = list.get(i).getDescription(); // get description from list
            String task = list.get(i).getIdentity(); // get whether it is a Todo, Deadline or Event
            String done = "0"; // get whether task is complete done (1 means mark and 0 unmark as done) from list
            String dateTime;


            // if task is done
            if (list.get(i).getStatusIcon().equals("X")) {
                done = "1";
            }

            // three types of tasks to write to file
            if (task.equals("T")) {
                fw.write("T | " + done + " | " + list.get(i).getDescriptionLite());
            } else if (task.equals("D")) {
                dateTime = list.get(i).getDateTime();
                fw.write("D | " + done + " | " + list.get(i).getDescriptionLite() + " | " + dateTime);
            } else if (task.equals("E")) {
                dateTime = list.get(i).getDateTime();
                fw.write("E | " + done + " | " + list.get(i).getDescriptionLite() + " | " + dateTime);
            }
            fw.write("\n"); //next line
        } //end of loop

        fw.close();


    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //user input scanner
        ArrayList<Task> list; //declare new arraylist
        String filePath = "data/duke.txt";

        //this section handles the loading of the data from the hard disk when Duke starts up (if the file exists)
        try {
            list = readFileContents(filePath); //use preloaded data (tested on Windows 10 machine)
            System.out.println("The data from data/duke.txt has been preloaded.");
        } catch (FileNotFoundException e) {
            System.out.println("The file or folder (data/duke.txt) for preloading data is not found.");
            list = new ArrayList<Task>(); //create new arraylist if file not found
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t____________________________________________________________\n"
        + "\t Hello! I'm Duke\n"
        + "\t What can I do for you?\n"
        +"\t____________________________________________________________\n");

        String input;

        while (true) {
            input = scanner.nextLine();
            String in[] = input.split(" ", 2);
            String option = in[0]; //get option (enum not neccessary)

            if(option.equals("bye")){
                break;
            }

            else if(option.equals("list")){ //output the list of tasks
                System.out.print("\t____________________________________________________________\n"
                    + "\t Here are the tasks in your list:\n");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.print("\t " + (i + 1) + "." + list.get(i).getDescription() + "\n");
                    }
                System.out.print("\t____________________________________________________________\n");
            }

            else if(option.equals("mark")){ //mark a task as done
                try {
                    int taskToUpdate = Integer.parseInt(in[1]); //get the task number to update
                    list.get(taskToUpdate - 1).markDone(); //mark the task as done
                    System.out.println("\t____________________________________________________________\n"
                            + "\t Nice! I've marked this task as done: \n"
                            + "\t " + list.get(taskToUpdate - 1).getDescription() + "\n"
                            + "\t____________________________________________________________\n");
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\t____________________________________________________________\n"
                            + "\t OOPS!!! The description of a mark cannot be empty.\n"
                            + "\t____________________________________________________________\n");
                }
            }

            else if(option.equals("unmark")){ //unmark
                try {
                    int taskToUpdate = Integer.parseInt(in[1]); //get the task number to update
                    list.get(taskToUpdate - 1).unmarkDone();
                    System.out.println("\t____________________________________________________________\n"
                            + "\t OK, I've marked this task as not done yet: \n"
                            + "\t "+ list.get(taskToUpdate - 1).getDescription() +"\n"
                            + "\t____________________________________________________________\n");
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\t____________________________________________________________\n"
                            + "\t OOPS!!! The description of a unmark cannot be empty.\n"
                            + "\t____________________________________________________________\n");
                }
            }

            else if(option.equals("todo")){ //handle todo
                try {
                    //remove leading and trailing spaces in description
                    in[1] = in[1].trim();

                    list.add(new Todo(in[1])); //creates new todo
                    System.out.println("\t____________________________________________________________\n"
                            + "\t Got it. I've added this task: \n"
                            + "\t  " + list.get(list.size() - 1).getDescription() + "\n"
                            + "\t Now you have " + list.size() + " tasks in the list.\n"
                            + "\t____________________________________________________________\n");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\t____________________________________________________________\n"
                            + "\t OOPS!!! The description of a todo cannot be empty.\n"
                            + "\t____________________________________________________________\n");
                }
            }

            else if(option.equals("deadline")){ //handle deadline
                try {
                    String arr[] = in[1].split("/by", 2); //to get description and by

                    //remove leading and trailing spaces in description and by
                    arr[0] = arr[0].trim();
                    arr[1] = arr[1].trim();

                    list.add(new Deadline(arr[0], arr[1])); //creates new Deadline
                    System.out.println("\t____________________________________________________________\n"
                            + "\t Got it. I've added this task: \n"
                            + "\t  " + list.get(list.size() - 1).getDescription() + "\n"
                            + "\t Now you have " + list.size() + " tasks in the list.\n"
                            + "\t____________________________________________________________\n");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\t____________________________________________________________\n"
                            + "\t OOPS!!! The description of a deadline cannot be empty.\n"
                            + "\t____________________________________________________________\n");
                }
            }

            else if(option.equals("event")){ //handle Event
                try {
                    String arr[] = in[1].split("/at", 2); //to get description and at

                    //remove leading and trailing spaces in description and by
                    arr[0] = arr[0].trim();
                    arr[1] = arr[1].trim();

                    list.add(new Event(arr[0], arr[1])); //creates new Event
                    System.out.println("\t____________________________________________________________\n"
                            + "\t Got it. I've added this task: \n"
                            + "\t  " + list.get(list.size() - 1).getDescription() + "\n"
                            + "\t Now you have " + list.size() + " tasks in the list.\n"
                            + "\t____________________________________________________________\n");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\t____________________________________________________________\n"
                            + "\t OOPS!!! The description of an event cannot be empty.\n"
                            + "\t____________________________________________________________\n");
                }
            }
            else if(option.equals("delete")){ //handle delete
                try {
                    int taskToDelete = Integer.parseInt(in[1]); //get the task number to delete
                    System.out.println("\t____________________________________________________________\n"
                            + "\t Noted. I've removed this task: \n"
                            + "\t  " + list.get(taskToDelete - 1).getDescription() + "\n"
                            + "\t Now you have " + (list.size() - 1) + " tasks in the list.\n"
                            + "\t____________________________________________________________\n");
                    list.remove(taskToDelete - 1); //deletes a task
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\t____________________________________________________________\n"
                            + "\t OOPS!!! The description of a delete cannot be empty.\n"
                            + "\t____________________________________________________________\n");
                }
            }

            else{ // handles unrecognized input
                System.out.println("\t____________________________________________________________\n"
                        +"\t  OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        +"\t____________________________________________________________\n");
            }

            //this part writes file
            try{
                storeFileContents(filePath, list); //store fileContent
            } catch (IOException e) {
                System.out.println("Something went wrong while writing the file");
            }
            
        }

        System.out.println("\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                +"\t____________________________________________________________\n");

    }

}