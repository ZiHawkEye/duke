package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Command containing method for finding Tasks in TaskList.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructor for FindCommand.
     * 
     * @param query Query to be matched.
     */
    public FindCommand(String query) {
        this.query = query;
    }
    
    /**
     * Finds Tasks from the TaskList that matches the query.
     *
     * @param tasks TaskList to find Tasks from.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tempList = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(this.query)) {
                tempList.addTask(task);
            }
        }
        ui.printResponse("Here are the matching tasks in your list:\n  "
                + tempList.toString() + "\n");
    }

    /**
     * Returns boolean to initiate exit of program.
     * 
     * @return false so program does not exit.
     */
    public boolean isExit() {
        return false;
    }
}