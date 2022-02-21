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

            else if(input.equals("list")){

                System.out.println("\t____________________________________________________________\n");
                System.out.println("\t Here are the tasks in your list:\n");
                for(int i = 0; i < index; i ++){
                    System.out.println("\t " + (i+1) + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription() + "\n");
                }
                
                System.out.println("\t____________________________________________________________\n");
            }

            else if(input.substring(0, 4).equals("mark")){

                int taskToUpdate = Integer.parseInt(input.substring(5)); //get the task to update
                list[taskToUpdate - 1].markDone(); //mark the task as done

                System.out.println("\t____________________________________________________________\n");
                System.out.println("\t Nice! I've marked this task as done: \n");
                System.out.println("\t "+ "[" + list[taskToUpdate - 1].getStatusIcon() + "] " + list[taskToUpdate - 1].getDescription() +"\n");
                System.out.println("\t____________________________________________________________\n");
                
            }

            else if(input.substring(0, 6).equals("unmark")){

                int taskToUpdate = Integer.parseInt(input.substring(7)); //get the task to update
                list[taskToUpdate - 1].unmarkDone();

                System.out.println("\t____________________________________________________________\n");
                System.out.println("\t OK, I've marked this task as not done yet: \n");
                System.out.println("\t "+ "[" + list[taskToUpdate - 1].getStatusIcon() + "] " + list[taskToUpdate - 1].getDescription() +"\n");
                System.out.println("\t____________________________________________________________\n");

            }

            else{
                System.out.println("\t____________________________________________________________\n"
                        +"\t added: "+input +"\n"
                        +"\t____________________________________________________________\n");

                list[index] = new Task(input); //creates new Task
                index +=1 ;
            }
        } 
        System.out.println("\t____________________________________________________________\n"
        + "\t Bye. Hope to see you again soon!\n"
        +"\t____________________________________________________________\n");

    }

}