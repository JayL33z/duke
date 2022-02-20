import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
            input = scanner.next();

            if(!input.equals("bye")){
                System.out.println("\t____________________________________________________________\n"
                        +"\t "+input +"\n"
                        +"\t____________________________________________________________\n");
            }
            else{
                break;
            }
        }
        System.out.println("\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                +"\t____________________________________________________________\n");

    }

}