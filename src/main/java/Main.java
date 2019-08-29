import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
    public static final int BYE = 0;
    public static final int LIST = 1;
    public static final int DONE = 2;
    public static final int DELETE = 3;
    public static final int TASK = 4;

    protected static String savePath = "../../../data/duke.txt";

    static String format(String command) {
        return "    ____________________________________________________________\n"
                + indent(command)
                + "    ____________________________________________________________\n";
    }

    static String indent(String command) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(command);
        while(scanner.hasNext()) {
            String temp = scanner.nextLine();
            stringBuffer.append("     " + temp + "\n");
        }
        scanner.close();
        return stringBuffer.toString();
    }

    static void response(String command) {
        System.out.println(format(command));
    }

    static void save(TaskList list) {
        try {
            FileOutputStream fout=new FileOutputStream(savePath);  
            ObjectOutputStream out=new ObjectOutputStream(fout);  
            out.writeObject(list);  
            out.flush();  
            out.close();  
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    static TaskList restore() {
        TaskList list = new TaskList();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(savePath));  
            list = (TaskList) in.readObject(); 
            in.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void run() {
        response("Hello! I'm Duke\n"
                + "What can I do for you?");

        TaskList list = restore();
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while(isRunning) {
            String input = scanner.nextLine();
            int command = parser.parseInput(input);
            switch (command) {
            case BYE:
                response("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case LIST:
                response("Here are the tasks in your list:\n" 
                        + list.toString());
                break;
            case DONE:
                int itemId = parser.parseDone(input);
                list.markAsDone(itemId);
                response("Nice! I've marked this task as done: \n  "
                        + list.get(itemId).toString());
                break;
            case DELETE:
                itemId = parser.parseDelete(input);
                Task item = list.remove(itemId);
                response("Noted. I've removed this task:  \n  "
                        + item.toString() + "\n"
                        + "Now you have " + list.size() +" tasks in the list.");
                break;
            case TASK:
                try {
                    Task task = parser.parseTask(input);
                    list.add(task);
                    response("Got it. I've added this task:\n  "
                            + task.toString() + "\n"
                            + "Now you have " + list.size() +" tasks in the list.");
                } catch(DukeException e) {
                    response("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            default:
                response("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
            save(list);
        }
        scanner.close();
    }
}