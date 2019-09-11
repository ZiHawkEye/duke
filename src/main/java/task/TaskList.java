package duke.task;

import duke.exception.DukeException;
import duke.task.Task;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * TaskList class contains Tasks to be done.
 */
public class TaskList extends ArrayList<Task> implements Serializable {
    /**
     * Adds Task to TaskList.
     * 
     * @param task Task to be added to TaskList.
     * @return Boolean if Task is successfully added.
     */
    public boolean addTask(Task task) throws DukeException {
        if (this.hasDuplicates(task)) {
            throw new DukeException("☹ OOPS!!! Task already exists in the list.");
        }
        return this.add(task);
    }

    /**
     * Returns Task of the specified id.
     * 
     * @param itemId Id of the Task.
     * @return Task of the specified itemId.
     */
    public Task get(int itemId) {
        return super.get(itemId - 1);
    }

    /**
     * Returns Task that has been removed from TaskList.
     * 
     * @param itemId Id of the Task to be removed.
     * @return Task which has been removed from TaskList.
     */
    @Override
    public Task remove(int itemId) {
        return super.remove(itemId - 1);
    }

    /**
     * Marks the Task of the specified id as done.
     * 
     * @param itemId Id of the Task to be marked as done.
     */
    public void markAsDone(int itemId) {
        super.get(itemId - 1).markAsDone();
    }

    /**
     * Sorts Tasks in TaskList by field specified.
     * 
     * @param field Field to sort Tasks by.
     */
    public void sort(String field) throws DukeException {
        switch (field) {
        case "date":
            Collections.sort(this,
                (task1, task2) -> task1.getDate().compareTo(task2.getDate()));
            break;
        case "description":
            Collections.sort(this,
                (task1, task2) -> task1.getDescription().compareTo(task2.getDescription()));
            break;
        case "type":
            Collections.sort(this,
                (task1, task2) -> task1.getType().compareTo(task2.getType()));
            break;
        case "done":
            Collections.sort(this,
                (task1, task2) -> task1.getStatusIcon().compareTo(task2.getStatusIcon()));
            break;
        default:
            throw new DukeException("☹ OOPS!!! Field not found to sort.");
        }
    } 

    /**
     * Returns true if there are duplicate Tasks in TaskList, else false.
     * 
     * @param task1 Task to be checked against.
     * @return True if there are duplicate Tasks, else false.
     */
    public boolean hasDuplicates(Task task1) {
        for(Task task2 : this) {
            if (task1.equals(task2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns String of Tasks contained in TaskList.
     * 
     * @return String of Tasks contained in TaskList.
     */
    public String toString() {
        StringBuffer listBuffer = new StringBuffer();
        int len = this.size();
        for (int i = 1; i <= len; i++) {
            listBuffer.append(i + "."
                    + this.get(i).toString()
                    + "\n");
        }
        return listBuffer.toString();
    }
}