import duke.task.TaskList;
import duke.task.Todo;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    void addTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("description"));
        assertEquals(1, taskList.size());
        taskList.remove(1);
    }

    @Test
    void deleteTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("description"));
        taskList.remove(1);
        assertEquals(0, taskList.size());
    }
}