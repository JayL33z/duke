import java.util.Scanner;
import java.lang.String;

public class Duke {
    public static void main(String[] args) {
        String[] list = new String[100];
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

                for(int i = 0; i < index; i ++){
                    System.out.println("\t " + (i+1) + ". "+ list[i] + "\n");
                }

                System.out.println("\t____________________________________________________________\n");
            }
            else{
                System.out.println("\t____________________________________________________________\n"
                        +"\t added: "+input +"\n"
                        +"\t____________________________________________________________\n");

                list[index] = input;
                index +=1 ;
            }
        }
        System.out.println("\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                +"\t____________________________________________________________\n");

    }

}