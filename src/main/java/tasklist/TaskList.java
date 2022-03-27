package tasklist;

import ui.*;
import commands.*;
import storage.*;
import parser.*;
import exception.*;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list; //declare new arraylist

    public TaskList() {
        list = new ArrayList<Task>(); //creates new ArrayList for this TaskList object
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list; //get a ArrayList for this TaskList object from storage.load()
    }

    //adds a task to the Arraylist
    public void addTask(Task task) {
        list.add(task);

    }

    //deletes a task from the Arraylist
    public void deleteTask(int taskToDeleteIndex) {
        list.remove(taskToDeleteIndex); //deletes a task
    }

    //marks a task from the Arraylist
    public void markTask(int taskToUpdateIndex) {
        list.get(taskToUpdateIndex).markDone(); //mark the task as done
    }

    //unmarks a task from the Arraylist
    public void unmarkTask(int taskToUpdateIndex) {
        list.get(taskToUpdateIndex).unmarkDone();
    }

    //returns the Arraylist
    public ArrayList<Task> getList() {
        return list;
    }

    //returns size of the ArrayList
    public int getSize() {
        return list.size();
    }
    
}