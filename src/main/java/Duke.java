import java.util.Scanner;
import java.lang.String;

public class Duke {
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int index =0;
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

            if(input.equals("bye")){
                break;
            }

            else if(input.equals("list")){ //output the list of tasks

                System.out.print("\t____________________________________________________________\n"
                    + "\t Here are the tasks in your list:\n");
                
                    for (int i = 0; i < index; i++) {
                        System.out.print("\t " + (i + 1) + "." + list[i].getDescription() + "\n");
                    }

                System.out.print("\t____________________________________________________________\n");
            }

            else if(input.substring(0, 4).equals("mark")){ //mark a task as done

                int taskToUpdate = Integer.parseInt(input.substring(5)); //get the task number to update
                list[taskToUpdate - 1].markDone(); //mark the task as done

                System.out.println("\t____________________________________________________________\n"
                        + "\t Nice! I've marked this task as done: \n"
                        + "\t "+ list[taskToUpdate - 1].getDescription() +"\n"
                        + "\t____________________________________________________________\n");
                
            }

            else if(input.substring(0, 6).equals("unmark")){ //unmark

                int taskToUpdate = Integer.parseInt(input.substring(7)); //get the task number to update
                list[taskToUpdate - 1].unmarkDone();

                System.out.println("\t____________________________________________________________\n"
                        + "\t OK, I've marked this task as not done yet: \n"
                        + "\t "+ list[taskToUpdate - 1].getDescription() +"\n"
                        + "\t____________________________________________________________\n");

            }

            else if(input.substring(0, 4).equals("todo")){ //handle todo
                
                list[index] = new Todo(input.substring(5)); //creates new todo

                System.out.println("\t____________________________________________________________\n"
                        + "\t Got it. I've added this task: \n"
                        + "\t  " + list[index].getDescription() +"\n"
                        + "\t Now you have " + (index + 1) + " tasks in the list.\n"
                        +"\t____________________________________________________________\n");

                index +=1 ; //increase index after printing info

            }

            else if(input.substring(0, 8).equals("deadline")){ //handle deadline

                String arr[] = input.substring(9).split("/by", 2); //to get description and by

                list[index] = new Deadline(arr[0], arr[1]); //creates new Deadline

                System.out.println("\t____________________________________________________________\n"
                        + "\t Got it. I've added this task: \n"
                        + "\t  " + list[index].getDescription() +"\n"
                        + "\t Now you have " + (index + 1) + " tasks in the list.\n"
                        + "\t____________________________________________________________\n");

                index +=1 ; //increase index after printing info
            }

            else if(input.substring(0, 5).equals("event")){ //handle Event

                String arr[] = input.substring(6).split("/at", 2); //to get description and at

                list[index] = new Event(arr[0], arr[1]); //creates new Event

                System.out.println("\t____________________________________________________________\n"
                        + "\t Got it. I've added this task: \n"
                        + "\t  " + list[index].getDescription() +"\n"
                        + "\t Now you have " + (index + 1) + " tasks in the list.\n"
                        + "\t____________________________________________________________\n");

                index +=1 ; //increase index after printing info
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