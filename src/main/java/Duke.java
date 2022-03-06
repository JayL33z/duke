import java.util.Scanner;
import java.lang.String;
import java.lang.Exception;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);

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
            String option = in[0]; //get option

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
            
        }

        System.out.println("\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                +"\t____________________________________________________________\n");

    }

}