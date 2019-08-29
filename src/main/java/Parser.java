import java.util.Scanner;
import java.util.HashMap;
import java.util.Date;

public class Parser {
    public static final int BYE = 0;
    public static final int LIST = 1;
    public static final int DONE = 2;
    public static final int DELETE = 3;
    public static final int TASK = 4;

    protected HashMap<String, Integer> cmdMap;

    public Parser() {
        this.cmdMap = new HashMap<>();
        cmdMap.put("bye", BYE);
        cmdMap.put("list", LIST);
        cmdMap.put("done", DONE);
        cmdMap.put("delete", DELETE);
        cmdMap.put("todo", TASK);
        cmdMap.put("deadline", TASK);
        cmdMap.put("event", TASK);
    }

    public int parseInput(String input) {
        if(cmdMap.get(input) != null) {
            return cmdMap.get(input);
        } else {
            Scanner scanner = new Scanner(input);
            String cmd = scanner.next();
            scanner.close();
            return (cmdMap.get(cmd) != null) ? cmdMap.get(cmd) : -1;
        }
    }

    public int parseDone(String input) throws AssertionError {
        Scanner scanner = new Scanner(input);
        
        String cmd = scanner.next();
        assert(cmd.equals("done"));

        int itemId = scanner.nextInt();
        scanner.close();
        return itemId;
    }

    public int parseDelete(String input) throws AssertionError {
        Scanner scanner = new Scanner(input);
        
        String cmd = scanner.next();
        assert(cmd.equals("delete"));

        int itemId = scanner.nextInt();
        scanner.close();
        return itemId;
    }

    public Task parseTask(String input) throws DukeException {
        Scanner scanner = new Scanner(input);
        String type = scanner.next();
        scanner.close();

        switch(type) {
        case "todo":
            return createTodo(input.substring("todo".length()));
        case "deadline":
            return createDeadline(input.substring("deadline".length()));
        case "event":
            return createEvent(input.substring("event".length()));
        }
        throw new DukeException("Invalid task input");
    }

    static Todo createTodo(String input) throws DukeException {
        String description = input.length() == 0
                ? input
                : input.substring(1);
        if(description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
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

        String description = stringBuffer.length() == 0
                ? stringBuffer.toString()
                : stringBuffer.toString().substring(1);

        String date = scanner.nextLine();

        scanner.close();
        return new Deadline(description, parseDate(date));
    }

    static Event createEvent(String input) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(input);
        
        String temp = scanner.next();
        while(!temp.equals("/at")) {
            stringBuffer.append(" " + temp);
            temp = scanner.next();
        }

        String description = stringBuffer.length() == 0
                ? stringBuffer.toString()
                : stringBuffer.toString().substring(1);

        String date = scanner.nextLine();

        scanner.close();
        return new Event(description, parseDate(date));
    }

    static Date parseDate(String date) {
        String[] dayAndTime = date.trim().split(" ");
        String[] ddMmYy = dayAndTime[0].split("/");
        String hhSs = dayAndTime[1];
        int day = Integer.parseInt(ddMmYy[0]);
        int month = Integer.parseInt(ddMmYy[1]);
        int year = Integer.parseInt(ddMmYy[2]);
        int hour = Integer.parseInt(hhSs.substring(0, 2));
        int min = Integer.parseInt(hhSs.substring(2));
        System.out.println(String.format("%d %d %d %d %d", day, month, year, hour, min));
        return new Date(year - 1900, month - 1, day, hour, min);
    } 
}