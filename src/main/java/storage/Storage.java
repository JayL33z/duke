package storage;

import ui.*;
import commands.*;
import tasklist.*;
import parser.*;
import exception.*;

import java.util.Scanner;
import java.lang.String;
import java.lang.Exception;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Storage {

    private String filePath;

    //instantiate a storage object with filePath in Duke.java
    public Storage(String filePath) {
            this.filePath = filePath;
    }

    /**
     * Returns the filepath for this storage
     * @return the string for filepath
     */
    public String getfilePath() {
        return filePath;
    }

    /**
     * loads from filePath to return an arraylist to instantiate a TaskList object in Duke.java
     * @return an arraylist of tasks
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        try{
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            //Assume that the format and syntax of task.txt is correct
            while (s.hasNext()) {
                String input = s.nextLine(); //read line
                String in[] = input.split("\\s\\|\\s"); //escape whitespace and escape |
                String option = in[0]; //get option
                String done = in[1]; //get done (1 means mark and 0 unmark as done)
                String description = in[2]; //get description

                if (option.equals("T")) { //handle todo
                    list.add(new Todo(in[2])); //creates new todo

                    if (done.equals("1")) {
                        list.get(list.size() - 1).markDone(); //mark the task
                    } else if (done.equals("0")) {
                        list.get(list.size() - 1).unmarkDone(); //unmark the task
                    }
                } else if (option.equals("D")) { //handle deadline
                    list.add(new Deadline(in[2], LocalDate.parse(in[3]))); //creates new Deadline where in[3] is the date

                    if (done.equals("1")) {
                        list.get(list.size() - 1).markDone(); //mark the task
                    } else if (done.equals("0")) {
                        list.get(list.size() - 1).unmarkDone(); //unmark the task
                    }
                } else if (option.equals("E")) { //handle event
                    list.add(new Event(in[2], LocalDateTime.parse(in[3]))); //creates new Event where in[3] is the date/time

                    if (done.equals("1")) {
                        list.get(list.size() - 1).markDone();
                    }//mark the task
                    else if (done.equals("0")){
                        list.get(list.size() - 1).unmarkDone(); //unmark the task
                    }
                } else if (option.equals("F")) { //handle event
                    list.add(new FixedTask(in[2], in[3])); //creates new FixedTask where in[3] is the duration
                    
                    if (done.equals("1")) {
                        list.get(list.size() - 1).markDone();
                    }//mark the task
                    else if (done.equals("0")){
                        list.get(list.size() - 1).unmarkDone(); //unmark the task
                    }
                } //end of loop

            }
        } //end of try clause
        catch (FileNotFoundException e){
            throw new DukeException("\t File data/tasks.txt was not successfully loaded.\n");
        }
        return list;
    } //end of load method

    /**
     * Writes the data to the stored file (it rewrites the whole file from the ArrayList)
     * @param a task list
     * @return the string for filepath
     */
    public void store(TaskList list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            String output = ""; //output to overwrite the text file

            for (int i = 0; i < list.getSize(); i++) {
                String description = list.getList().get(i).getDescription(); // get description from list
                String task = list.getList().get(i).getIdentity(); // get whether it is a Todo, Deadline or Event
                String done = "0"; // get whether task is complete done (1 means mark and 0 unmark as done) from list
                String dateTime;
                String duration;

                // if task is done
                if (list.getList().get(i).getStatusIcon().equals("X")) {
                    done = "1";
                }

                // three types of tasks to write to file
                if (task.equals("T")) {
                    fw.write("T | " + done + " | " + list.getList().get(i).getDescription());
                } else if (task.equals("D")) {
                    dateTime = list.getList().get(i).getDateTimeStore();
                    fw.write("D | " + done + " | " + list.getList().get(i).getDescription() + " | " + dateTime);
                } else if (task.equals("E")) {
                    dateTime = list.getList().get(i).getDateTimeStore();
                    fw.write("E | " + done + " | " + list.getList().get(i).getDescription() + " | " + dateTime);
                } else if (task.equals("F")) {
                    duration = list.getList().get(i).getFixedDuration();
                    fw.write("F | " + done + " | " + list.getList().get(i).getDescription() + " | " + duration);
                }
                fw.write("\n"); //next line

            } //end of loop
            
            fw.close();

        }  catch (IOException e){
            throw new DukeException("\t Updated list was not successfully stored.\n");
        }

    } //end of store method

}//end of class