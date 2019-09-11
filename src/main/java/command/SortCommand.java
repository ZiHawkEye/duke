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
public class SortCommand extends Command {
    private String field;

    /**
     * Constructor for SortCommand.
     * 
     * @param field Field to sort tasks by.
     */
    public SortCommand(String field) {
        this.field = field;
    }
    
    /**
     * Sorts Tasks in TaskList according to the specified field.
     *
     * @param tasks TaskList to sort Tasks.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.sort(field);
        ui.printResponse("Here is the sorted list:\n"
                + tasks.toString() + "\n");
    }

    /**
     * Returns boolean to initiate exit of program.
     * 
     * @return False so program does not exit.
     */
    public boolean isExit() {
        return false;
    }
}