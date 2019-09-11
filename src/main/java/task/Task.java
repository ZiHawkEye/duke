package duke.task;

import java.io.Serializable;
import java.util.Date;

/**
 * Task class for storing information about a task.
 */
public class Task implements Serializable {
    protected String type;
    protected String description;
    protected Date date;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     * 
     * @param type Type of task.
     * @param description Description of task.
     * @param date Date of task.
     */
    public Task(String type, String description, Date date) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    /**
     * Returns String containing type of task.
     * 
     * @return String of type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns String containing description of task.
     * 
     * @return String containing description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns Date containing date of task.
     * 
     * @return Date containing date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns String containing status of task.
     * 
     * @return String containing status.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets status of task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets status of task as undone.
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Returns true if Tasks have the same values in all fields, else false.
     * 
     * @param task Task to be compared to this Task.
     * @return True if Tasks are the same.
     */
    public boolean equals(Task task) {
        return this.type.equals(task.type) 
                && this.description.equals(task.description)
                && this.date.equals(task.date);
    }

    /**
     * Returns String containing information about the task.
     * 
     * @return String containing status, description and date of task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s %s", this.type,
                this.getStatusIcon(),
                this.description,
                this.date.toString());
    }
}