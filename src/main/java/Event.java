package duke.task;

import java.util.Date;

public class Event extends Task {
    public Event(String description, Date date) {
        super("E", description, date);
    }

    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.type,
                this.getStatusIcon(),
                this.description,
                this.date.toString());
    }
}
