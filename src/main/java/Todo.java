import java.util.Date;
import java.util.Scanner;

public class Todo extends Task {
    public Todo(String description) {
        super("T", description, new Date());
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.type,
                this.getStatusIcon(),
                this.description);
    }
}
