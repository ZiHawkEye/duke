import java.util.Scanner;
import java.util.ArrayList;

public class Main {
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

    static Todo createTodo(String input) {
        String description = input.substring(1);
        return new Todo(description);
    }

    static Deadline createDeadline(String input) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(input);
        String temp = scanner.next();
        while(!temp.equals("/by")) {
            stringBuffer.append(" " + temp);
            temp = scanner.next();
        }
        String description = stringBuffer.toString().substring(1);
        String date = scanner.nextLine();
        scanner.close();
        return new Deadline(description, date);
    }

    static Event createEvent(String input) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(input);
        String temp = scanner.next();
        while(!temp.equals("/at")) {
            stringBuffer.append(" " + temp);
            temp = scanner.next();
        }
        String description = stringBuffer.toString().substring(1);
        String date = scanner.nextLine();
        scanner.close();
        return new Event(description, date);
    }

    static void addToList(Task task, ArrayList<Task> list) {
        list.add(task);
        response("Got it. I've added this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + list.size() +" tasks in the list.");
    }

    public static void main(String[] args) {
        response("Hello! I'm Duke\n"
                + "What can I do for you?");

        ArrayList<Task> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning) {
            String cmd = scanner.next();
            switch (cmd) {
            case "bye":
                response("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "list":
                StringBuffer listBuffer = new StringBuffer();
                listBuffer.append("Here are the tasks in your list:\n");

                int len = list.size();
                for(int i = 0; i < len; i++) {
                    listBuffer.append((i + 1) + "."
                            + list.get(i).toString()
                            + "\n");
                }
                response(listBuffer.toString());
                break;
            case "done":
                int itemId = scanner.nextInt() - 1;
                list.get(itemId).markAsDone();
                response("Nice! I've marked this task as done: \n  "
                        + list.get(itemId).toString());
                break;
            case "todo":
                Task t = createTodo(scanner.nextLine());
                addToList(t, list);
                break;
            case "deadline":
                t = createDeadline(scanner.nextLine());
                addToList(t, list);
                break;
            case "event":
                t = createEvent(scanner.nextLine());
                addToList(t, list);
                break;
            default:

                break;
            }
        }
        scanner.close();
    }
}